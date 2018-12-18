package com.example.javaserver.web;

import com.example.javaserver.dtos.PostingDTO;
import com.example.javaserver.entities.User;
import com.example.javaserver.services.OfferLogic;
import com.example.javaserver.services.UserLogic;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RestApi {
    @Autowired
    private UserLogic userLogic;

    @Autowired
    private OfferLogic offerLogic;

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

    @GetMapping(path="/postings")
    public @ResponseBody List<PostingDTO> getPostings() {
        return offerLogic.getOffers("", " ","", "", "");
    }

//    @GetMapping(path="/all")
//    public @ResponseBody
//    Iterable<User> getAllUsers() {
//        // This returns a JSON or XML with the users
//        return userRepository.findAll();
//    }

//    @RequestMapping(value = "/offers", method = RequestMethod.GET)
//    @ResponseBody
//    public List<Offer> getJobsOffers(@RequestParam(value = "city", required = false, defaultValue = "") String city,
//                                     @RequestParam(value = "tech", required = false, defaultValue = "") String tech,
//                                     @RequestParam(value = "position", required = false, defaultValue = "") String position,
//                                     @RequestParam(value = "experience", required = false, defaultValue = "") String experience,
//                                     @RequestParam(value = "salary", required = false, defaultValue = "") String salary) {
//
//        List<Offer> offers = this.offerLogic.getOffers(city, tech, position, experience, salary);
//        return offers;
//    }
}
