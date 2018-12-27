package com.example.javaserver.services;

import com.example.javaserver.dtos.BasicPostingDTO;
import com.example.javaserver.dtos.PostingDTO;
import com.example.javaserver.dtos.PostingsDTO;

import com.example.javaserver.entities.Company;
import com.example.javaserver.entities.Offer;
import com.example.javaserver.entities.ParserUsed;

import com.example.javaserver.repositories.EquipmentRepository;
import com.example.javaserver.repositories.ParserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class NofluffParser {

    @Autowired
    private ParserRepository parserRepository;

    @Autowired
    private CompanyLogic companyLogic;

    @Autowired
    private OfferLogic offerLogic;

    @Autowired
    private EquipmentLogic equipmentLogic;


    static final String parserName = "Nofluff";

    private HttpCustomClient httpCustomClient = new HttpCustomClient();
    private String nofluffApiUrl = "https://nofluffjobs.com/api/search/posting";
    private String commonUrlForOffers = "https://nofluffjobs.com/api/postingNew/";
    private ObjectMapper objectMapper;

    public NofluffParser() {
        this.objectMapper = new ObjectMapper();
    }

//    @EventListener(ApplicationReadyEvent.class)
    public void saveOffers() throws IOException {
        String completeUrl = nofluffApiUrl;
        String json = httpCustomClient.getPageContent(completeUrl);

        ObjectMapper objectMapper = new ObjectMapper();
        PostingsDTO postings = objectMapper.readValue(json, PostingsDTO.class);
        List<BasicPostingDTO> postedOffers = postings.getPostings();

        Optional<ParserUsed> parserUsed = this.parserRepository.findByParserName(parserName);
        if(parserUsed.isPresent()) {
            postedOffers = postedOffers.parallelStream()
                    .filter(offer -> offer.getPosted().after(parserUsed.get().getUsed()))
                    .collect(Collectors.toList());
        }
        postedOffers.parallelStream().forEach(offer -> this.saveOffer(offer.getId()));
    }

    private void saveOffer(String offerID) {
        String offerURL = commonUrlForOffers + offerID;
        try {
            String json = httpCustomClient.getPageContent(offerURL);

            PostingDTO postingDTO = this.objectMapper.readValue(json, PostingsDTO.class).getPosting();
            Offer offer = this.createOffer(postingDTO);

            this.offerLogic.saveOffer(offer);
            Company company = this.companyLogic.addOfferToCompany(offer, postingDTO);
            this.offerLogic.addCompany(offer.getOfferID(), company);
            this.equipmentLogic.addEquipment(postingDTO.getEquipment(), offer);
            System.out.println("Stworzono");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Offer createOffer(PostingDTO postingDTO) {
        System.out.println("Tworzenie");
        Offer offer = new Offer();
        offer.setPostingID(postingDTO.getId());
        offer.setSalaryFrom(postingDTO.getEssentials().getSalaryFrom());
        offer.setSalaryTo(postingDTO.getEssentials().getSalaryTo());
        offer.setSalaryCurrency(postingDTO.getEssentials().getSalaryCurrency());
        offer.setSalaryDuration(postingDTO.getEssentials().getSalaryDuration());
        offer.setMusts(new HashSet<>(postingDTO.getMusts()));
        offer.setNices(new HashSet<>(postingDTO.getNices()));
        offer.setLangs(new HashSet<>(postingDTO.getLangs()));

        List<Offer> offers = new LinkedList<>();

        offer.getMusts().stream().forEach(must -> must.setMusts(new HashSet<>(offers)));
        offer.getNices().stream().forEach(nice -> nice.setNices(new HashSet<>(offers)));
        offer.getLangs().stream().forEach(lang -> lang.setLangs(new HashSet<>(offers)));

        offer.setTitle(postingDTO.getTitle().getTitle());
        offer.setCategory(postingDTO.getCategory());
        offer.setLevel(postingDTO.getTitle().getLevel());
        offer.setTechnology(postingDTO.getTitle().getTechnology());
        offer.setEmploymentType(postingDTO.getEssentials().getEmploymentType());
        return offer;
    }
}
