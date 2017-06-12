package com.example.administrator.xiangou.mine.setting.personal;

import android.net.Uri;

import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

import okhttp3.MultipartBody;


public class PersonalContract {
    interface View extends BaseView {
        void sendSuccessRequest(String message);
    }

    interface  Presenter extends BasePresenter<View> {
        void uploadUserLogo( Uri data, MultipartBody.Part requestbody);
    }
}
