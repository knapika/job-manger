package com.example.javaserver.dtos;

public class CityStats {
    private String city;
    private Long count;

    public CityStats(String city, Long count) {
        this.city = city;
        this.count = count;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
