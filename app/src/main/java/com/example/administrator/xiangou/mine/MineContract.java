package com.example.administrator.xiangou.mine;


import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

public class MineContract {
    interface View extends BaseView {
//        void dealApplyReturnData(List<ToApplyStoreBean.DataBean> data);
    }

    interface  Presenter extends BasePresenter<View> {
//        void callToApplyStore(int user_id);
    }
}
