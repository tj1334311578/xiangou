package com.example.administrator.xiangou.mine.mystore.storemanager;

import android.content.Context;

import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class StoreManagerContract {
    interface View extends BaseView {
        void infoDataToView(StoreManagerInfoBean data);
        void modifySuccessd();
    }

    interface  Presenter extends BasePresenter<View> {
        void callStoreInfo(int store_id);
        void callEditStoreInfo(int did, String map_x, String map_y, String address
        , int province_id, int city_id, int district_id, String synopsis, String tel, MultipartBody.Part logo);
    }
}
