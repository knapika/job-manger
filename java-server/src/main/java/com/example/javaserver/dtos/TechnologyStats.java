package com.example.javaserver.dtos;

public class TechnologyStats {
    private String technology;
    private Long count;

    public TechnologyStats() {
    }

    public TechnologyStats(String technology, Long count) {
        this.technology = technology;
        this.count = count;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
