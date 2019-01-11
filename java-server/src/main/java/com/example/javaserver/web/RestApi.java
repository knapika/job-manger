package com.example.javaserver.web;

import com.example.javaserver.dtos.*;
import com.example.javaserver.entities.Offer;
import com.example.javaserver.entities.User;
import com.example.javaserver.services.CompanyLogic;
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

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.example.javaserver.utils.Consts.ERROR_BAD_CRED;
import static com.example.javaserver.utils.Consts.STATUS_OK;

@CrossOrigin
@Controller
public class RestApi {
    @Autowired
    private UserLogic userLogic;

    @Autowired
    private OfferLogic offerLogic;

    @Autowired
    private CompanyLogic companyLogic;

    public RestApi() {
    }

//    @GetMapping(path="/add")
//    public @ResponseBody Integer addNewUser () {
//        User n = new User();
//        n.setLogin("JonSnow1");
//        n.setPassword(DigestUtils.sha256Hex("jon"));
//        n.setFirstName("Jon");
//        n.setLastName("Snow");
//        n.setEmail("aaa");
//        return userLogic.addUser(n);
//    }

    @RequestMapping(path="/offers")
    public @ResponseBody String getOffers(@RequestBody User user) {
        List<Offer> offers = offerLogic.getAllOffers();
        List<Offer> favorites = offerLogic.getUserFavorites(user.getUserID());

        favorites.parallelStream().forEach(fav -> {
            int idx = offers.indexOf(fav);
            offers.get(idx).setIsFavorite(true);
        });

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

    @GetMapping(path="/companies/cities")
    public @ResponseBody String getCities() {
        List<CityStats> stats = this.companyLogic.getCitiesStats(null);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return mapper.writeValueAsString(stats);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path="/favorite/add")
    public @ResponseBody String addFavoriteOffer(@RequestBody FavoriteFormDTO formDTO) throws JsonProcessingException {
        this.offerLogic.addFavoriteOffer(formDTO.getUserID(), formDTO.getOfferID());
        return new ObjectMapper().writeValueAsString(formDTO);
    }


    @RequestMapping(path="/favorite/delete")
    public @ResponseBody String deleteFavoriteOffer(@RequestBody FavoriteFormDTO formDTO) throws JsonProcessingException {
        this.offerLogic.deleteFavoriteOffer(formDTO.getUserID(), formDTO.getOfferID());
        return new ObjectMapper().writeValueAsString(formDTO);
    }

    @RequestMapping(path="/favorites")
    public @ResponseBody String getUserFavorites(@RequestBody FavoriteFormDTO formDTO) {
        List<Offer> offers = this.offerLogic.getUserFavorites(formDTO.getUserID());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return mapper.writeValueAsString(offers);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path="/stats/offers/byCities")
    public @ResponseBody String getOffersByCities(@RequestBody TechnologyStats tech) {
        List<CityStats> stats = this.companyLogic.getCitiesStats(tech.getTechnology());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return mapper.writeValueAsString(stats);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path="/stats/technologies/byCities")
    public @ResponseBody String getTechnologiesByCities(@RequestBody CityStats city) {
        List<TechnologyStats> stats = this.companyLogic.getTechnologiesStatsInCity(city.getCity());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return mapper.writeValueAsString(stats);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(path="/register")
    public @ResponseBody String registerUser(@RequestBody User user) {
        user.setRegistrationDate(new Date());
        ReportDTO report = this.userLogic.addUser(user);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return mapper.writeValueAsString(report);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path="/login")
    public @ResponseBody String loginUser(@RequestBody User user) throws JsonProcessingException {
        User loginResult = this.userLogic.login(user);
        ReportDTO<User> report = new ReportDTO<User>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        if (loginResult != null) {
            report.setData(loginResult);
            report.setStatus(STATUS_OK);
        } else {
            report.setStatus(ERROR_BAD_CRED);
        }
        return mapper.writeValueAsString(report);
    }
}
