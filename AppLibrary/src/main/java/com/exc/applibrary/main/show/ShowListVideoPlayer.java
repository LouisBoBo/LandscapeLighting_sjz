package com.exc.applibrary.main.show;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.exc.applibrary.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;

import zuo.biao.library.util.StringUtil;

public class ShowListVideoPlayer extends StandardGSYVideoPlayer {
    public ShowListVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public ShowListVideoPlayer(Context context) {
        super(context);
    }

    public ShowListVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrValues(context,attrs);
    }

    private void initAttrValues(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShowListVideoPlayer);
        boolean isShowSwitchBTN = typedArray.getBoolean(R.styleable.ShowListVideoPlayer_showSwitchBTN,true);

        if(!isShowSwitchBTN){
            iv_switch.setVisibility(GONE);
        }
    }

    public TextView show_title;
    public ImageView iv_switch;

    @Override
    protected void init(Context context) {
        super.init(context);

        show_title = findViewById(R.id.show_title);
        iv_switch = findViewById(R.id.iv_switch);






    }


    @Override
    public int getLayoutId() {
        return R.layout.show_list_video_layout;
    }

    public void setTitle(String title) {
        if (StringUtil.isEmpty(title)) {
            show_title.setVisibility(GONE);
            return;
        }
        show_title.setText(title);
        iv_switch.setOnClickListener(v -> {
            switchBTNOnClickListener.onSwitchClick();
        });

    }
    SwitchBTNOnClickListener switchBTNOnClickListener;
    public void setOnSwitchBtnClickListener(SwitchBTNOnClickListener btnOnClickListener) {
        this.switchBTNOnClickListener = btnOnClickListener;
    }
    public interface SwitchBTNOnClickListener {
        void onSwitchClick();
    }
}
