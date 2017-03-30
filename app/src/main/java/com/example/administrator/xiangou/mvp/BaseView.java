package com.example.administrator.xiangou.mvp;

import android.content.Context;

public interface BaseView {
     Context getContext();
     void showLoading();
     void hideLoading();
     //请求失败Object user
     void sendFialRequest(String message);
}
