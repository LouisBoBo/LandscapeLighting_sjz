package com.exc.applibrary.main.adapter;

import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.exc.applibrary.R;
import com.exc.applibrary.main.model.IndexModel;

import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

public class BuildMoreRecycleAdapter extends BGARecyclerViewAdapter<IndexModel> {

    public BuildMoreRecycleAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.activity_searchresult_item);
    }

    @Override
    public void fillData(BGAViewHolderHelper helper, int position, IndexModel model) {
        helper.setText(R.id.tv_content, model.name);
        helper.getTextView(R.id.bottom_line).setVisibility(View.INVISIBLE);
        if (model.is_select) {
            helper.getTextView(R.id.tv_select).setBackgroundResource(R.drawable.background_btn_selector);
        }else {
            helper.getTextView(R.id.tv_select).setBackgroundResource(R.color.alph);
        }
    }

    /**
     * 是否为该分类下的第一个条目
     *
     * @param position
     * @return
     */
    public boolean isCategoryFistItem(int position) {
        // 第一条数据是该分类下的第一个条目
        if (position == 0) {
            return true;
        }

        String currentTopc = getItem(position).topc;
        String preTopc = getItem(position - 1).topc;
        // 当前条目的分类和上一个条目的分类不相等时，当前条目为该分类下的第一个条目
        if (!TextUtils.equals(currentTopc, preTopc)) {
            return true;
        }

        return false;
    }

    public int getPositionForCategory(int category) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = getItem(i).topc;
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == category) {
                return i;
            }
        }
        return -1;
    }
}