package com.example.javaserver.dtos;

public class Skill {
    private final String rank;
    private final String value;

    public Skill(String rank, String value) {
        this.rank = rank;
        this.value = value;
    }

    public String getRank() {
        return rank;
    }

    public String getValue() {
        return value;
    }
}
