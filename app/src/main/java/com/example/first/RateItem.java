package com.example.first;

public class RateItem {
    private int id;
    private String curName;
    private String curRate;

    public RateItem(){}

    public RateItem(String curName,String curRate){
        this.curName = curName;
        this.curRate = curRate;
    }

    public int getId() {
        return id;
    }

    public String getCurName() {
        return curName;
    }

    public float getCurRate() {
        return Float.valueOf(curRate);
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public void setCurRate(String curRate) {
        this.curRate = curRate;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RateItem{" +
                "id=" + id +
                ", curName='" + curName + '\'' +
                ", curRate='" + curRate + '\'' +
                '}';
    }
}
