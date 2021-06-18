package com.example.first;

public class Rate {
    String name;
    String rate;
    public Rate(String name, String rate){
        this.name = name;
        this.rate = rate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRate() {
        return rate;
    }
}
