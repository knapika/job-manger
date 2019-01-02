package com.example.javaserver.services;

import com.example.javaserver.dtos.CityStats;
import com.example.javaserver.dtos.PostingDTO;
import com.example.javaserver.dtos.TechnologyStats;
import com.example.javaserver.entities.Company;
import com.example.javaserver.entities.Offer;
import com.example.javaserver.repositories.CompanyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyLogic {

    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    public Company addOfferToCompany(Offer offer, PostingDTO postingDTO) {
        Optional<Company> company = this.companyRepository.findByUrl(postingDTO.getCompany().getUrl());
        if (company.isPresent()) {
            offer.setCompany(company.get());
            company.get().getOffers().add(offer);
            return company.get();
        } else {
            Company newCompany = new Company();
            newCompany.setName(postingDTO.getCompany().getName());
            newCompany.setUrl(postingDTO.getCompany().getUrl());
            newCompany.setLocationCity(this.translateCity(postingDTO.getEssentials().getLocationCity()));
            newCompany.setLocationCountry(postingDTO.getEssentials().getLocationCountry().getName());
            newCompany.setLocationStreet(postingDTO.getEssentials().getLocationStreet());
            newCompany.setPostalCode(postingDTO.getEssentials().getPostalCode());
            newCompany.setCompanySize(postingDTO.getEssentials().getCompanySize());
            List<Offer> offerList = new LinkedList<>();
            offerList.add(offer);
            newCompany.setOffers(new HashSet<>(offerList));
            this.companyRepository.save(newCompany);
            return newCompany;
        }
    }

    public List<CityStats> getCitiesStats(String technology) {
        List<CityStats> cityStats = new LinkedList<>();
        if(technology == null) {
            technology = "%";
        }

        List<Object[]> stats = stats = this.companyRepository.getCitiesStatsByTechnologies(technology);

        stats.forEach(pair -> cityStats.add(new CityStats(pair[0].toString(),
            Long.parseLong(String.valueOf(pair[1])),
            Double.parseDouble(String.valueOf(pair[2])))));

        return cityStats;
    }

    public List<TechnologyStats> getTechnologiesStatsInCity(String city) {
        if (city == null) {
            city = "%";
        }
        List<Object[]> stats = this.companyRepository.getTechnologiesStatsByCity(city);
        List<TechnologyStats> technologyStats = new LinkedList<>();

        stats.forEach(pair -> technologyStats.add(new TechnologyStats(pair[0].toString(),
                this.getValueFromString(pair[1].toString()))));

        return technologyStats;
    }

    private Long getValueFromString(String val) {
        int idx = val.indexOf(".");
        if(idx == -1) {
            return Long.parseLong(String.valueOf(val));
        }

        return Long.parseLong(String.valueOf(val.substring(0, idx)));
    }

    private String translateCity(String city) {
        if (city.toLowerCase().equals("warsaw")) {
            return "Warszawa";
        }

        if (city.toLowerCase().equals("cracow")) {
            return "Kraków";
        }

        if (city.toLowerCase().contains("remote") || city.toLowerCase().contains("zdalnie") ||
                city.toLowerCase().contains("any")) {
            return "Zdalnie";
        }
        return city;
    }
}
