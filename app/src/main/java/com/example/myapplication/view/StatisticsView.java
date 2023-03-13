package com.example.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.R;

/**
 * @Description:绘制一个简单的统计图
 * @CreateDate: 2023/3/11
 * @Version:
 */
public class StatisticsView extends LinearLayout {
    //我的阅读数量
    private int myMonthCount = 0;
    private int myMonthLastCount = 0;
    //其他人的阅读数量
    private int otherMonthCount = 0;
    private int otherMonthLastCount = 0;

    private Context context;
    //控件的最大高度
    private Float maxHeight;
    //每本书占据的高度
    private Float itemHeight;

    private View view1;
    private View view2;
    private View view3;
    private View view4;

    private TextView tvCount1;
    private TextView tvCount2;
    private TextView tvCount3;
    private TextView tvCount4;


    public StatisticsView(Context context) {
        this(context, null);
    }

    public StatisticsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatisticsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        maxHeight = dp2px(200f);
        initView();
    }

    public void setCount(int myMonthCount, int myMonthLastCount, int otherMonthCount, int otherMonthLastCount) {
        this.myMonthCount = myMonthCount;
        this.myMonthLastCount = myMonthLastCount;
        this.otherMonthCount = otherMonthCount;
        this.otherMonthLastCount = otherMonthLastCount;
        refreshView();
    }

    /**
     * 重绘制界面
     */
    private void refreshView() {
        int a = Math.max(myMonthCount, myMonthLastCount);
        int b = Math.max(otherMonthCount, otherMonthLastCount);
        a = Math.max(a, b);
        itemHeight = maxHeight / a;

        Log.d("refreshView", "refreshView: "+itemHeight);

        ConstraintLayout.LayoutParams layoutParams1 = (ConstraintLayout.LayoutParams) view1.getLayoutParams();
        layoutParams1.height = (int) (myMonthCount * itemHeight);
        view1.setLayoutParams(layoutParams1);
        tvCount1.setText(myMonthCount+"本");

        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) view2.getLayoutParams();
        layoutParams2.height = (int) (myMonthLastCount * itemHeight);
        view2.setLayoutParams(layoutParams2);
        tvCount2.setText(myMonthLastCount+"本");


        ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) view3.getLayoutParams();
        layoutParams3.height = (int) (otherMonthCount * itemHeight);
        view3.setLayoutParams(layoutParams3);
        tvCount3.setText(otherMonthCount+"本");

        ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) view4.getLayoutParams();
        layoutParams4.height = (int) (otherMonthLastCount * itemHeight);
        view4.setLayoutParams(layoutParams4);
        tvCount4.setText(otherMonthCount+"本");



    }

    /**
     * dp转px
     */
    private Float dp2px(float dpVal) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics()
        );
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_chat_view, this, true);
        view1 = view.findViewById(R.id.view_count1);
        view2 = view.findViewById(R.id.view_count2);
        view3 = view.findViewById(R.id.view_count3);
        view4 = view.findViewById(R.id.view_count4);
        tvCount1 = view.findViewById(R.id.tv_count1);
        tvCount2 = view.findViewById(R.id.tv_count2);
        tvCount3 = view.findViewById(R.id.tv_count3);
        tvCount4 = view.findViewById(R.id.tv_count4);
    }
}
