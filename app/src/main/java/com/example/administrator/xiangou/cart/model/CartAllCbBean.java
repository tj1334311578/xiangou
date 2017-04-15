package com.example.administrator.xiangou.cart.model;

import java.util.List;

/**
 * Created by cuboo on 2016/12/1.
 */

public class CartAllCbBean {
    private boolean ischeck;
//    private String text;
    private List<CartItemCbBean> list;

    public boolean ischeck() {
        return ischeck;
    }

    public void setIsCheck(boolean ischeck) {
        this.ischeck = ischeck;
    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }

    public List<CartItemCbBean> getList() {
        return list;
    }

    public void setList(List<CartItemCbBean> list) {
        this.list = list;
    }
}
