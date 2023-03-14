package com.example.myapplication;

import com.example.myapplication.bean.ResultData;

public interface RequestBack {
    //获取指定月份的读书日期
   <T> void getReadDayByMonth(ResultData<T> list);
}
