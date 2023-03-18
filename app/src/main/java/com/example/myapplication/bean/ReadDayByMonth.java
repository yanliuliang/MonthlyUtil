package com.example.myapplication.bean;

import java.util.List;

public class ReadDayByMonth {
    private List<Integer> days;
    private int readAllTime;
    private int readMaxTime;
    private String readMaxTimeBook;
    private int readBookCount;
    private int readBookLastMonthCount;
    private int readBookCountByOther;
    private int readBookLastMonthCountByOther;

    public List<Integer> getDays() {
        return days;
    }

    public void setDays(List<Integer> days) {
        this.days = days;
    }

    public int getReadAllTime() {
        return readAllTime;
    }

    public void setReadAllTime(int readAllTime) {
        this.readAllTime = readAllTime;
    }

    public int getReadMaxTime() {
        return readMaxTime;
    }

    public void setReadMaxTime(int readMaxTime) {
        this.readMaxTime = readMaxTime;
    }

    public String getReadMaxTimeBook() {
        return readMaxTimeBook;
    }

    public void setReadMaxTimeBook(String readMaxTimeBook) {
        this.readMaxTimeBook = readMaxTimeBook;
    }

    public int getReadBookCount() {
        return readBookCount;
    }

    public void setReadBookCount(int readBookCount) {
        this.readBookCount = readBookCount;
    }

    public int getReadBookLastMonthCount() {
        return readBookLastMonthCount;
    }

    public void setReadBookLastMonthCount(int readBookLastMonthCount) {
        this.readBookLastMonthCount = readBookLastMonthCount;
    }

    public int getReadBookCountByOther() {
        return readBookCountByOther;
    }

    public void setReadBookCountByOther(int readBookCountByOther) {
        this.readBookCountByOther = readBookCountByOther;
    }

    public int getReadBookLastMonthCountByOther() {
        return readBookLastMonthCountByOther;
    }

    public void setReadBookLastMonthCountByOther(int readBookLastMonthCountByOther) {
        this.readBookLastMonthCountByOther = readBookLastMonthCountByOther;
    }

    @Override
    public String toString() {
        return "ReadDayByMonth{" +
                "days=" + days +
                ", readAllTime=" + readAllTime +
                ", readMaxTime=" + readMaxTime +
                ", readMaxTimeBook='" + readMaxTimeBook + '\'' +
                ", readBookCount=" + readBookCount +
                ", readBookLastMonthCount=" + readBookLastMonthCount +
                ", readBookCountByOther=" + readBookCountByOther +
                ", readBookLastMonthCountByOther=" + readBookLastMonthCountByOther +
                '}';
    }
}