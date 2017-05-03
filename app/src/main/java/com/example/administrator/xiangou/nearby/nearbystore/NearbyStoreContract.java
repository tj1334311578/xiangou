package com.example.administrator.xiangou.nearby.nearbystore;

import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;
import com.example.administrator.xiangou.nearby.apimodel.NearbyStoreApiDataBean;

import java.util.List;

public class NearbyStoreContract {
    interface View extends BaseView {
        void sendStoreDataToView(List<NearbyStoreApiDataBean.DataBean> dataList);
    }

    interface  Presenter extends BasePresenter<View> {
        //附近商品
        void dealNearbyStoreCall(String mapX, String mapY, int distance,int page_no);
    }
}
