package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;


import com.example.myapplication.adapter.AllBookAdapter;
import com.example.myapplication.adapter.BookAdapter;
import com.example.myapplication.bean.AllBookBean;
import com.example.myapplication.bean.BookInfo;
import com.example.myapplication.bean.ReadCountBean;
import com.example.myapplication.bean.ReadDayByMonth;
import com.example.myapplication.bean.RecordCountBean;
import com.example.myapplication.bean.ResultData;
import com.example.myapplication.databinding.ActivityMonthlyReportBinding;
import com.example.myapplication.dialog.LoadingDialog;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.haibin.calendarview.Calendar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MonthlyReportActivity extends AppCompatActivity {
    private ActivityMonthlyReportBinding binding;
    private BookAdapter monthBookAdapter;
    private BookAdapter yearBookAdapter;
    private AllBookAdapter allBookAdapter;
    private LoadingDialog loadingDialog;
    private final String TAG = "MonthlyReportActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMonthlyReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadingDialog = new LoadingDialog();


//        loadingDialog.show(getSupportFragmentManager(), "loading");
        initData();

        getHttpData();

        initMonthAndYearAdapter();
        initAllBookAdapter();
        // TODO: 2023/3/13 测试上报数据
//        MonthlyUtil.getIntent().addReadRecord("名著", 123, 100L);


    }

    /**
     * 获取服务器数据
     */
    private void getHttpData() {
        MonthlyUtil.getIntent().getReadDayByMonth(new RequestBack() {
            @Override
            public <T> void getReadDayByMonth(ResultData<T> list) {
                ReadDayByMonth data = (ReadDayByMonth) list.getData();
                //改变日历布局
                Map<String, Calendar> result = MonthlyUtil.getIntent().getMothResult(data.getDays());
                binding.calendarView.setSchemeDate(result);
                binding.tvReadBookInfo1.setText(MonthlyUtil.getIntent().getFirstTextInfo(MonthlyReportActivity.this, data));
                String maxInfo;
                if (data.getReadBookLastMonthCount() > data.getReadBookCount()) {
                    maxInfo = "少" + (data.getReadBookLastMonthCount() - data.getReadBookCount());
                } else {
                    maxInfo = "少" + (data.getReadBookCount() - data.getReadBookLastMonthCount());
                }
                binding.tvReadBookInfo2.setText(String.format(getResources().getString(R.string.month_all_read), data.getReadBookCount(), maxInfo));
                initChat1(data.getReadBookCount(), data.getReadBookLastMonthCount(), data.getReadBookCountByOther(), data.getReadBookLastMonthCountByOther());
            }
        });

        MonthlyUtil.getIntent().getReadRecordAllBook(new RequestBack() {
            @Override
            public <T> void getReadDayByMonth(ResultData<T> list) {
                List<AllBookBean> data = (List<AllBookBean>) list.getData();
                StringBuilder text = new StringBuilder();
                for (int i = 0; i < data.size(); i++) {
                    text.append("《" + data.get(i).getBookName() + "》，");
                }
                String value = String.format(getResources().getString(R.string.month_all_read_classification), text);
                binding.tvReadRecordBook.setText(value);
            }
        });

        MonthlyUtil.getIntent().getReadRecordByAllBook(new RequestBack() {
            @Override
            public <T> void getReadDayByMonth(ResultData<T> list) {

            }
        });

        MonthlyUtil.getIntent().getReadRecordCount(new RequestBack() {
            @Override
            public <T> void getReadDayByMonth(ResultData<T> list) {
                RecordCountBean bean = (RecordCountBean) list.getData();
            }
        });

        MonthlyUtil.getIntent().getReadRecordCountByBookType(new RequestBack() {
            @Override
            public <T> void getReadDayByMonth(ResultData<T> list) {
                List<ReadCountBean> data = (List<ReadCountBean>) list.getData();
                initChat2(data);
                initFlowLayout(data);
                initProgress(data);
            }
        });


    }

    private void initProgress(List<ReadCountBean> data) {
        int value1 =(int) (data.get(0).getPie()*100);
        Log.d(TAG, "initProgress: "+value1);
        Log.d(TAG, "initProgress: "+data.get(0).getPie());
        binding.progressView1.setProgress(value1);
        binding.tvProgress1.setText(value1+"%");
        binding.tvProgressTypeName1.setText(data.get(0).getType());

        int value2 =(int) (data.get(1).getPie()*100);
        binding.progressView2.setProgress(value2);
        binding.tvProgress2.setText(value2+"%");
        binding.tvProgressTypeName2.setText(data.get(1).getType());
    }

    /**
     * 全部读过的书籍
     */
    private void initAllBookAdapter() {
        allBookAdapter = new AllBookAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7, GridLayoutManager.VERTICAL, false);
        binding.rvAllBookByMonth.setLayoutManager(gridLayoutManager);
        binding.rvAllBookByMonth.setAdapter(allBookAdapter);
        allBookAdapter.setEntityList(BookInfo.data4());
    }

    /**
     * 月最多3本和年最多3本
     */
    private void initMonthAndYearAdapter() {
        monthBookAdapter = new BookAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        binding.recycleMonth.setLayoutManager(gridLayoutManager);
        binding.recycleMonth.setAdapter(monthBookAdapter);
        monthBookAdapter.setEntityList(BookInfo.data3());
        //年
        yearBookAdapter = new BookAdapter();
        GridLayoutManager gridLayoutManage2 = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        binding.recycleYear.setLayoutManager(gridLayoutManage2);
        binding.recycleYear.setAdapter(yearBookAdapter);
        yearBookAdapter.setEntityList(BookInfo.data3());
    }

    /**
     * 流布局数据填充
     */
    private void initFlowLayout(List<ReadCountBean> date) {

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 5, 10, 5);
        for (int i = 0; i < date.size(); i++) {
            TextView tv = new TextView(this);
            tv.setPadding(28, 10, 28, 10);
            tv.setText(date.get(i).getType() + "(" + date.get(i).getCount() + ")");
            tv.setMaxEms(10);
            tv.setSingleLine();
            if (date.get(i).getCount() % 2 == 0) {
                tv.setBackgroundResource(R.drawable.bg_radio_blue);
            } else {
                tv.setBackgroundResource(R.drawable.bg_radio_blue_uselect);
            }

            tv.setLayoutParams(layoutParams);
            binding.flowLayout.addView(tv, layoutParams);
        }
    }


    /**
     * 树状图数据
     */
    private void initChat1(int myMonthCount, int myMonthLastCount, int otherMonthCount, int otherMonthLastCount) {
        binding.chart1.setCount(myMonthCount, myMonthLastCount, otherMonthCount, otherMonthLastCount);
    }


    /**
     * 饼状图
     *
     * @param data
     */
    private void initChat2(List<ReadCountBean> data) {
        binding.chart2.setUsePercentValues(true);
        binding.chart2.getDescription().setEnabled(false);
        binding.chart2.setCenterText("\n名著阅读分布");
        binding.chart2.setDrawHoleEnabled(true);
        binding.chart2.setHoleColor(Color.WHITE);
        binding.chart2.setTransparentCircleColor(Color.WHITE);
        binding.chart2.setTransparentCircleAlpha(110);
        binding.chart2.setHoleRadius(58f);
        binding.chart2.setTransparentCircleRadius(61f);
        binding.chart2.setDrawCenterText(true);
        binding.chart2.setRotationEnabled(false);
        binding.chart2.setHighlightPerTapEnabled(true);
        binding.chart2.setMaxAngle(180f); // HALF CHART
        binding.chart2.setRotationAngle(180f);
        binding.chart2.setCenterTextOffset(0, -20);
        setData(data);
        binding.chart2.animateY(1400, Easing.EaseInOutQuad);
        Legend l = binding.chart2.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        binding.chart2.setEntryLabelColor(Color.WHITE);
        binding.chart2.setEntryLabelTextSize(12f);
    }


    private void setData(List<ReadCountBean> dataList) {
        ArrayList<PieEntry> values = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            values.add(new PieEntry(dataList.get(i).getCount(), dataList.get(i).getType()));
        }
        PieDataSet dataSet = new PieDataSet(values, "Election Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        binding.chart2.setData(data);
        binding.chart2.invalidate();
    }

    /**
     * 基础数据的初始化
     */
    private void initData() {
        binding.tvTime.setText(String.format(getResources().getString(R.string.monthly_title), 2022, 3));
    }
}
