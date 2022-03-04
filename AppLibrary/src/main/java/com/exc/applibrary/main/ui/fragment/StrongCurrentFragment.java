package com.exc.applibrary.main.ui.fragment;

import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exc.applibrary.R;
import com.exc.applibrary.databinding.FragmentStrongCurrentBinding;
import com.exc.applibrary.main.HttpRequest;
import com.exc.applibrary.main.customview.CustomDialog;
import com.exc.applibrary.main.db.Electricity;
import com.exc.applibrary.main.model.DateEnergyModel;
import com.exc.applibrary.main.model.DeviceListModel;
import com.exc.applibrary.main.model.EnergyAllModel;
import com.exc.applibrary.main.model.EveryDayModel;
import com.exc.applibrary.main.model.EveryMonthModel;
import com.exc.applibrary.main.model.NodeCountModel;
import com.exc.applibrary.main.utils.DateUtil;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.net.HttpCookie;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import zuo.biao.library.base.BaseFragment;
import zuo.biao.library.interfaces.OnHttpResponseListener;
import zuo.biao.library.util.JsonUtils;
import zuo.biao.library.util.Log;

public class StrongCurrentFragment extends BaseFragment implements OnHttpResponseListener, OnChartValueSelectedListener,
        OnChartGestureListener , View.OnClickListener {
    private PieChart mPieChart;
    private BarChart mBarChart;
    private BarData mBarData;
    private BarChart dBarChart;
    private BarData dBarData;
    private Spinner yearspinner;
    private Spinner monthspinner;
    private TextView day_energy;
    private TextView month_energy;
    private TextView month_style;
    private TextView tv_online;
    private TextView tv_offline;
    private TextView num1;
    private TextView num2;
    private TextView num3;
    private TextView num4;
    private TextView num5;
    private TextView num6;
    private TextView num7;
    private TextView num8;
    private ArrayAdapter<String> adapter;
    private CustomDialog customDialog;

    private NodeCountModel nodeCountModel;
    private EnergyAllModel energyAllModel;
    private List<DateEnergyModel> dateEnergyModelList = new ArrayList<>();

    private final int ELECTRICITY_NODECOUNT_CODE = 1;
    private final int ELECTRICITY_ENERGYALL_CODE = 2;
    private final int ELECTRICITY_EVERYMONTH_CODE = 3;
    private final int ELECTRICITY_EVERYDAY_CODE = 4;
    private final int ELECTRICITY_MONTH_CODE = 5;
    private final int ELECTRICITY_YEAR_CODE = 6;

    private XAxis xAxis;
    private String year;
    private String month;
    private int month_day;

    //收到消息回主UI刷新界面
    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 123:
                    setmBarData();
                    break;
            }

            super.handleMessage(msg);
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater .inflate(R.layout.fragment_strong_current ,container,false) ;
        mPieChart = view.findViewById(R.id.mPieChart);
        mBarChart = view.findViewById(R.id.mbarChart);
        dBarChart = view.findViewById(R.id.dbarChart);
        yearspinner = view.findViewById(R.id.yrar_spinner);
        monthspinner = view.findViewById(R.id.month_spinner);
        day_energy = view.findViewById(R.id.day_energy);
        month_energy = view.findViewById(R.id.month_energy);
        month_style = view.findViewById(R.id.month_style_text);
        tv_online = view.findViewById(R.id.tv_online);
        tv_offline = view.findViewById(R.id.tv_offline);

        num1 = view.findViewById(R.id.num1);
        num2 = view.findViewById(R.id.num2);
        num3 = view.findViewById(R.id.num3);
        num4 = view.findViewById(R.id.num4);
        num5 = view.findViewById(R.id.num5);
        num6 = view.findViewById(R.id.num6);
        num7 = view.findViewById(R.id.num7);
        num8 = view.findViewById(R.id.num8);

        initView();

        return view;
    }

    //饼状图
    public void initNewPieChart(){
        mPieChart.setUsePercentValues(true);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setExtraOffsets(5, 10, 5, 5);

        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        //设置中间文件
        mPieChart.setCenterText("在线0%");

        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setHoleColor(Color.WHITE);

        mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(110);

        mPieChart.setHoleRadius(58f);
        mPieChart.setTransparentCircleRadius(61f);

        mPieChart.setDrawCenterText(true);

        mPieChart.setRotationAngle(0);
        // 触摸旋转
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);

        //变化监听
        mPieChart.setOnChartValueSelectedListener(this);
        //模拟数据
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(0, "在线"));
        entries.add(new PieEntry(100, "离线"));


        //设置数据
        setPieData(entries);
        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = mPieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // 输入标签样式
        mPieChart.setEntryLabelColor(Color.WHITE);
        mPieChart.setEntryLabelTextSize(0f);
    }

    private void setPieData(ArrayList<PieEntry> entries) {
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(1f);
        dataSet.setSelectionShift(5f);

        int[] VORDIPLOM_COLORS = {
                Color.rgb(0, 255, 127), Color.rgb(255, 0, 0)
        };

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : VORDIPLOM_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(0f);
        data.setValueTextColor(R.color.alph);
        mPieChart.setData(data);
        mPieChart.highlightValues(null);
        //刷新
        mPieChart.invalidate();
    }

    //圆柱图
    public void initBarchart(BarChart barChart){
        //设置表格上的点，被点击的时候，的回调函数
        barChart.setOnChartValueSelectedListener(this);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.getDescription().setEnabled(false);
        // 如果60多个条目显示在图表,drawn没有值
        barChart.setMaxVisibleValueCount(60);
        // 扩展现在只能分别在x轴和y轴
        barChart.setPinchZoom(false);
        //是否显示表格颜色
        barChart.setDrawGridBackground(false);
        //去掉左下角图例
        barChart.getLegend().setEnabled(false);

        //默认动画
        barChart.animateX(2500);
        //设置是否可以触摸
        barChart.setTouchEnabled(true);
        //设置是否可以拖拽
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(false);//设置是否可以缩放

        if(barChart == dBarChart){
            float ratio = (float) month_day/12;
            //显示的时候是按照多大的比率缩放显示，1f表示不放大缩小
            dBarChart.zoom(ratio,1f,0,0);
        }else {
            mBarChart.zoom(1f,1f,0,0);
        }

        //自定义设置横坐标
        xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(12);
        xAxis.setLabelRotationAngle(-60);


        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        //这个替换setStartAtZero(true)
        leftAxis.setAxisMinimum(0f);

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(8, false);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f);

        // 获取 右边 y 轴
        YAxis mRAxis = barChart.getAxisRight();
        // 隐藏 右边 Y 轴
        mRAxis.setEnabled(false);
        // 获取 左边 Y轴
        YAxis mLAxis = barChart.getAxisLeft();
        // 隐藏 左边 Y 轴
        mLAxis.setEnabled(true);
        // 取消 左边 Y轴 坐标线
        mLAxis.setDrawAxisLine(false);
        // 取消 横向 网格线
        mLAxis.setDrawGridLines(false);

        //设置数据
        setmBarData();
    }
    public void setmBarData(){

        // y 轴数据
        ArrayList<BarEntry> yValues = new ArrayList<>();
        boolean isday = day_energy.isSelected()?true:false;

        if(day_energy.isSelected()){
            // 2.0 ----x 轴数据
            for (int x = 1; x < month_day +1; x++) {
                // 2.0 ----xValues.add(String.valueOf(i));
                float y = 0;
                for (DateEnergyModel model : dateEnergyModelList) {
                    if(String.valueOf(x).equals(model.getDate())){
                        y = Integer.parseInt(model.getEnergy());
                    }
                }
                yValues.add(new BarEntry(x, y));
            }
        }else {
            for (int x = 1; x < 12 +1; x++) {
                // 2.0 ----xValues.add(String.valueOf(i));
                float y = 0;
                for (DateEnergyModel model : dateEnergyModelList) {
                    if(String.valueOf(x).equals(model.getDate())){
                        y = Integer.parseInt(model.getEnergy());
                    }
                }
                yValues.add(new BarEntry(x, y));
            }
        }


        // y 轴数据集
        BarDataSet barDataSet = new BarDataSet(yValues, "条形图");
        barDataSet.setColor(getResources().getColor(R.color.blue_btn));

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int i = (int)value;
                if(i<=yValues.size()){
                    if(isday){
                        return year + "/" + month +"/" + i;
                    }else
                        return year + "/" + i;
                }else
                    return "";
            }
        });

        if(isday){
            dBarData = new BarData(barDataSet);
            dBarChart.setData(dBarData);
            dBarChart.getViewPortHandler().setMaximumScaleX(month_day/12);
            dBarChart.invalidate();

        }else {
            mBarData = new BarData(barDataSet);
            mBarChart.setData(mBarData);
            mBarChart.getViewPortHandler().setMaximumScaleY(1.0f);
            mBarChart.invalidate();
        }



    }

    //时间选择框
    public void inityearspinnerView() {
        final String[] color = {"2021", "2020", "2019"};
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, color);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearspinner.setAdapter(adapter);
        year = color[0];

        //第五步：添加监听器，为下拉列表设置事件的响应
        yearspinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView tv = (TextView) view;
                tv.setTextColor(getResources().getColor(R.color.black_slight)); //设置颜色
                tv.setTextSize(14.0f); //设置大小

                view.setVisibility(View.VISIBLE);

                year = color[position];
                customDialogShow();

                DateEnergyData();
