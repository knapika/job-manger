package com.example.javaserver.services;

import com.example.javaserver.dtos.PostingDTO;
import com.example.javaserver.dtos.PostingsDTO;
import com.example.javaserver.entities.Company;
import com.example.javaserver.entities.Offer;
import com.example.javaserver.entities.ParserUsed;
import com.example.javaserver.entities.Skill;
import com.example.javaserver.repositories.CompanyRepository;
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


    static final String parserName = "Nofluff";

    private HttpCustomClient httpCustomClient = new HttpCustomClient();
    private String nofluffApiUrl = "https://nofluffjobs.com/api/search/posting";
    private String commonUrlForOffers = "https://nofluffjobs.com/api/postingNew/";
    private ObjectMapper objectMapper;

    public NofluffParser() {
        this.objectMapper = new ObjectMapper();
    }

//    public List<PostingDTO> getJobsOffersIdByCriteria(String tech, String city, String position, String experience,
//                                                  String salary) throws IOException {
//        String completeUrl = nofluffApiUrl;
//        String json = httpCustomClient.getPageContent(completeUrl);
//
//
//        PostingsDTO postings = this.objectMapper.readValue(json, PostingsDTO.class);
//        List<PostingDTO> postedOffers = postings.getPostings();
//
//        Optional<ParserUsed> parserUsed = this.parserRepository.findByParserName(NofluffParser.parserName);
//        if(parserUsed.isPresent()) {
//            postedOffers = postedOffers.parallelStream()
//                    .filter(offer -> offer.getPosted().after(parserUsed.get().getUsed()))
//                    .collect(Collectors.toList());
//        }
//
//        postedOffers.parallelStream().limit(1).forEach(offer -> this.saveOffer(offer.getId()));
//
//        return postings.getPostings();
//    }

    @EventListener(ApplicationReadyEvent.class)
    public void saveOffers() throws IOException {
        String completeUrl = nofluffApiUrl;
        String json = httpCustomClient.getPageContent(completeUrl);

        ObjectMapper objectMapper = new ObjectMapper();
        PostingsDTO postings = objectMapper.readValue(json, PostingsDTO.class);
        List<PostingDTO> postedOffers = postings.getPostings();

        Optional<ParserUsed> parserUsed = this.parserRepository.findByParserName(parserName);
        if(parserUsed.isPresent()) {
            postedOffers = postedOffers.parallelStream()
                    .filter(offer -> offer.getPosted().after(parserUsed.get().getUsed()))
                    .collect(Collectors.toList());
        }

        postedOffers.parallelStream().limit(1).forEach(offer -> this.saveOffer(offer.getId()));
    }

    private void saveOffer(String offerID) {
        String offerURL = commonUrlForOffers + offerID;
        try {
            String json = httpCustomClient.getPageContent(offerURL);

            PostingDTO postingDTO = this.objectMapper.readValue(json, PostingsDTO.class).getPosting();
            Offer offer = this.createOffer(postingDTO);

            System.out.println(offer.getOfferID());
            this.offerLogic.saveOffer(offer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Offer createOffer(PostingDTO postingDTO) {
        System.out.println("Tworzenie");
        Offer offer = new Offer();
        offer.setPostingID(postingDTO.getId());
        offer.setMusts(new HashSet<>(postingDTO.getMusts()));
        offer.setNices(new HashSet<>(postingDTO.getNices()));
        offer.setLangs(new HashSet<>(postingDTO.getLangs()));

        List<Offer> offers = new LinkedList<>();

        offer.getMusts().stream().forEach(must -> must.setMusts(new HashSet<>(offers)));
        offer.getNices().stream().forEach(nice -> nice.setNices(new HashSet<>(offers)));
        offer.getLangs().stream().forEach(lang -> lang.setLangs(new HashSet<>(offers)));

        offer.setCompanyID(this.companyLogic.addOfferToCompany(offer, postingDTO));
        System.out.println("Stworzono");
        return offer;
    }
}
