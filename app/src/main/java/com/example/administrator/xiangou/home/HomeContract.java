package com.example.administrator.xiangou.home;


import com.example.administrator.xiangou.home.model.HomeDataBean;
import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

public class HomeContract {
    interface View extends BaseView {
        void getHomeDataSuccess(HomeDataBean.DataBean data);
    }

    interface  Presenter extends BasePresenter<View> {
        void getHomeData(String map_x, String map_y, int cityid);
    }
}
