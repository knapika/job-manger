package com.example.javaserver.services;

import com.example.javaserver.dtos.PostingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class OfferLogic {

    @Autowired
    private NofluffParser parser;

    public List<PostingDTO> getOffers(String city, String tech, String position, String experience, String salary) {
//        try {
//            List<Offer> offers = Collections.synchronizedList(new LinkedList<>());
//            List<PostingDTO> offersIDs= parser.getJobsOffersIdByCriteria(tech, city, position, experience, salary);
//
////            offersIDs.parallelStream().limit(4).forEach(id -> offers.add(parser.getOfferObject(id)));
//
//            return offersIDs;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return null;
    }
}
