package com.exc.applibrary.main.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.exc.applibrary.R;
import com.exc.applibrary.databinding.ActivityDataCenterBinding;
import com.exc.applibrary.main.HttpRequest;
import com.exc.applibrary.main.customview.CustomDialog;
import com.exc.applibrary.main.model.ElectricityNodeModel;
import com.exc.applibrary.main.model.EnergyDaysModel;
import com.exc.applibrary.main.model.OnlineDaysModel;
import com.exc.applibrary.main.model.PartitionHomeModel;
import com.exc.applibrary.main.model.PatitionFindListModel;
import com.exc.applibrary.main.model.SelectBuildModel;
import com.exc.applibrary.main.model.TypeRealyModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.interfaces.OnHttpResponseListener;
import zuo.biao.library.util.JsonUtils;
import zuo.biao.library.util.Log;

import static com.github.mikephil.charting.animation.Easing.*;
import static com.github.mikephil.charting.animation.Easing.EasingOption.EaseInOutQuad;

public class DataCenterActivity extends BaseActivity implements OnHttpResponseListener, OnChartValueSelectedListener,
        OnChartGestureListener {

    private ActivityDataCenterBinding binding;
    private Spinner spinnerItems;
    private ArrayAdapter<String> adapter;
    private CustomDialog customDialog;

    private PieChart mPieChart;
    private LineChart mLineChar;
    private XAxis xAxis;
    private LineDataSet set1;
    private ArrayList<String> dateList;
    private List<EnergyDaysModel.DataBean.ListBean> energylistBeanList;
    private List<OnlineDaysModel.DataBean.ListBean> onlinelistBeanList;
    private int daycount;//近几天
    private int select_partitionId;//分区ID
    private Boolean select_online;//是否选择的是建筑在线 否则是能耗数据

    private EnergyDaysModel energyDaysModel;
    private OnlineDaysModel onlineDaysModel;
    private PartitionHomeModel partitionHomeModel;

    private final int ENERGYDAYS_REQUEST_CODE = 1;
    private final int PARTITION_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataCenterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        select_online = true;//默认建筑在线
        daycount = 6;//默认近7天的数据
        select_partitionId = 12;//默认武昌区
        binding.partitionTitile.setText("武昌区");

        binding.mPieChart.setNoDataText("");
        binding.mLineChar.setNoDataText("暂无数据");

        initView();
        initEvent();
        initPartitionData();
    }

    @Override
    public void onHttpResponse(int requestCode, String resultJson, Exception e) {
        if(requestCode == ENERGYDAYS_REQUEST_CODE){
            customDialog.dismiss();
            if (null == resultJson) {
                showShortToast("请求异常，请检查网络");
                return;
            }

            if(select_online){
                onlineDaysModel = JsonUtils.parseObject(resultJson, OnlineDaysModel.class);
                if(onlineDaysModel.getCode() == 200){
                    if(onlineDaysModel.getData().getList().size() >0){
                        onlinelistBeanList.addAll(onlineDaysModel.getData().getList());

                        if(onlinelistBeanList.size()>0){
                            initLineChart(daycount);
                        }
                    }
                }
            }else {
                energyDaysModel = JsonUtils.parseObject(resultJson, EnergyDaysModel.class);
                if(energyDaysModel.getCode() == 200){
                    if(energyDaysModel.getData().getList().size() >0){
                        energylistBeanList.addAll(energyDaysModel.getData().getList());

                        if(energylistBeanList.size()>0){
                            initLineChart(daycount);
                        }
                    }
                }
            }

        }else if(requestCode == PARTITION_REQUEST_CODE){
            customDialog.dismiss();
            if (null == resultJson) {
                showShortToast("请求异常，请检查网络");
                return;
            }

            partitionHomeModel = JsonUtils.parseObject(resultJson,PartitionHomeModel.class);
            if(partitionHomeModel.getCode() ==200){
                refreshUI();
            }
        }
    }

    @Override
    public void initView() {

        customDialog = new CustomDialog(getActivity());
        binding.headView.setImg_right(R.mipmap.icon_searchblue);
        binding.headView.img_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataCenterActivity.this.startActivityForResult(new Intent(DataCenterActivity.this, PartitionListActivity.class), 0);
            }
        });

        binding.buingLine.setSelected(true);
        binding.buingLine.setTextColor(Color.WHITE);
        binding.buingLine.setBackgroundResource(R.drawable.login_btn_login);

        binding.buingLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.buingLine.setSelected(true);
                binding.buingLine.setTextColor(Color.WHITE);
                binding.buingLine.setBackgroundResource(R.drawable.login_btn_login);

                binding.energyData.setSelected(false);
                binding.energyData.setTextColor(Color.DKGRAY);
                binding.energyData.setBackgroundResource(R.drawable.background_solid_gray_shape);

                binding.markTitle.setText("历史在线率");
                initData();
            }
        });

        binding.energyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.energyData.setSelected(true);
                binding.energyData.setTextColor(Color.WHITE);
                binding.energyData.setBackgroundResource(R.drawable.login_btn_login);

                binding.buingLine.setSelected(false);
                binding.buingLine.setTextColor(Color.DKGRAY);
                binding.buingLine.setBackgroundResource(R.drawable.background_solid_gray_shape);

                binding.markTitle.setText("能耗KW/h");
                initData();
            }
        });

        initspinnerView();
    }

    //刷新主界面
    public void refreshUI(){
        //在线率 离线率
        initPieChart(partitionHomeModel.getData().getOnlineRate(),partitionHomeModel.getData().getOfflineRate());

        binding.totalNum.setText(String.valueOf(partitionHomeModel.getData().getTotalNum()));
        binding.onlineNum.setText(String.valueOf(partitionHomeModel.getData().getOnlineNum()));
        binding.offlineNum.setText(String.valueOf(partitionHomeModel.getData().getOfflineNum()));

        //总能耗
        setenergyData(String.valueOf(partitionHomeModel.getData().getTotalEnergy()));
    }

    //日期下拉选择框
    public void initspinnerView() {
        final String[] color = {"近7天", "近14天", "近30天"};
        spinnerItems = findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, color);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerItems.setAdapter(adapter);

        //第五步：添加监听器，为下拉列表设置事件的响应
        spinnerItems.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView tv = (TextView) view;
                tv.setTextColor(getResources().getColor(R.color.black_slight)); //设置颜色
                tv.setTextSize(14.0f); //设置大小

                view.setVisibility(View.VISIBLE);

                if(position == 0){
                    daycount = 6;
                }else if(position == 1){
                    daycount = 13;
                }else if(position == 2){
                    daycount = 29;
                }

                initData();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("spinner", "Spinner Touch事件被触发!");
            }

        });

        //将spinnertext添加到OnTouchListener对内容选项触屏事件处理
        spinnerItems.setOnTouchListener(new Spinner.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setVisibility(View.VISIBLE);
                Log.i("spinner", "Spinner Touch事件被触发!");
                return false;
            }
        });

        //焦点改变事件处理
        spinnerItems.setOnFocusChangeListener(new Spinner.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                v.setVisibility(View.VISIBLE);
                Log.i("spinner", "Spinner FocusChange事件被触发！");
            }
        });
    }

    //折线图
    public void initLineChart(int daycont) {

        ArrayList<Entry> values = binding.buingLine.isSelected()?onlineData(daycont) : eneygylineData(daycont);

        mLineChar = (LineChart) findViewById(R.id.mLineChar);

        //设置手势滑动事件
        mLineChar.setOnChartGestureListener(this);
        //设置数值选择监听
        mLineChar.setOnChartValueSelectedListener(this);
        //后台绘制
        mLineChar.setDrawGridBackground(false);
        //设置描述文本
        mLineChar.getDescription().setEnabled(false);
        //设置支持触控手势
        mLineChar.setTouchEnabled(true);
        //设置缩放
        mLineChar.setDragEnabled(true);
        //设置推动
        mLineChar.setScaleEnabled(true);
        //如果禁用,扩展可以在x轴和y轴分别完成
        mLineChar.setPinchZoom(true);
        //重设所有缩放和拖动，使图表完全适合它的边界（完全缩小）。
        mLineChar.fitScreen();

        xAxis = mLineChar.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴位置
        xAxis.setDrawGridLines(false);//不绘制网格线
        xAxis.setTextSize(8.0f);//设置轴标签的文字大小。
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int i = (int)value;
                if(i<=dateList.size()){
                    return dateList.get(i-1);
                }else
                    return "";
            }
        });

        YAxis leftYAxis = mLineChar.getAxisLeft();
        leftYAxis.setAxisMinimum(0f);

        YAxis axisRight = mLineChar.getAxisRight();
        axisRight.setEnabled(false);//隐藏右轴  默认显示

        //设置数据
        setLineData(values);

        //设置当前窗口只展示固定的坐标点个数
        mLineChar.setVisibleXRangeMaximum(6);
        //默认动画
        mLineChar.animateX(2500);
        //刷新
        mLineChar.invalidate();

        // 得到这个文字
        Legend l = mLineChar.getLegend();
        // 修改文字 ...
        l.setForm(Legend.LegendForm.NONE);
    }

    //传递数据集
    private void setLineData(ArrayList<Entry> values) {

        if (mLineChar.getData() != null && mLineChar.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mLineChar.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mLineChar.getData().notifyDataChanged();
            mLineChar.notifyDataSetChanged();
            //刷新
            mLineChar.invalidate();
        } else {
            // 创建一个数据集,并给它一个类型
            set1 = new LineDataSet(values, "");

            // 在这里设置线
            set1.enableDashedLine(10f, 0f, 0f);
            set1.enableDashedHighlightLine(10f, 0f, 0f);
            set1.setColor(0xA30000FF);
            set1.setCircleColor(0xA30000FF);
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(false);
            set1.setFormLineWidth(0f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 0f}, 0f));
            set1.setFormSize(15.f);
            set1.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                    if (entry.getY()==v && binding.buingLine.isSelected()){
                        return Math.round(v*100)+"%";
                    }else if(binding.energyData.isSelected()) {
                        return v+"";
                    }
                    return "";
                }
            });

            if (Utils.getSDKInt() >= 18) {
                // 填充背景只支持18以上
                //Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.ic_launcher);
                //set1.setFillDrawable(drawable);
                set1.setFillColor(R.color.alph);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            //添加数据集
            dataSets.add(set1);

            //创建一个数据集的数据对象
            LineData data = new LineData(dataSets);
            //设置数据
            mLineChar.setData(data);
        }
    }

    //饼状图
    public void initPieChart(int online_rate , int offline_rate) {

        mPieChart = (PieChart) findViewById(R.id.mPieChart);
        mPieChart.setUsePercentValues(true);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setExtraOffsets(0, -3, 8, 0);

        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        //设置中间文件
        mPieChart.setCenterText("");
        mPieChart.setDrawHoleEnabled(true);
        mPieChart.setHoleColor(Color.WHITE);

        mPieChart.setTransparentCircleColor(Color.YELLOW);
        mPieChart.setTransparentCircleAlpha(110);

        mPieChart.setHoleRadius(58f);
        mPieChart.setTransparentCircleRadius(61f);

        mPieChart.setDrawCenterText(false);

        mPieChart.setRotationAngle(0);
        // 触摸旋转
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);
        mPieChart.setDrawHoleEnabled(false);

        //变化监听
        mPieChart.setOnChartValueSelectedListener(this);
        //模拟数据
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(online_rate, "在线"));
        entries.add(new PieEntry(offline_rate, "离线"));

        //设置数据
        setPieData(entries);
        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = mPieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);

        // 输入标签样式
        mPieChart.setEntryLabelColor(Color.WHITE);
        mPieChart.setEntryLabelTextSize(10f);
    }

    private void setPieData(ArrayList<PieEntry> entries) {
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(1f);
        dataSet.setSelectionShift(5f);

        int[] VORDIPLOM_COLORS = {
                Color.rgb(0, 255, 127), Color.rgb(255, 130, 71)
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
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        mPieChart.setData(data);
        mPieChart.highlightValues(null);
        //刷新
        mPieChart.invalidate();
    }

    @Override
    public void initData() {
        energylistBeanList = new ArrayList<>();
        onlinelistBeanList = new ArrayList<>();
//        customDialogShow();
        select_online = binding.buingLine.isSelected()?true:false;
//        HttpRequest.energyenergySevenDaysHttp(select_partitionId,daycount+1,select_online,ENERGYDAYS_REQUEST_CODE,this::onHttpResponse);
    }
    public void initPartitionData(){
        HttpRequest.partitionHomeHttp(select_partitionId,PARTITION_REQUEST_CODE,this::onHttpResponse);
    }
    //设置在线率数据
    public ArrayList<Entry> onlineData(int daycount){
        dateList = new ArrayList<String>();
        dateList = getDateList(daycount);
        //这里我模拟一些数据
        ArrayList<Entry> values = new ArrayList<Entry>();
        for (int i = onlinelistBeanList.get(0).getHistoryData().size()-1; i >=0; i--) {
            float value = (float) onlinelistBeanList.get(0).getHistoryData().get(i).getOnlineRate();
            values.add(new Entry(onlinelistBeanList.get(0).getHistoryData().size()-i, value));
        }
        return values;
    }
    //设置能耗数据
    public ArrayList<Entry> eneygylineData(int daycount){
        dateList = new ArrayList<String>();
        dateList = getDateList(daycount);
        //这里我模拟一些数据
        ArrayList<Entry> values = new ArrayList<Entry>();
        for (int i = energylistBeanList.get(0).getHistoryData().size()-1; i >=0; i--) {
            float value = (float) energylistBeanList.get(0).getHistoryData().get(i).getElectricEnergy1();
            values.add(new Entry(energylistBeanList.get(0).getHistoryData().size()-i, value));
        }
        return values;
    }
    //设置总能耗
    public void setenergyData(String data){
        List<TextView> viewArray = new ArrayList<TextView>();
        viewArray.add(binding.num8);
        viewArray.add(binding.num7);
        viewArray.add(binding.num6);
        viewArray.add(binding.num5);
        viewArray.add(binding.num4);
        viewArray.add(binding.num3);
        viewArray.add(binding.num2);
        viewArray.add(binding.num1);

        char[] flag = data.toCharArray();
        int count = flag.length>8?8:flag.length;
        for(int i=0;i<count;i++){
            viewArray.get(i).setText(String.valueOf(flag[flag.length-i-1]));
        }
    }
    //获取前几天的数据
    private ArrayList<String> getDateList(int day) {
        //创建集合储存日期
        ArrayList<String> dateList = new ArrayList<>();
//        //获取当前日期
//        SimpleDateFormat sdf = new SimpleDateFormat("M月dd日");
//        String format = sdf.format(new Date().getTime());
//        //此处是我项目的需求，需要保存当前日期在第一位，你也可以根据自己的需求自行决定
//        dateList.add(format);
//        for (int i = 0; i < day;i++){
//            // 将当前的日期转为Date类型，ParsePosition(0)表示从第一个字符开始解析
//            Date date = sdf.parse(format, new ParsePosition(0));
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(date);
//            // add方法的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天，你可根据自己的需求自行决定,
//            //如果项目中需要多次调用，你也可把这个参数，通过方法动态传入
//            calendar.add(Calendar.DATE, -1);
//            Date date1 = calendar.getTime();
//            format = sdf.format(date1);
//            dateList.add(format);
//        }

        if(select_online){
            for (int i = onlinelistBeanList.get(0).getHistoryData().size()-1; i >=0; i--) {
                String time = onlinelistBeanList.get(0).getHistoryData().get(i).getTime();
                time = time.substring(5,time.length());
                dateList.add(time);
            }
        }else {
            for (int i = energylistBeanList.get(0).getHistoryData().size()-1; i >=0; i--) {
                String time = energylistBeanList.get(0).getHistoryData().get(i).getTime();
                time = time.substring(5,time.length());
                dateList.add(time);
            }
        }


        return dateList;
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (resultCode == 100) {//选择分区返回

                PatitionFindListModel.DataBean.PartitionListBean bean = (PatitionFindListModel.DataBean.PartitionListBean) data.getExtras().getSerializable("result");
                if (bean != null) {

                    binding.partitionTitile.setText(bean.getName());
                    select_partitionId = bean.getId();
                    Log.d("选择的分区", bean.getName() + bean.getId());
                    if(select_partitionId >0){
                        initData();
                        initPartitionData();
                    }
                }
            }
        }
    }

    //加载提示
    public void customDialogShow(){
        if (null != customDialog) {
            customDialog.dismiss();
            customDialog = new CustomDialog(this);
            customDialog.show();
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

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
}
