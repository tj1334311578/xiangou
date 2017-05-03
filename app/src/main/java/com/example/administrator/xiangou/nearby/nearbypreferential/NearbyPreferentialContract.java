package com.example.administrator.xiangou.nearby.nearbypreferential;

import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;
import com.example.administrator.xiangou.nearby.apimodel.NearbyBenifitDataBean;

public class NearbyPreferentialContract {
    interface View extends BaseView {
        void sendPreferentialDataToView(NearbyBenifitDataBean.DataBean data);
    }

    interface  Presenter extends BasePresenter<View> {
        //附近优惠
        void dealNearbyPreferentialCall(String mapX, String mapY);

        //倒计时器
//     void dealTimeLimitModuleCall(TextView hTv, TextView mTv, TextView sTv);
    }
}
