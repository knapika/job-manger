package com.example.javaserver.services;

import com.example.javaserver.dtos.PostingDTO;
import com.example.javaserver.entities.Company;
import com.example.javaserver.entities.Offer;
import com.example.javaserver.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyLogic {

    @Autowired
    private CompanyRepository companyRepository;

    public Company addOfferToCompany(Offer offer, PostingDTO postingDTO) {
        Optional<Company> company = this.companyRepository.findByUrl(postingDTO.getCompany().getUrl());
        if (company.isPresent()) {
            offer.setCompanyID(company.get());
            company.get().getOffersID().add(offer);
            return company.get();
        } else {
            Company newCompany = new Company();
            newCompany.setName(postingDTO.getCompany().getName());
            newCompany.setUrl(postingDTO.getCompany().getUrl());
            newCompany.setLocationCity(postingDTO.getEssentials().getLocationCity());
            newCompany.setLocationCountry(postingDTO.getEssentials().getLocationCountry().getName());
            newCompany.setLocationStreet(postingDTO.getEssentials().getLocationStreet());
            newCompany.setPostalCode(postingDTO.getEssentials().getPostalCode());
            List<Offer> offerList = new LinkedList<>();
            offerList.add(offer);
            newCompany.setOffersID(new HashSet<>(offerList));
//            this.companyRepository.save(newCompany);
            return newCompany;
        }
    }
}
