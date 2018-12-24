package com.example.javaserver.dtos;

public class LevelStats {
    private String level;
    private Long count;

    public LevelStats(String level, Long count) {
        this.level = level;
        this.count = count;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
