package com.exc.applibrary.main.ui.fragment;

import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
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

import com.exc.applibrary.R;
import com.exc.applibrary.main.HttpRequest;
import com.exc.applibrary.main.customview.CustomDialog;
import com.exc.applibrary.main.model.NodeCountModel;
import com.exc.applibrary.main.model.NodeLightModel;
import com.exc.applibrary.main.model.NodeWeekLightModel;
import com.exc.applibrary.main.model.WeakNodeCount;
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
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

import zuo.biao.library.base.BaseFragment;
import zuo.biao.library.interfaces.OnHttpResponseListener;
import zuo.biao.library.util.JsonUtils;
import zuo.biao.library.util.Log;

public class WeakCurrentFragment extends BaseFragment implements OnHttpResponseListener, OnChartValueSelectedListener,
        OnChartGestureListener {
    private PieChart mPieChart;
    private BarChart mBarChart;
    private BarData mBarData;
    private Spinner yearspinner;
    private Spinner monthspinner;
    private ArrayAdapter<String> adapter;
    private TextView tv_online;
    private TextView tv_offline;

    private CustomDialog customDialog;

    private final int ELECTRICITY_NODECOUNT_CODE = 1;
    private final int ELECTRICITY_NODELIGHT_CODE = 2;
    private final int WEEK_NODELIGHT_CODE = 3;

    private WeakNodeCount nodeCountModel;
    private NodeLightModel nodeLightModel;
    private NodeWeekLightModel nodeWeekLightModel;

    private XAxis xAxis;
    private YAxis yAxis;
    private int labelCount;
    private String year;
    private String month;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater .inflate(R.layout.fragment_weak_current ,container,false) ;
        mPieChart = view.findViewById(R.id.mPieChart);
        mBarChart = view.findViewById(R.id.mbarChart);
        yearspinner = view.findViewById(R.id.yrar_spinner);
        monthspinner = view.findViewById(R.id.month_spinner);
        tv_online = view.findViewById(R.id.tv_online);
        tv_offline = view.findViewById(R.id.tv_offline);
        initView();
        return view;
    }

    //?????????
    public void initNewPieChart(){
        mPieChart.setUsePercentValues(true);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setExtraOffsets(5, 10, 5, 5);

        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        //??????????????????
        mPieChart.setCenterText("??????0%");

        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setHoleColor(Color.WHITE);

        mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(110);

        mPieChart.setHoleRadius(58f);
        mPieChart.setTransparentCircleRadius(61f);

        mPieChart.setDrawCenterText(true);

        mPieChart.setRotationAngle(0);
        // ????????????
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);

        //????????????
        mPieChart.setOnChartValueSelectedListener(this);
        //????????????
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(0, "??????"));
        entries.add(new PieEntry(100, "??????"));


        //????????????
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

        // ??????????????????
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

        //???????????????
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
        //??????
        mPieChart.invalidate();
    }

    //?????????
    public void initBarchart(){
        //????????????????????????????????????????????????????????????
        mBarChart.setOnChartValueSelectedListener(this);
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawValueAboveBar(true);
        mBarChart.getDescription().setEnabled(false);
        // ??????60???????????????????????????,drawn?????????
        mBarChart.setMaxVisibleValueCount(60);
        // ???????????????????????????x??????y???
        mBarChart.setPinchZoom(false);
        //????????????????????????
        mBarChart.setDrawGridBackground(false);
        //?????????????????????
        mBarChart.getLegend().setEnabled(false);

        //????????????
        mBarChart.animateX(2500);
        //????????????????????????
        mBarChart.setTouchEnabled(true);
        //????????????????????????
        mBarChart.setDragEnabled(true);
        mBarChart.setScaleEnabled(false);//????????????????????????

        float ratio = (float) labelCount/12;
        //??????????????????????????????????????????????????????1f?????????????????????
        mBarChart.zoom(ratio,1f,0,0);

        //????????????????????????
        xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(7);
        xAxis.setLabelRotationAngle(-60);

        yAxis = mBarChart.getAxisLeft();
        yAxis.setLabelCount(8, false);
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        yAxis.setSpaceTop(15f);
        //????????????setStartAtZero(true)
        yAxis.setAxisMinimum(0f);

        YAxis rightAxis = mBarChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(8, false);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f);

        // ?????? ?????? y ???
        YAxis mRAxis = mBarChart.getAxisRight();
        // ?????? ?????? Y ???
        mRAxis.setEnabled(false);
        // ?????? ?????? Y???
        YAxis mLAxis = mBarChart.getAxisLeft();
        // ?????? ?????? Y ???
        mLAxis.setEnabled(true);
        // ?????? ?????? Y??? ?????????
        mLAxis.setDrawAxisLine(false);
        // ?????? ?????? ?????????
        mLAxis.setDrawGridLines(false);



        //????????????
        ArrayList<BarEntry> yValues = new ArrayList<>();
        for (int x = 1; x < 7 +1; x++) {
            // 2.0 ----xValues.add(String.valueOf(i));
            float y = 0;

            yValues.add(new BarEntry(x, y));
        }
        setmBarData(yValues);
    }

    public void setmBarData(ArrayList<BarEntry> yValues){

        // y ????????????
        BarDataSet barDataSet = new BarDataSet(yValues, "?????????");
        barDataSet.setColor(getResources().getColor(R.color.blue_btn));

        barDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                return Math.round(v)+"%";
            }
        });

        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return Math.round(value)+"%";
            }
        });

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int i = (int)value;
                if(i<=yValues.size() && nodeWeekLightModel != null){
                    String date = String.valueOf(nodeWeekLightModel.getData().getDates().get(i-1));
                    return year + "-" + date;
                }else
                    return "";
            }
        });

        // 2.0 ---- mBarData = new BarData(xValues, barDataSet);
        mBarData = new BarData(barDataSet);

        mBarChart.setData(mBarData);

        mBarChart.getViewPortHandler().setMaximumScaleX(yValues.size()/7);

        mBarChart.invalidate();
    }

    //???????????????
    public void inityearspinnerView() {
        final String[] color = {"2021", "2020", "2019"};
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, color);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearspinner.setAdapter(adapter);
        year = color[0];

        //??????????????????????????????????????????????????????????????????
        yearspinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView tv = (TextView) view;
                tv.setTextColor(getResources().getColor(R.color.black_slight)); //????????????
                tv.setTextSize(14.0f); //????????????

                view.setVisibility(View.VISIBLE);

                year = color[position];

                customDialogShow();
                HttpRequest.nodeweekLightHttp(WEEK_NODELIGHT_CODE,WeakCurrentFragment.this::onHttpResponse);
                HttpRequest.nodeLightHttp(year + "-" + (month.length()>1?month:("0"+month)),0,ELECTRICITY_NODELIGHT_CODE,WeakCurrentFragment.this::onHttpResponse);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("spinner", "Spinner Touch???????????????!");
            }

        });

        //???spinnertext?????????OnTouchListener?????????????????????????????????
        yearspinner.setOnTouchListener(new Spinner.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setVisibility(View.VISIBLE);
                Log.i("spinner", "Spinner Touch???????????????!");
                return false;
            }
        });

        //????????????????????????
        yearspinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                v.setVisibility(View.VISIBLE);
                Log.i("spinner", "Spinner FocusChange??????????????????");
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

        monthspinner.setSelection(DateUtil.getMonth()-1,true);

        //??????????????????????????????????????????????????????????????????
        monthspinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView tv = (TextView) view;
                tv.setTextColor(getResources().getColor(R.color.black_slight)); //????????????
                tv.setTextSize(14.0f); //????????????

                view.setVisibility(View.VISIBLE);

                month = color[position];

                customDialogShow();
                HttpRequest.nodeweekLightHttp(WEEK_NODELIGHT_CODE,WeakCurrentFragment.this::onHttpResponse);
                HttpRequest.nodeLightHttp(year + "-" + (month.length()>1?month:("0"+month)),0,ELECTRICITY_NODELIGHT_CODE,WeakCurrentFragment.this::onHttpResponse);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("spinner", "Spinner Touch???????????????!");
            }

        });

        //???spinnertext?????????OnTouchListener?????????????????????????????????
        monthspinner.setOnTouchListener(new Spinner.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setVisibility(View.VISIBLE);
                Log.i("spinner", "Spinner Touch???????????????!");
                return false;
            }
        });

        //????????????????????????
        monthspinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                v.setVisibility(View.VISIBLE);
                Log.i("spinner", "Spinner FocusChange??????????????????");
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void initView() {
        customDialog = new CustomDialog(getActivity());

        labelCount = 31;

        initNewPieChart();
        inityearspinnerView();
        initmonthspinnerView();
        initBarchart();
        initData();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void initData() {
        customDialogShow();
        HttpRequest.nodeCountHttp(ELECTRICITY_NODECOUNT_CODE,this::onHttpResponse);
        HttpRequest.nodeweekLightHttp(WEEK_NODELIGHT_CODE,this::onHttpResponse);
//        HttpRequest.nodeLightHttp(year + "-" + (month.length()>1?month:("0"+month)),0,ELECTRICITY_NODELIGHT_CODE,this::onHttpResponse);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onHttpResponse(int requestCode, String resultJson, Exception e) {
        if (requestCode == ELECTRICITY_NODECOUNT_CODE) {
            customDialog.dismiss();
            if (null == resultJson) {
                showShortToast("??????????????????????????????");
                return;
            }
            nodeCountModel = JsonUtils.parseObject(resultJson, WeakNodeCount.class);
            if (nodeCountModel != null && nodeCountModel.getCode() == 200) {
                if(nodeCountModel.getData().getOnLine() != 0 && nodeCountModel.getData().getCount() !=0){
                    double scale = new BigDecimal((float)nodeCountModel.getData().getOnLine()/(double)nodeCountModel.getData().getCount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    int rate = (int) (scale*100);
                    mPieChart.setCenterText("??????" + String.valueOf(rate) +"%");
                }
                tv_online.setText("???????????? " + nodeCountModel.getData().getOnLine());
                tv_offline.setText("???????????? " + nodeCountModel.getData().getOffLine());

                ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
                entries.add(new PieEntry(nodeCountModel.getData().getOnLine(), "??????"));
                entries.add(new PieEntry(nodeCountModel.getData().getOffLine(), "??????"));

                //?????????????????????
                setPieData(entries);
            }
        }else if(requestCode == ELECTRICITY_NODELIGHT_CODE){
            customDialog.dismiss();
            if (null == resultJson) {
                showShortToast("??????????????????????????????");
                return;
            }
            nodeLightModel = JsonUtils.parseObject(resultJson, NodeLightModel.class);
            if(nodeLightModel != null && nodeLightModel.getCode() == 200){
                setLightDayData();
            }
        }else if(requestCode == WEEK_NODELIGHT_CODE){
            customDialog.dismiss();
            if (null == resultJson) {
                showShortToast("??????????????????????????????");
                return;
            }

            nodeWeekLightModel = JsonUtils.parseObject(resultJson, NodeWeekLightModel.class);
            if(nodeWeekLightModel != null && nodeWeekLightModel.getCode() == 200){
                setLightDayData();
            }
        }
    }

    //???????????????
    public void setLightDayData(){
        // y ?????????
        ArrayList<BarEntry> yValues = new ArrayList<>();
        // 2.0 ----x ?????????
        for (int x = 0; x < nodeWeekLightModel.getData().getDates().size(); x++) {
            // 2.0 ----xValues.add(String.valueOf(i));
            float y = 0;
            y = nodeWeekLightModel.getData().getNumbers().get(x);
            yValues.add(new BarEntry(x+1, y));
        }

        setmBarData(yValues);
    }

    //????????????
    public void customDialogShow(){
        if (null != customDialog) {
            customDialog.dismiss();
            customDialog = new CustomDialog(getActivity());
            customDialog.show();
        }
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
