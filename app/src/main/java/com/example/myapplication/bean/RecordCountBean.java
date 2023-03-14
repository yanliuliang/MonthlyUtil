package com.example.myapplication.bean;


public class RecordCountBean {
    private int countOfYear;
    private int countOfMonth;
    private int countOfQuarter;
    private int countOfWeek;
    private int countOfDay;

    public int getCountOfYear() {
        return countOfYear;
    }

    public void setCountOfYear(int countOfYear) {
        this.countOfYear = countOfYear;
    }

    public int getCountOfMonth() {
        return countOfMonth;
    }

    public void setCountOfMonth(int countOfMonth) {
        this.countOfMonth = countOfMonth;
    }

    public int getCountOfQuarter() {
        return countOfQuarter;
    }

    public void setCountOfQuarter(int countOfQuarter) {
        this.countOfQuarter = countOfQuarter;
    }

    public int getCountOfWeek() {
        return countOfWeek;
    }

    public void setCountOfWeek(int countOfWeek) {
        this.countOfWeek = countOfWeek;
    }

    public int getCountOfDay() {
        return countOfDay;
    }

    public void setCountOfDay(int countOfDay) {
        this.countOfDay = countOfDay;
    }

    public RecordCountBean(int countOfYear, int countOfMonth, int countOfQuarter, int countOfWeek, int countOfDay) {
        this.countOfYear = countOfYear;
        this.countOfMonth = countOfMonth;
        this.countOfQuarter = countOfQuarter;
        this.countOfWeek = countOfWeek;
        this.countOfDay = countOfDay;
    }

    @Override
    public String toString() {
        return "RecordCountBean{" +
                "countOfYear=" + countOfYear +
                ", countOfMonth=" + countOfMonth +
                ", countOfQuarter=" + countOfQuarter +
                ", countOfWeek=" + countOfWeek +
                ", countOfDay=" + countOfDay +
                '}';
    }
}
