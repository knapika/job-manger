package com.example.javaserver.services;

import com.example.javaserver.dtos.Offer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class OfferLogic {
    private static NofluffPageReader nofluffPageReader = new NofluffPageReader();

    public OfferLogic() {

    }

    public static List<Offer> getOffers(String city, String tech, String position, String experience, String salary) {
        try {
            List<Offer> offers = Collections.synchronizedList(new LinkedList<>());
            List<String> offersIds= nofluffPageReader.getJobsOffersIdByCriteria(tech, city, position, experience, salary);

            offersIds.parallelStream().limit(4).forEach(id -> offers.add(nofluffPageReader.getOfferObject(id)));

            return offers;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
