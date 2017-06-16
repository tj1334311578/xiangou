package com.example.administrator.xiangou.mine.followpage.followstore;

import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

import java.util.List;

public class FollowStoreContract {
    interface View extends BaseView {
        void getStoresListSuccess(List<FollowStoreBean.DataBean> data);
    }

    interface  Presenter extends BasePresenter<View> {
        void getCollectStoresListApi(int user_id, int page_no);
    }
}
