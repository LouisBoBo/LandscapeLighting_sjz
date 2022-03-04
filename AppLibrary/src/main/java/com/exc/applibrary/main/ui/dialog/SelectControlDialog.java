package com.exc.applibrary.main.ui.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exc.applibrary.R;
import com.exc.applibrary.main.adapter.SelectControlRecycleAdapter;
import com.exc.applibrary.main.model.TypeRealyModel;

public class SelectControlDialog extends Dialog {
    private Context context;
    private TypeRealyModel typeRealyModel;
    private String channelTypeName;
    private RecyclerView control_recycleview;
    private SelectControlRecycleAdapter adapter;
    private SelectControlDialog.onSelectControlListener selectControlListener;

    public interface onSelectControlListener{
        void onSelectControl(String name, int id);
    }
    public SelectControlDialog(@NonNull Context context,TypeRealyModel typeRealyModel,String channelTypeName,onSelectControlListener listener) {
        super(context, zuo.biao.library.R.style.MyDialog);
        this.context = context;
        this.typeRealyModel = typeRealyModel;
        this.channelTypeName = channelTypeName;
        this.selectControlListener = listener;
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_selectcontrol_dialog);
        setCanceledOnTouchOutside(true);

        control_recycleview = findViewById(R.id.control_recycleview);
        adapter = new SelectControlRecycleAdapter();

        GridLayoutManager device_manager = new GridLayoutManager(this.context,1);
        control_recycleview.setLayoutManager(device_manager);
        control_recycleview.setAdapter(adapter);
        adapter.setmDatas(typeRealyModel,channelTypeName);

        adapter.setOnItemClickListener(new SelectControlRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemSiteClick(String select_text, int id) {
               selectControlListener.onSelectControl(select_text, id);
            }
        });
    }
    public void setChannelTypeName(TypeRealyModel typeRealyModel,String channelTypeName){
        this.typeRealyModel = typeRealyModel;
        this.channelTypeName = channelTypeName;

        adapter.setmDatas(typeRealyModel,channelTypeName);
    }
}
