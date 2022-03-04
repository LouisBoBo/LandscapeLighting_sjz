package com.exc.applibrary.main.eventbus;

import com.exc.applibrary.main.model.LoopList;

public class LoopItemSwitchBean {

    private  LoopList.DataBean.ListBean switchItem;
    private int switchPosition;
    private boolean isChecked;

    public LoopList.DataBean.ListBean getSwitchItem() {
        return switchItem;
    }

    public void setSwitchItem(LoopList.DataBean.ListBean switchItem) {
        this.switchItem = switchItem;
    }

    public int getSwitchPosition() {
        return switchPosition;
    }

    public void setSwitchPosition(int switchPosition) {
        this.switchPosition = switchPosition;
    }




    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }



}
