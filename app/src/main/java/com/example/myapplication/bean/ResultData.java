package com.example.myapplication.bean;

import java.util.List;

public class ResultData {
    private int code;
    private String msg;
    private ReadDayByMonth data;
    private String arrayList;

    public String getArrayList() {
        return arrayList;
    }

    public void setArrayList(String arrayList) {
        this.arrayList = arrayList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ReadDayByMonth getData() {
        return data;
    }

    public void setData(ReadDayByMonth data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", arrayList='" + arrayList + '\'' +
                '}';
    }

    public class ReadDayByMonth {
        private List<String> days;
        private long readAllTime;
        private long readMaxTime;
        private String readMaxTimeBook;
        private int readBookCount;
        private int readBookLastMonthCount;
        private int readBookCountByOther;
        private int readBookLastMonthCountByOther;

        public List<String> getDays() {
            return days;
        }

        public void setDays(List<String> days) {
            this.days = days;
        }

        public long getReadAllTime() {
            return readAllTime;
        }

        public void setReadAllTime(long readAllTime) {
            this.readAllTime = readAllTime;
        }

        public long getReadMaxTime() {
            return readMaxTime;
        }

        public void setReadMaxTime(long readMaxTime) {
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
}


