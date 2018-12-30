package com.example.javaserver.dtos;

public class CityStats {
    private String city;
    private Long count;
    private Double avg;

    public CityStats() {
    }

    public CityStats(String city, Long count, Double avg) {
        this.city = city;
        this.count = count;
        this.avg = avg;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

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
