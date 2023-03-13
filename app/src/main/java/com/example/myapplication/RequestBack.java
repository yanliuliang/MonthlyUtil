package com.example.myapplication;

import com.example.myapplication.bean.ResultData;

import java.util.List;

public interface RequestBack {
    //获取指定月份的读书日期
    void getReadDayByMonth(ResultData list);
}
