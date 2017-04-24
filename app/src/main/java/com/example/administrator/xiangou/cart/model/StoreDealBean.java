package com.example.administrator.xiangou.cart.model;

import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/15.
 */

public class StoreDealBean {
    private String storeName;
    private List<GoodsDealBean> mList;

    public List<GoodsDealBean> getList() {
        return mList;
    }

    public void setList(List<GoodsDealBean> list) {
        mList = list;
    }

    public StoreDealBean(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
