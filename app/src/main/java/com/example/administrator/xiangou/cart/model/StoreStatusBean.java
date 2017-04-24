package com.example.administrator.xiangou.cart.model;

/**
 * Created by cuboo on 2016/12/1.
 */

public class StoreStatusBean {
    private boolean ischeck;
    private boolean hasToEditStore;

    public boolean ischeck() {
        return ischeck;
    }

    public void setIsCheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public boolean isHasToEditStore() {
        return hasToEditStore;
    }

    public void setHasToEditStore(boolean hasToEditStore) {
        this.hasToEditStore = hasToEditStore;
    }
}
