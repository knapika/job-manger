package com.example.javaserver.services;

import com.example.javaserver.dtos.PostingDTO;
import com.example.javaserver.entities.Offer;
import com.example.javaserver.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

import static com.example.javaserver.utils.Consts.STATUS_OK;

@Service
public class OfferLogic {

    @Autowired
    private OfferRepository offerRepository;

//    public List<PostingDTO> getOffers(String city, String tech, String position, String experience, String salary) {
//        try {
////            List<Offer> offers = Collections.synchronizedList(new LinkedList<>());
//            List<PostingDTO> offersIDs= parser.getJobsOffersIdByCriteria(tech, city, position, experience, salary);
////
////            offersIDs.parallelStream().limit(4).forEach(id -> offers.add(parser.getOfferObject(id)));
////
////            return offersIDs;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public Integer saveOffer(Offer offer) {
        this.offerRepository.save(offer);
        return STATUS_OK;
    }

    public List<Offer> getAllOffers() {
        List<Offer> offers = new LinkedList<>();
        this.offerRepository.findAll().forEach(offers::add);
        return offers;
    }
}
