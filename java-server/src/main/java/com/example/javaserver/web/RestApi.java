package com.example.javaserver.web;

import com.example.javaserver.dtos.CategoryStats;
import com.example.javaserver.dtos.LevelStats;
import com.example.javaserver.dtos.PostingDTO;
import com.example.javaserver.dtos.TechnologyStats;
import com.example.javaserver.entities.Offer;
import com.example.javaserver.entities.User;
import com.example.javaserver.services.OfferLogic;
import com.example.javaserver.services.UserLogic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class RestApi {
    @Autowired
    private UserLogic userLogic;

    @Autowired
    private OfferLogic offerLogic;

    public RestApi() {
    }

    @GetMapping(path="/add")
    public @ResponseBody Integer addNewUser () {
        User n = new User();
        n.setLogin("JonSnow1");
        n.setPassword(DigestUtils.sha256Hex("jon"));
        n.setFirstName("Jon");
        n.setLastName("Snow");
        n.setEmail("aaa");
        return userLogic.addUser(n);
    }

    @GetMapping(path="/offers")
    public @ResponseBody String getPostings() {
        List<Offer> offers = offerLogic.getAllOffers();
//        System.out.println(offers.get(0).getCompanyID().getName());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return mapper.writeValueAsString(offers);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(path="/offers/categories")
    public @ResponseBody String getCategories() {
        List<CategoryStats> stats = this.offerLogic.getCategories();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return mapper.writeValueAsString(stats);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(path="/offers/technologies")
    public @ResponseBody String getTechnologies() {
        List<TechnologyStats> stats = this.offerLogic.getTechnologies();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return mapper.writeValueAsString(stats);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(path="/offers/levels")
    public @ResponseBody String getLevels() {
        List<LevelStats> stats = this.offerLogic.getLevels();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return mapper.writeValueAsString(stats);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
