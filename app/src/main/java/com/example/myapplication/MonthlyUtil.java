package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.example.myapplication.bean.AllBookBean;
import com.example.myapplication.bean.ReadCountBean;
import com.example.myapplication.bean.ReadDayByMonth;
import com.example.myapplication.bean.RecordCountBean;
import com.example.myapplication.bean.ResultData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
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
     */
    public Map<String, Calendar> getMothResult(List<Integer> list) {
        Map<String, Calendar> map = new HashMap<>();
        for (int j : list) {
            map.put(getSchemeCalendar(year, month, j, 0xFF40db25, "假").toString(),
                    getSchemeCalendar(year, month, j, 0xFF40db25, "假"));
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
     * 第一个文案的数据
     */
    public String getFirstTextInfo(Context context, ReadDayByMonth bean) {
        String count = bean.getReadMaxTime() / bean.getReadAllTime()+"%";
        String info = String.format(context.getResources().getString(R.string.monthly_all_book)
                , bean.getReadBookCount()
                , bean.getReadAllTime()
                , bean.getReadMaxTimeBook()
                , count
                , bean.getReadMaxTimeBook()
                , bean.getDays().size());
        return info;
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
    public void addReadRecord(String bookName, Long readTime) {
        map.clear();
        map.put("userId", userId);
        map.put("bookName", bookName);
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
    private void getBaseDate(){
        map.clear();
        map.put("userId", userId);
        String pattern = "yyyy-MM-dd HH:mm:ss";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        String date = sDateFormat.format(new Date(System.currentTimeMillis()));
        year = Integer.parseInt(date.split(" ")[0].split("-")[0]);
        month = Integer.parseInt(date.split(" ")[0].split("-")[1]);
        Log.e(TAG, "getReadDayByMonth: ");
        map.put("dateStr", date);
    }
    /**
     * 获取指定月份的读书日期
     */
    /**
     * @param requestBack
     */
    public void getReadDayByMonth(RequestBack requestBack) {
        Log.e(TAG, "getReadDayByMonth: 获取指定月份的读书日期");
        getBaseDate();
        Xutils.post(GET_READ_GAY_BY_MONTH, map, new Xutils.GetDataCallback() {
            @Override
            public void success(String result) {
                ResultData<ReadDayByMonth> resultData = new Gson().fromJson(result, new TypeToken<ResultData<ReadDayByMonth>>() {
                }.getType());
                requestBack.getReadDayByMonth(resultData);
                Log.e(TAG, "success: getReadDayByMonth" + result);
            }

            @Override
            public void failed(String... args) {
                Log.d(TAG, "failed: getReadDayByMonth" + args);
            }
        });
    }

    /**
     * 获取指定日期的图书列表
     */
    public void getReadRecordAllBook(RequestBack requestBack) {
        getBaseDate();
        Xutils.post(GET_READ_RECORD_ALL_BOOK, map, new Xutils.GetDataCallback() {
            @Override
            public void success(String result) {
                ResultData<List<AllBookBean>> resultData = new Gson().fromJson(result, new TypeToken<ResultData<List<AllBookBean>>>() {
                }.getType());
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
     * 每月各个书读的次数
     */
    public void getReadRecordByAllBook(RequestBack requestBack) {
        getBaseDate();
        Xutils.post(GET_READ_RECORD_BY_ALL_BOOL, map, new Xutils.GetDataCallback() {
            @Override
            public void success(String result) {
                ResultData<String[]> resultData = new Gson().fromJson(result, new TypeToken<ResultData<String[]>>() {
                }.getType());
                requestBack.getReadDayByMonth(resultData);
                Log.e(TAG, "success:getReadRecordByAllBook " + result);
            }

            @Override
            public void failed(String... args) {
                Log.d(TAG, "failed: " + args);
            }
        });
    }

    /**
     * 获取指定日期的读书次数
     */
    public void getReadRecordCount(RequestBack requestBack) {
        getBaseDate();
        Xutils.post(GET_READ_RECORD_COUNT, map, new Xutils.GetDataCallback() {
            @Override
            public void success(String result) {
                ResultData<RecordCountBean> resultData = new Gson().fromJson(result, new TypeToken<ResultData<RecordCountBean>>() {
                }.getType());
                requestBack.getReadDayByMonth(resultData);
                Log.e(TAG, "success:getReadRecordCount " + result);
            }

            @Override
            public void failed(String... args) {
                Log.d(TAG, "failed: " + args);
            }
        });
    }

    /**
     * 获取指定月份读书不同种类书本数量
     */
    public void getReadRecordCountByBookType(RequestBack requestBack) {
        getBaseDate();
        Xutils.post(GET_READ_RECORD_COUNT_BY_BOOK_TYPE, map, new Xutils.GetDataCallback() {
            @Override
            public void success(String result) {
                ResultData<List<ReadCountBean>> resultData = new Gson().fromJson(result, new TypeToken<ResultData<List<ReadCountBean>>>() {}.getType());
                requestBack.getReadDayByMonth(resultData);
                Log.e(TAG, "success: getReadRecordCountByBookType" + result);
            }

            @Override
            public void failed(String... args) {
                Log.d(TAG, "failed: " + args);
            }
        });
    }
}
