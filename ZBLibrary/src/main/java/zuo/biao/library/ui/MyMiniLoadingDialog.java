package zuo.biao.library.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.StyleRes;

import com.xuexiang.xui.widget.dialog.BaseDialog;
import com.xuexiang.xui.widget.progress.loading.IMessageLoader;
import com.xuexiang.xui.widget.progress.loading.LoadingCancelListener;

import zuo.biao.library.R;

/**
 * 迷你loading加载
 *
 * @author XUE
 * @since 2019/4/9 14:16
 */
public class MyMiniLoadingDialog extends BaseDialog implements IMessageLoader {

    private MyMiniLoadingView mLoadingView;

    private LoadingCancelListener mLoadingCancelListener;
    private Context mContext;

    public MyMiniLoadingDialog(Context context) {
        super(context, R.style.XUIDialog_Custom_MiniLoading, R.layout.my_xui_dialog_loading_mini);
        this.mContext = context;
        initView(getString(R.string.xui_tip_loading_message));
    }

    public MyMiniLoadingDialog(Context context, String tipMessage) {
        super(context, R.style.XUIDialog_Custom_MiniLoading, R.layout.my_xui_dialog_loading_mini);
        this.mContext = context;

        initView(tipMessage);
    }

    public MyMiniLoadingDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId, R.layout.xui_dialog_loading_mini);
        this.mContext = context;
        initView(getString(R.string.xui_tip_loading_message));
    }

    public MyMiniLoadingDialog(Context context, @StyleRes int themeResId, String tipMessage) {
        super(context, themeResId, R.layout.my_xui_dialog_loading_mini);
        initView(tipMessage);
    }

    private void initView(String tipMessage) {
        mLoadingView = findViewById(R.id.mini_loading_view);

        updateMessage(tipMessage);

        setCancelable(false);
        setCanceledOnTouchOutside(false);

    }


    /**
     * 更新提示信息
     *
     * @param tipMessage
     * @return
     */
    @Override
    public void updateMessage(String tipMessage) {
    }

    /**
     * 更新提示信息
     *
     * @param tipMessageId
     * @return
     */
    @Override
    public void updateMessage(int tipMessageId) {
        updateMessage(getString(tipMessageId));
    }


    /**
     * 显示加载
     */
    @Override
    public void show() {
        super.show();
        if (mLoadingView != null) {
            mLoadingView.start();
        }
    }

    /**
     * 隐藏加载
     */
    @Override
    public void dismiss() {
        ((Activity) mContext).runOnUiThread(() -> {
            if (mLoadingView != null) {
                mLoadingView.stop();
            }
            super.dismiss();

        });
    }

    /**
     * 资源释放
     */
    @Override
    public void recycle() {

    }

    /**
     * 是否在加载
     *
     * @return
     */
    @Override
    public boolean isLoading() {
        return isShowing();
    }

    /**
     * 设置是否可取消
     *
     * @param flag
     */
    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
        if (flag) {
            setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    if (mLoadingCancelListener != null) {
                        mLoadingCancelListener.onCancelLoading();
                    }
                }
            });
        }
    }

    /**
     * 设置取消的回掉监听
     *
     * @param listener
     */
    @Override
    public void setLoadingCancelListener(LoadingCancelListener listener) {
        mLoadingCancelListener = listener;
    }
}
