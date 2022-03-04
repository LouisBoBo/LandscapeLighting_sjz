package com.exc.applibrary.main.monitoring;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.exc.applibrary.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import zuo.biao.library.util.StringUtil;

public class MonitoringVideoPlayer extends StandardGSYVideoPlayer {
    public MonitoringVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public MonitoringVideoPlayer(Context context) {
        super(context);
    }

    public MonitoringVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrValues(context,attrs);
    }

    private void initAttrValues(Context context, AttributeSet attrs) {
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShowListVideoPlayer);
//        boolean isShowSwitchBTN = typedArray.getBoolean(R.styleable.ShowListVideoPlayer_showSwitchBTN,true);
    }


    @Override
    protected void init(Context context) {
        super.init(context);
    }
    @Override
    public int getLayoutId() {
        return R.layout.monitoring_video_layout;
    }



    //旋转播放角度




}
