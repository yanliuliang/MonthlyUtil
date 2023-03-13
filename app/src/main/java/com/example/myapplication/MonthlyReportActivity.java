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
import com.example.myapplication.bean.BookInfo;
import com.example.myapplication.databinding.ActivityMonthlyReportBinding;
import com.example.myapplication.dialog.LoadingDialog;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

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
        loadingDialog.show(getSupportFragmentManager(), "loading");
        initData();
        initChat1();
        initChat2();
        initFlowLayout();

        initMonthAndYearAdapter();
        initAllBookAdapter();
        // TODO: 2023/3/13 测试上报数据
//        MonthlyUtil.getIntent().addReadRecord("名著", 123, 100L);
        MonthlyUtil.getIntent().getReadDayByMonth(list -> {
            Log.d(TAG, "onCreate: " + list);
            loadingDialog.dismiss();
            initCalendarView(list.getData().getDays());
        });
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
    private void initFlowLayout() {
        List<BookInfo> list = BookInfo.data2();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 5, 10, 5);
        for (int i = 0; i < list.size(); i++) {
            TextView tv = new TextView(this);
            tv.setPadding(28, 10, 28, 10);
            tv.setText(list.get(i).getType() + "(" + list.get(i).getCount() + ")");
            tv.setMaxEms(10);
            tv.setSingleLine();
            if (list.get(i).getCount() % 2 == 0) {
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
    private void initChat1() {
        binding.chart1.setCount(100, 50, 20, 100);
    }

    /**
     * 改变日历布局
     */
    private void initCalendarView(List<String> list) {
        //此方法在巨大的数据量上不影响遍历性能，推荐使用

        binding.calendarView.setSchemeDate(MonthlyUtil.getIntent().getMothResult(list));
    }

    /**
     * 饼状图
     */
    private void initChat2() {

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

        setData();

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


    private void setData() {
        ArrayList<PieEntry> values = new ArrayList<>();
        List<BookInfo> list = BookInfo.data1();
        for (int i = 0; i < list.size(); i++) {
            values.add(new PieEntry(list.get(i).getCount(), list.get(i).getType()));
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
