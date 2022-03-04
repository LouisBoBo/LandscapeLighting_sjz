package com.exc.applibrary.main.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.exc.applibrary.R;
import com.exc.applibrary.main.ui.activity.LoopControlActivity;

import zuo.biao.library.util.StringUtil;

public class LoopConrolDialog extends Dialog{

    private Context context;
    private TextView tv_loopname;
    private TextView tv_loopstyle;
    private TextView tv_confirm;
    private TextView tv_cancle;

    private int requestCode;
    private String loop_name;
    private String loop_style;
    private OnDialogConfirmClickListener listener;
    private onSelectControlListener controlListener;

    public interface OnDialogConfirmClickListener {
        /**点击item事件的回调方法
         * @param requestCode 传入的用于区分某种情况下的showDialog
         */
        void onDialogConfirmClick(int requestCode);
    }

    public interface onSelectControlListener{
        void onSelectControl();
    }
    /**
     * 带监听器参数的构造函数
     */
    public LoopConrolDialog(Context context,int requestCode,String loop_name,String loop_style,OnDialogConfirmClickListener listener,onSelectControlListener controlListener) {
        super(context, zuo.biao.library.R.style.MyDialog);

        this.context = context;
        this.requestCode = requestCode;
        this.loop_name = loop_name;
        this.loop_style = loop_style;
        this.listener = listener;
        this.controlListener = controlListener;
    }
    public void refreshUI(String loop_style){
        tv_loopstyle.setText(loop_style);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop_control_dialog);
        setCanceledOnTouchOutside(true);

        tv_loopname = findViewById(R.id.loop_name_text);
        tv_loopstyle = findViewById(R.id.loop_style_text);
        tv_confirm = findViewById(R.id.loop_confirm);
        tv_cancle = findViewById(R.id.loop_cancle);

        tv_loopname.setText(loop_name);
        tv_loopstyle.setText(loop_style);

        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.share_animation);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT); //设置横向全屏

        tv_loopstyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlListener.onSelectControl();
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDialogConfirmClick(requestCode);
                dismiss();
            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}

