package com.example.javaserver.services;

import com.example.javaserver.dtos.CategoryStats;
import com.example.javaserver.dtos.LevelStats;
import com.example.javaserver.dtos.PostingDTO;
import com.example.javaserver.dtos.TechnologyStats;
import com.example.javaserver.entities.Company;
import com.example.javaserver.entities.FavoriteOffer;
import com.example.javaserver.entities.Offer;
import com.example.javaserver.repositories.FavoriteOfferRepository;
import com.example.javaserver.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.javaserver.utils.Consts.STATUS_OK;

@Service
public class OfferLogic {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private FavoriteOfferRepository favoriteOfferRepository;
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

    public List<LevelStats> getLevels() {
        List<Object[]> stats = this.offerRepository.getLevelStats();
        List<LevelStats> levelStats = new LinkedList<>();
        stats.forEach(pair -> levelStats.add(new LevelStats(pair[0].toString(),
                Long.parseLong(String.valueOf(pair[1])))));

        return levelStats;
    }

    public void addFavoriteOffer(Integer userID, Integer offerID) {
        this.favoriteOfferRepository.save(new FavoriteOffer(userID, offerID));
    }

    public void deleteFavoriteOffer(Integer userID, Integer offerID) {
        this.favoriteOfferRepository.deleteByUserIDAndOfferID(userID, offerID);
    }

    public List<Offer> getUserFavorites(Integer userID) {
        List<FavoriteOffer> favorites = this.favoriteOfferRepository.findByUserID(userID);
        List<Integer> offerIDs = favorites.stream().map(fav -> fav.getOfferID()).collect(Collectors.toList());
        List<Offer> offers = (List<Offer>) this.offerRepository.findAllById(offerIDs);
        offers.parallelStream().forEach(offer -> offer.setIsFavorite(true));
        return offers;
    }

}
