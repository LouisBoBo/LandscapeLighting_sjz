package com.exc.applibrary.main.adapter;

import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.exc.applibrary.R;
import com.exc.applibrary.main.model.StrategyModel;

import java.util.List;


/**
 * @author Richie on 2017.07.31
 *         改过 Indicator 的 ExpandableListView 的适配器
 */
public class IndicatorExpandableListAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "IndicatorExpandableList";
    private List<StrategyModel.DataBean.ListBean> listBeans;
    //                用于存放Indicator的集合
    private SparseArray<ImageView> mIndicators;
    private OnGroupExpandedListener mOnGroupExpandedListener;

    private IndicatorExpandableListAdapter.OnPlayClickListener onPlayClickListener;
    public interface OnPlayClickListener {
        void onPlayClick(String select_text , int id , int style);//0播放 1暂停
    }
    public void setOnPlayClickListener(IndicatorExpandableListAdapter.OnPlayClickListener listener){
        onPlayClickListener = listener;
    }

    public IndicatorExpandableListAdapter(List<StrategyModel.DataBean.ListBean> listBeans) {

        this.listBeans = listBeans;
        mIndicators = new SparseArray<>();
    }
    public void refreshUI(List<StrategyModel.DataBean.ListBean> listBeans){
        this.listBeans = listBeans;
        notifyDataSetChanged();
    }
    public void setOnGroupExpandedListener(OnGroupExpandedListener onGroupExpandedListener) {
        mOnGroupExpandedListener = onGroupExpandedListener;
    }

    //            根据分组的展开闭合状态设置指示器
    public void setIndicatorState(int groupPosition, boolean isExpanded) {
        if (isExpanded) {

        } else {

        }
    }

    @Override
    public int getGroupCount() {
        return this.listBeans.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }


    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expand_group_indicator, parent, false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.baseview = (TextView) convertView.findViewById(R.id.view_baseview);
            groupViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.label_group_indicator);
            groupViewHolder.ivIndicator = (ImageView) convertView.findViewById(R.id.iv_indicator);
            groupViewHolder.imgPlay = (ImageView) convertView.findViewById(R.id.img_play);
            groupViewHolder.imgStop = (ImageView) convertView.findViewById(R.id.img_stop);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.tvTitle.setText(this.listBeans.get(groupPosition).getName());
        //      把位置和图标添加到Map
        mIndicators.put(groupPosition, groupViewHolder.ivIndicator);
        //      根据分组状态设置Indicator
        setIndicatorState(groupPosition, isExpanded);

        //播放
        groupViewHolder.imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlayClickListener.onPlayClick(groupViewHolder.tvTitle.getText().toString(),listBeans.get(groupPosition).getId(),0);
            }
        });

        //停止
        groupViewHolder.imgStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlayClickListener.onPlayClick(groupViewHolder.tvTitle.getText().toString(),listBeans.get(groupPosition).getId(),1);
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View
            convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expand_child, parent, false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.baseview = (TextView) convertView.findViewById(R.id.view_baseview);
            childViewHolder.tvdescription = (TextView) convertView.findViewById(R.id.tv_description);
            childViewHolder.tvstatus = (TextView) convertView.findViewById(R.id.tv_status);
            childViewHolder.tvweek = (TextView) convertView.findViewById(R.id.tv_week);
            childViewHolder.tvcontent = (TextView) convertView.findViewById(R.id.tv_content);

            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }



        childViewHolder.tvdescription.setText("描述：" + this.listBeans.get(groupPosition).getDescription());
        childViewHolder.tvstatus.setText("状态：" + (this.listBeans.get(groupPosition).getStatus()==0?"未使用":"使用中"));
        childViewHolder.tvweek.setText("星期执行：" + weekData(groupPosition));
        childViewHolder.tvcontent.setText("内容：");

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        Log.d(TAG, "onGroupExpanded() called with: groupPosition = [" + groupPosition + "]");
        if (mOnGroupExpandedListener != null) {
            mOnGroupExpandedListener.onGroupExpanded(groupPosition);
        }
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        Log.d(TAG, "onGroupCollapsed() called with: groupPosition = [" + groupPosition + "]");
    }

    public String weekData(int groupPosition){
        String weekstr = "";
        if(this.listBeans.get(groupPosition).getCycleTypes()!=null && this.listBeans.get(groupPosition).getCycleTypes().size() >0){
            for (Integer cycleType : this.listBeans.get(groupPosition).getCycleTypes()) {
                switch (cycleType){
                    case 1:
                        weekstr = weekstr + "星期天" + "，";
                        break;
                    case 2:
                        weekstr = weekstr + "星期一" + "，";
                        break;
                    case 3:
                        weekstr = weekstr + "星期二" + "，";
                        break;
                    case 4:
                        weekstr = weekstr + "星期三" + "，";
                        break;
                    case 5:
                        weekstr = weekstr + "星期四" + "，";
                        break;
                    case 6:
                        weekstr = weekstr + "星期五" + "，";
                        break;
                    case 7:
                        weekstr = weekstr + "星期六" + "，";
                        break;
                }
            }

            weekstr=weekstr.substring(0,weekstr.length()-1);
        }

        return weekstr;
    }

    private static class GroupViewHolder {
        TextView tvTitle;
        ImageView ivIndicator;
        ImageView imgPlay;
        ImageView imgStop;
        TextView baseview;
    }

    private static class ChildViewHolder {
        TextView tvdescription;
        TextView tvstatus;
        TextView tvweek;
        TextView tvcontent;
        TextView baseview;
    }
}
