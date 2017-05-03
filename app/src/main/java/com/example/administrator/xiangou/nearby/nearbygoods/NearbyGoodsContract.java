package com.example.administrator.xiangou.nearby.nearbygoods;


import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;
import com.example.administrator.xiangou.nearby.apimodel.NearbyGoodsDataBean;

public class NearbyGoodsContract {
    interface View extends BaseView {
        void sendDataBeanToView(NearbyGoodsDataBean.DataBean data);
    }

    interface  Presenter extends BasePresenter<View> {
        //附近商品
        void dealNearbyGoodsCall(String mapX, String mapY, int distance);
    }
}