//                if(day_energy.isSelected()){
//                    HttpRequest.electricityEnergyMonthEverydayHttp(year + "-" + (month.length()>1?month:("0"+month)),0,ELECTRICITY_EVERYDAY_CODE,StrongCurrentFragment.this::onHttpResponse);
//                }else {
//                    HttpRequest.electricityEnergyYearEverymonthHttp(year,0,ELECTRICITY_EVERYMONTH_CODE,StrongCurrentFragment.this::onHttpResponse);
//                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("spinner", "Spinner Touch事件被触发!");
            }

        });

        //将spinnertext添加到OnTouchListener对内容选项触屏事件处理
        yearspinner.setOnTouchListener(new Spinner.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setVisibility(View.VISIBLE);
                Log.i("spinner", "Spinner Touch事件被触发!");
                return false;
            }
        });

        //焦点改变事件处理
        yearspinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                v.setVisibility(View.VISIBLE);
                Log.i("spinner", "Spinner FocusChange事件被触发！");
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void initmonthspinnerView() {
        final String[] color = {"1", "2", "3","4","5","6","7","8","9","10","11","12"};
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, color);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthspinner.setAdapter(adapter);
        month = String.valueOf(DateUtil.getMonth());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        getMonthDay(date);

        monthspinner.setSelection(DateUtil.getMonth()-1,true);
        //第五步：添加监听器，为下拉列表设置事件的响应
        monthspinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                TextView tv = (TextView) view;
                tv.setTextColor(getResources().getColor(R.color.black_slight)); //设置颜色
                tv.setTextSize(14.0f); //设置大小

                view.setVisibility(View.VISIBLE);

                month = color[position];

                String str = year + "-" + (month.length()>1?month:("0"+month)) + "-" + "01";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = (Date) simpleDateFormat.parse(str);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                getMonthDay(date);

                customDialogShow();
                DateEnergyData();
