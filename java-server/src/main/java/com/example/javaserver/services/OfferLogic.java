package com.example.javaserver.services;

import com.example.javaserver.dtos.CategoryStats;
import com.example.javaserver.dtos.PostingDTO;
import com.example.javaserver.dtos.TechnologyStats;
import com.example.javaserver.entities.Company;
import com.example.javaserver.entities.Offer;
import com.example.javaserver.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.math.BigInteger;
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


    public Offer getOfferById(Integer id) {
        Optional<Offer> offer = this.offerRepository.findById(id);
        if(offer.isPresent()) {
            return offer.get();
        }
        return null;
    }

    public void addCompany(Integer id, Company company) {
        Optional<Offer> offer = this.offerRepository.findById(id);
        if(offer.isPresent()) {
            offer.get().setCompany(company);
            this.offerRepository.save(offer.get());
        }
    }

    public List<Offer> getAllOffers() {
        List<Offer> offers = new LinkedList<>();
        this.offerRepository.findAll().forEach(offers::add);
        return offers;
    }

    public  List<CategoryStats> getCategories() {
        List<Object[]> stats = this.offerRepository.getCategoryStats();
        List<CategoryStats> categoryStats = new LinkedList<>();
        stats.forEach(pair -> categoryStats.add(new CategoryStats(pair[0].toString(),
                Long.parseLong(String.valueOf(pair[1])))));

        return categoryStats;
    }

    public List<TechnologyStats> getTechnologies() {
        List<Object[]> stats = this.offerRepository.getTechnologyStats();
        List<TechnologyStats> technologyStats = new LinkedList<>();
        stats.forEach(pair -> technologyStats.add(new TechnologyStats(pair[0].toString(),
                Long.parseLong(String.valueOf(pair[1])))));

        return technologyStats;
    }
}
