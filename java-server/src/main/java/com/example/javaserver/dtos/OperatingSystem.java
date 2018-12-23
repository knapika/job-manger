package com.example.javaserver.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OperatingSystem {
    @JsonProperty("lin")
    private boolean lin;

    @JsonProperty("mac")
    private boolean mac;

    @JsonProperty("win")
    private boolean win;


    public boolean isLin() {
        return lin;
    }

    public void setLin(boolean lin) {
        this.lin = lin;
    }

    public boolean isMac() {
        return mac;
    }

    public void setMac(boolean mac) {
        this.mac = mac;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}