//                if(day_energy.isSelected()){
//                    HttpRequest.electricityEnergyMonthEverydayHttp(year + "-" + (month.length()>1?month:("0"+month)),0,ELECTRICITY_EVERYDAY_CODE,StrongCurrentFragment.this::onHttpResponse);
//                }else {
//                    HttpRequest.electricityEnergyYearEverymonthHttp(year,0,ELECTRICITY_EVERYMONTH_CODE,StrongCurrentFragment.this::onHttpResponse);
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("spinner", "Spinner Touch事件被触发!");
            }

        });


        //将spinnertext添加到OnTouchListener对内容选项触屏事件处理
        monthspinner.setOnTouchListener(new Spinner.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setVisibility(View.VISIBLE);
                Log.i("spinner", "Spinner Touch事件被触发!");
                return false;
            }
        });

        //焦点改变事件处理
        monthspinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                v.setVisibility(View.VISIBLE);
                Log.i("spinner", "Spinner FocusChange事件被触发！");
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = (Date) formatter.parse(strTime);
        return date;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void initView() {
        customDialog = new CustomDialog(getActivity());
        day_energy.setSelected(true);
        month_energy.setSelected(false);

        initNewPieChart();
        inityearspinnerView();
        initmonthspinnerView();
        initBarchart(dBarChart);
        initBarchart(mBarChart);

        mBarChart.setVisibility(View.GONE);
        dBarChart.setVisibility(View.VISIBLE);

        day_energy.setTextColor(getResources().getColor(R.color.blue_btn));
        month_energy.setTextColor(getResources().getColor(R.color.gray));
        day_energy.setOnClickListener(this::onClick);
        month_energy.setOnClickListener(this::onClick);

        initData();
    }

    @Override
    public void onClick(View v) {
        if(v == day_energy){
            day_energy.setSelected(true);
            month_energy.setSelected(false);

            day_energy.setTextColor(getResources().getColor(R.color.blue_btn));
            month_energy.setTextColor(getResources().getColor(R.color.gray));

            month_style.setVisibility(View.VISIBLE);
            monthspinner.setVisibility(View.VISIBLE);

            mBarChart.setVisibility(View.GONE);
            dBarChart.setVisibility(View.VISIBLE);

            customDialogShow();
//            HttpRequest.electricityEnergyMonthEverydayHttp(year + "-" + (month.length()>1?month:("0"+month)),0,ELECTRICITY_EVERYDAY_CODE,StrongCurrentFragment.this::onHttpResponse);
            DateEnergyData();
        }else if (v == month_energy){
            day_energy.setSelected(false);
            month_energy.setSelected(true);

            day_energy.setTextColor(getResources().getColor(R.color.gray));
            month_energy.setTextColor(getResources().getColor(R.color.blue_btn));

            month_style.setVisibility(View.GONE);
            monthspinner.setVisibility(View.GONE);

            mBarChart.setVisibility(View.VISIBLE);
            dBarChart.setVisibility(View.GONE);

            customDialogShow();
//            HttpRequest.electricityEnergyYearEverymonthHttp(year,0,ELECTRICITY_EVERYMONTH_CODE,StrongCurrentFragment.this::onHttpResponse);
            DateEnergyData();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void initData() {
        //获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);
        getMonthDay(date);

        HttpRequest.electricityNodeCountHttp(ELECTRICITY_NODECOUNT_CODE,this::onHttpResponse);
        HttpRequest.electricityEnergyAllHttp(time,ELECTRICITY_ENERGYALL_CODE,this::onHttpResponse);
//        HttpRequest.electricityEnergyMonthEverydayHttp(year + "-" + (month.length()>1?month:("0"+month)),0,ELECTRICITY_EVERYDAY_CODE,this::onHttpResponse);
//        DateEnergyData();
    }

    public void DateEnergyData(){
        String date = year + "-" + month + "-" + "1";
        if(day_energy.isSelected()){
            HttpRequest.apielectricityenergycountByTypeAndNumHttp(date,month_day,"day",ELECTRICITY_MONTH_CODE,this::onHttpResponse);
        }else {
            HttpRequest.apielectricityenergycountByTypeAndNumHttp(date,1,"month",ELECTRICITY_MONTH_CODE,this::onHttpResponse);
        }

    }
    @Override
    public void onHttpResponse(int requestCode, String resultJson, Exception e) {
        if (requestCode == ELECTRICITY_NODECOUNT_CODE) {

            if (null == resultJson) {
                showShortToast("请求异常，请检查网络");
                return;
            }
            nodeCountModel = JsonUtils.parseObject(resultJson, NodeCountModel.class);
            if (nodeCountModel != null &&nodeCountModel.getCode() == 200) {
                mPieChart.setCenterText("在线" + nodeCountModel.getData().getOffLineRate() +"%");
                tv_online.setText("在线数量 " + nodeCountModel.getData().getOffLineNum());
                tv_offline.setText("离线数量 " + (nodeCountModel.getData().getNodeNum()-nodeCountModel.getData().getOffLineNum()));

                ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
                entries.add(new PieEntry(nodeCountModel.getData().getOffLineRate(), "在线"));
                entries.add(new PieEntry(100 - nodeCountModel.getData().getOffLineRate(), "离线"));

                //饼状图设置数据
                setPieData(entries);
            }

        }else if(requestCode == ELECTRICITY_ENERGYALL_CODE){
            customDialog.dismiss();
            if (null == resultJson) {
                showShortToast("请求异常，请检查网络");
                return;
            }
            energyAllModel= JsonUtils.parseObject(resultJson, EnergyAllModel.class);
            if (energyAllModel != null && energyAllModel.getCode() == 200) {
                setenergyData(String.valueOf(energyAllModel.getData()));
            }
        }else if(requestCode == ELECTRICITY_EVERYMONTH_CODE){

        }else if(requestCode == ELECTRICITY_EVERYDAY_CODE){

        }else if(requestCode == ELECTRICITY_MONTH_CODE){
            customDialog.dismiss();
            if (null == resultJson) {
                showShortToast("请求异常，请检查网络");
                return;
            }

            JSONObject json = JSON.parseObject(resultJson);
            if (json.getInteger("code") == 200) {
                dateEnergyModelList.clear();

                JSONArray data = json.getJSONArray("data");
                JSONObject obj = data.getJSONObject(0);
                JSONObject datas = obj.getJSONObject("datas");

                Set<String> set = datas.keySet();
                for (String key : set) {
                    JSONObject jsonObject = datas.getJSONObject(key);
                    String energy = jsonObject.getString("electric_energy_2");
                    Log.i("这是我要的数据key=",key);
                    Log.i("这是我要的数据strint=",energy);
                    Log.i("jwjw","sdfjasf");

                    DateEnergyModel model = new DateEnergyModel();
                    model.setDate(key);
                    model.setEnergy(energy);
                    dateEnergyModelList.add(model);
                }

                myHandler.sendEmptyMessage(123);
            }
        }

    }

    //获取当月有多少天
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getMonthDay(Date date){
        month_day = dayByMonth(date);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int dayByMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;

        switch (month) {
            case 1: case 3: case 5:case 7:  case 8:  case 10:  case 12:
                return 31;
            case 4:  case 6: case 9:  case 11:
                return 30;
            //对于2月份需要判断是否为闰年
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }

            default:
                return 0;
        }
    }


    //加载提示
    public void customDialogShow(){
        if (null != customDialog) {
            customDialog.dismiss();
            customDialog = new CustomDialog(getActivity());
            customDialog.show();
        }
    }
    //设置总能耗
    public void setenergyData(String data){
        List<TextView> viewArray = new ArrayList<TextView>();
        viewArray.add(num8);
        viewArray.add(num7);
        viewArray.add(num6);
        viewArray.add(num5);
        viewArray.add(num4);
        viewArray.add(num3);
        viewArray.add(num2);
        viewArray.add(num1);

        char[] flag = data.toCharArray();
        int count = flag.length>8?8:flag.length;
        for(int i=0;i<count;i++){
            viewArray.get(i).setText(String.valueOf(flag[flag.length-i-1]));
        }

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

}
