package com.exc.applibrary.main.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.exc.applibrary.R;
import com.exc.applibrary.main.model.SiteModel;
import com.exc.applibrary.main.ui.activity.SearchBuildingActivity;

import javax.security.auth.login.LoginException;

import zuo.biao.library.base.BaseFragment;

public class BuildBottomFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "buildbottom";
    private ImageView image_search;
    private View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.activity_bottom_siteinfo, container, false);
        initMainView(root);
        return root;
    }

    public void initMainView(View rootView){

        image_search = rootView.findViewById(R.id.img_search);
        image_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchBuildingActivity.class);
                getActivity().startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public void onDestroyView() {
        Log.e(TAG, "onDestroyView: " );
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Log.e(TAG, "onDetach: " );
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: " );
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
