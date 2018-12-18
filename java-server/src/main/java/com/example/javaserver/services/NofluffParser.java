package com.example.javaserver.services;

import com.example.javaserver.dtos.PostingDTO;
import com.example.javaserver.dtos.PostingsDTO;
import com.example.javaserver.entities.Offer;
import com.example.javaserver.entities.ParserUsed;
import com.example.javaserver.repositories.ParserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class NofluffParser {

    @Autowired
    private ParserRepository parserRepository;

    static final String parserName = "Nofluff";

    private HttpCustomClient httpCustomClient = new HttpCustomClient();
    private String nofluffApiUrl = "https://nofluffjobs.com/api/search/posting";
    private String commonUrlForOffers = "https://nofluffjobs.com/api/postingNew/";
    private ObjectMapper objectMapper;

    public NofluffParser() {
        this.objectMapper = new ObjectMapper();
    }

    public List<PostingDTO> getJobsOffersIdByCriteria(String tech, String city, String position, String experience,
                                                  String salary) throws IOException {
        String completeUrl = nofluffApiUrl;
        String json = httpCustomClient.getPageContent(completeUrl);


        PostingsDTO postings = this.objectMapper.readValue(json, PostingsDTO.class);
        List<PostingDTO> postedOffers = postings.getPostings();

        Optional<ParserUsed> parserUsed = this.parserRepository.findByParserName(NofluffParser.parserName);
        if(parserUsed.isPresent()) {
            postedOffers = postedOffers.parallelStream()
                    .filter(offer -> offer.getPosted().after(parserUsed.get().getUsed()))
                    .collect(Collectors.toList());
        }

        postedOffers.parallelStream().limit(1).forEach(offer -> this.saveOffer(offer.getId()));

        return postings.getPostings();
    }

    private void saveOffers() throws IOException {
        String completeUrl = nofluffApiUrl;
        String json = httpCustomClient.getPageContent(completeUrl);

        ObjectMapper objectMapper = new ObjectMapper();
        PostingsDTO postings = objectMapper.readValue(json, PostingsDTO.class);
        List<PostingDTO> postedOffers = postings.getPostings();

        Optional<ParserUsed> parserUsed = this.parserRepository.findByParserName(NofluffParser.parserName);
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
            System.out.println(this.objectMapper.writeValueAsString(offer));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Offer createOffer(PostingDTO postingDTO) {
        Offer offer = new Offer();
        offer.setOfferID(postingDTO.getId());
        offer.setMusts(postingDTO.getMusts());
        return offer;
    }
}
