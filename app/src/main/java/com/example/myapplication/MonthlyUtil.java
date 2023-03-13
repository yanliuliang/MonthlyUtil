package com.example.myapplication;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.myapplication.bean.ResultData;
import com.google.gson.Gson;
import com.haibin.calendarview.Calendar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonthlyUtil {
    private static String userId = "1";
    private final String TAG = "MonthlyUtil";
    private final static String ADD_READ_RECORD = "addReadRecord"; //上报数据的接口
    private final static String GET_READ_GAY_BY_MONTH = "getReadDayByMonth"; //获取指定月份的读书日期
    private final static String GET_READ_RECORD_ALL_BOOK = "getReadRecordAllBook"; //获取指定日期的图书列表
    private final static String GET_READ_RECORD_BY_ALL_BOOL = "getReadRecordByAllBook"; //每月各个书读的次数
    private final static String GET_READ_RECORD_COUNT = "getReadRecordCount"; //获取指定日期的读书次数
    private final static String GET_READ_RECORD_COUNT_BY_BOOK_TYPE = "getReadRecordCountByBookType";//获取指定月份读书不同种类书本数量
    private static final MonthlyUtil monthlyUtil = new MonthlyUtil();

    private int year = 2023;
    private int month = 3;

    public static MonthlyUtil getIntent() {
        return monthlyUtil;
    }

    private static Map<String, Object> map = new HashMap<>();

    private MonthlyUtil() {
    }

    /**
     * 设置月份数据
     *
     * @return
     */
    public Map<String, Calendar> getMothResult(List<String> list) {

        Map<String, Calendar> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(getSchemeCalendar(year, month, Integer.parseInt(list.get(i)), 0xFF40db25, "假").toString(),
                    getSchemeCalendar(year, month, Integer.parseInt(list.get(i)), 0xFF40db25, "假"));
        }

        return map;
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }

    /**
     * 获取用户的月度报告
     */
    public void getHttpData() {
        map.clear();
        map.put("userId", userId);
    }

    /**
     * 存储用户的userId
     */
    public void setUserId(String userId) {
        MonthlyUtil.userId = userId;
    }

    /**
     * 上报用户的阅读记录
     */
    public void addReadRecord(String bookType, int bookid, Long readTime) {
        map.clear();
        map.put("userId", userId);
        map.put("bookType", bookType);
        map.put("bookid", bookid);
        map.put("readTime", readTime * 1000L);
        Xutils.post(ADD_READ_RECORD, map, new Xutils.GetDataCallback() {
            @Override
            public void success(String result) {
                Log.e(TAG, "success: " + result);
            }

            @Override
            public void failed(String... args) {
                Log.d(TAG, "failed: " + args);
            }
        });
    }

    /**
     * 获取指定月份的读书日期
     */
    public void getReadDayByMonth(RequestBack requestBack) {
        Log.e(TAG, "getReadDayByMonth: 获取指定月份的读书日期");
        map.clear();
        map.put("userId", userId);
        String pattern = "yyyy-MM-dd HH:mm:ss";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        String date = sDateFormat.format(new Date(System.currentTimeMillis()));
        year = Integer.parseInt(date.split(" ")[0].split("-")[0]);
        month = Integer.parseInt(date.split(" ")[0].split("-")[1]);
        Log.e(TAG, "getReadDayByMonth: " );
        map.put("dateStr", date);
        Xutils.post(GET_READ_GAY_BY_MONTH, map, new Xutils.GetDataCallback() {
            @Override
            public void success(String result) {
                ResultData resultData = new Gson().fromJson(date, ResultData.class);
                requestBack.getReadDayByMonth(resultData);
                Log.e(TAG, "success: " + result);
            }

            @Override
            public void failed(String... args) {
                Log.d(TAG, "failed: " + args);
            }
        });
    }

    /**
     * 获取指定日期的图书列表
     */
    public void getReadRecordAllBook() {
        map.clear();
        map.put("userId", userId);
        String pattern = "yyyy-MM-dd HH:mm:ss";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        String date = sDateFormat.format(new Date(System.currentTimeMillis()));
        map.put("date", date);
        Xutils.post(GET_READ_RECORD_ALL_BOOK, map, new Xutils.GetDataCallback() {
            @Override
            public void success(String result) {
                Log.e(TAG, "success: " + result);
            }

            @Override
            public void failed(String... args) {
                Log.d(TAG, "failed: " + args);
            }
        });
    }

}
