package com.example.administrator.xiangou.goods_details.storehome.storehome.homestore;

import android.content.Context;

import com.example.administrator.xiangou.goods_details.storehome.HomePageBean;
import com.example.administrator.xiangou.home.adapter.HomeAdapterRV;
import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class HomeStoreContract {
    interface View extends BaseView {
        void sendDataBeanToView(HomePageBean dataBean);
    }

    interface  Presenter extends BasePresenter<View> {
        void dealHomeStoreCall(HomePageBean dataBean);
    }
}
