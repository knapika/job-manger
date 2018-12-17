package com.example.javaserver.services;


import com.example.javaserver.dtos.Offer;
import com.example.javaserver.dtos.Skill;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static org.apache.http.protocol.HTTP.USER_AGENT;

@Component
public class NofluffPageReader {
    HttpCustomClient httpCustomClient = new HttpCustomClient();

//    @Value("${nofluffApiUrl}")
    private String nofluffApiUrl = "https://nofluffjobs.com/api/search/posting";

//    @Value("${commonPartForOffer}")
    private String commonUrlForOffers = "https://nofluffjobs.com/api/postingNew/";

    public List<String>  getJobsOffersIdByCriteria(String tech, String city, String position, String experience,
                                                   String salary) throws IOException {
        String completeUrl = getCompleteUrlForHttpGet(tech, city, position, experience, salary);
        String json = httpCustomClient.getPageContent(completeUrl);
        List<String> idsList = getIdsList(json);
        return idsList;
    }

    public Offer getOfferObject(String offerId) {
        String offerUrl = getOfferUrl(offerId);
        try {
            String json = httpCustomClient.getPageContent(offerUrl);
            String category = getValueFromJson(json, "\"category\":");
            String city = getValueFromJson(json, "\"city\":");
            String street = getValueFromJson(json, "\"street\":");
            String companyName = getValueFromJson(json, "\"name\":");
            String companyUrl = getValueFromJson(json, "\"url\":");
            String salaryCurrency = getValueFromJson(json, "\"salaryCurrency\":");
            String salaryFrom = getValueFromJson(json, "\"salaryFrom\":" );
            String salaryTo = getValueFromJson(json, "\"salaryTo\":" );
            List<Skill> langs = getSkillsList(json, "langs", "logo");
            List<Skill> musts = getSkillsList(json, "musts", "nices");
            List<Skill> nices = getSkillsList(json, "nices", "oneOnOne");

            return new Offer(offerId, category, city, street, companyName, companyUrl, salaryFrom, salaryTo,
                    musts, nices, langs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Skill> getSkillsList(String json, String skillsName, String nextCategory) {
        List<Skill> skills = new LinkedList<>();
        int beginIndex = json.indexOf(skillsName);
        int endIndex = json.indexOf(nextCategory);

        String skillsListString = json.substring(beginIndex, endIndex - 1 );
        String[] skillsLines = skillsListString.split("\n");

        for(int i = 2; i < skillsLines.length - 3; i = i + 4) {
            String rank = getElementFromLine(skillsLines[i], "\"rank\":");
            String value = getElementFromLine(skillsLines[i + 1], "\"value\":");
            skills.add(new Skill(rank, value));
        }
        return skills;
    }

    private String getOfferUrl(String offerId) {
        return commonUrlForOffers + offerId;
    }

    private String getCompleteUrlForHttpGet(String tech, String city, String position, String experience,
                                            String salary) {
        String compeleteUrl = nofluffApiUrl;

        if(checkIfAnyCriteriaDefined(tech, city, position, experience, salary)) {
            compeleteUrl += "?criteria=";
            if(checkIfcriterionDefined(tech)) {
                compeleteUrl += tech + "+";
            }
            if(checkIfcriterionDefined(city)) {
                compeleteUrl += "city%3D" + city + "+";
            }
            if(checkIfcriterionDefined(position)) {
                compeleteUrl += position + "+";
            }
            if(checkIfcriterionDefined(experience)) {
                compeleteUrl += experience + "+";
            }
            if(checkIfcriterionDefined(salary)) {
                compeleteUrl += salary + "+";
            }
            compeleteUrl = deleteAddCharacterIfNecessary(compeleteUrl);
        }
        return compeleteUrl;
    }

    private boolean checkIfAnyCriteriaDefined(String tech, String city, String position, String experience,
                                              String salary) {
        return checkIfcriterionDefined(tech) || checkIfcriterionDefined(city) || checkIfcriterionDefined(position) ||
                checkIfcriterionDefined(experience) || checkIfcriterionDefined(salary);
    }

    private boolean checkIfcriterionDefined(String criterion) {
        return criterion.length() > 0;
    }

    private String deleteAddCharacterIfNecessary(String s) {
        if(checkIfLastCharIsAddCharacter(s)) {
            return s.substring(0, s.length() - 1);
        } else {
            return s;
        }
    }

    private boolean checkIfLastCharIsAddCharacter(String s) {
        return s.charAt(s.length() - 1) == '+';
    }

    private List<String> getIdsList(String json) {
        List<String> idsList = new LinkedList<>();
        String[] lines = json.split("\n");

        for(String line : lines) {
            if(line.contains("\"id\":")){
                idsList.add(getElementFromLine(line, "\"id\":"));
            }
        }
        return idsList;
    }

    private String getValueFromJson(String json, String paramName) {
        String[] lines = json.split("\n");

        for(String line : lines) {
            if(line.contains(paramName)){
                return getElementFromLine(line, paramName);
            }
        }
        return "";
    }

    private String getElementFromLine(String line, String elemName) {
        line = line.replace(elemName, "");
        line = line.replace("\"", "");
        line = line.replace(",", "");
        return line.trim();
    }
}
