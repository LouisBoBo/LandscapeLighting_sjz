package com.exc.applibrary.main.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.exc.applibrary.R;
import com.exc.applibrary.main.customview.BottomDialog;
import com.exc.applibrary.main.login.TaiYuanLoginActivity;
import com.exc.applibrary.main.login.WuHanLoginActivity;

public class CommonUtils {


    public static void exitLogin(int code, Activity mActivity) {
        if (code == 401) {
            PreferencesUtil.putString(mActivity, Constant.LOGIN_INFO_JSON, "");
            PreferencesUtil.putString(mActivity, Constant.USER_TOKEN, "");
            Intent intent = new Intent();
            intent.setAction("ACTION_EXIT_APP");
            mActivity.sendBroadcast(intent);


            String packageName = SystemUtils.getAppProcessName(mActivity);
            if(packageName.equals(PackageNameList.WuHan)){
                mActivity.startActivity(new Intent(mActivity, WuHanLoginActivity.class));

            }
            if(packageName.equals(PackageNameList.TaiYaun)){
                mActivity.startActivity(new Intent(mActivity, TaiYuanLoginActivity.class));

            }

        }
    }


    public static void textLinkClick(final Activity activity, String html, TextView tv) {
        CharSequence charSequence = Html.fromHtml(html);
        tv.setText(charSequence);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence text = tv.getText();
        Spannable sp = (Spannable) text;
        URLSpan[] urlSpans = sp.getSpans(0, text.length(), URLSpan.class);
        SpannableStringBuilder builder = new SpannableStringBuilder(
                charSequence);
        builder.clearSpans();
        for (URLSpan span : urlSpans) {
            int flag = builder.getSpanFlags(span);
            final String link = span.getURL();
            builder.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    // 捕获<a>标签点击事件，及对应超链接link
//                    Toast.makeText(context, link, Toast.LENGTH_SHORT).show();
                    Uri uri = Uri.parse(link);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    activity.startActivity(intent);

                }
            }, sp.getSpanStart(span), sp.getSpanEnd(span), flag);
            builder.removeSpan(span);
        }
        tv.setText(builder);
    }

    public static void getPicPathFromDialog(Activity mActivity, ChoosePicPathListener choosePicPathListener) {
        BottomDialog.Builder builder = new BottomDialog.Builder(mActivity);
        builder.setContentView(R.layout.dialog_choose_phone_pic);
        BottomDialog bottomDialog = builder.create();
        bottomDialog.setCanceledOnTouchOutside(false);
        View bottomView = bottomDialog.getContentView();


        ImageView iv_camera = bottomView.findViewById(R.id.iv_camera);
        ImageView iv_gallery = bottomView.findViewById(R.id.iv_gallery);
        ImageView iv_file = bottomView.findViewById(R.id.iv_file);

        TextView tv_camera = bottomView.findViewById(R.id.tv_camera);
        TextView tv_gallery = bottomView.findViewById(R.id.tv_gallery);
        TextView tv_file = bottomView.findViewById(R.id.tv_file);

        //获取系统摄像头信息
//        PackageManager packageManager = mActivity.getPackageManager();
//        try {
//            PackageInfo packageInfo = packageManager.getPackageInfo("com.meizu.media.camera", 0);
//            String AppName = packageInfo.applicationInfo.loadLabel(packageManager).toString();
//            Drawable icon = packageInfo.applicationInfo.loadIcon(packageManager);
//            String packageName = packageInfo.packageName;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }


        iv_camera.setOnClickListener(v -> {

        });
        tv_gallery.setOnClickListener(v -> {

        });
        tv_file.setOnClickListener(v -> {

        });


        bottomView.findViewById(R.id.tv_cancel).setOnClickListener(v -> {
            bottomDialog.dismiss();
        });


        bottomDialog.show();

    }

    public interface ChoosePicPathListener {
        void callBackPicPath(String picPath);
    }


}
