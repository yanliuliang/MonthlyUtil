package com.example.myapplication.bean;

public class ReadCountBean {
    String type;
    int count;
    double pie;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPie() {
        return pie;
    }

    public void setPie(double pie) {
        this.pie = pie;
    }

    public ReadCountBean(String type, int count, double pie) {
        this.type = type;
        this.count = count;
        this.pie = pie;
    }
}
