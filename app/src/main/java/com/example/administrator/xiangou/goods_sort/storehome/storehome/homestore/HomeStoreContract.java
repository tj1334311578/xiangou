package com.example.administrator.xiangou.goods_sort.storehome.storehome.homestore;

import com.example.administrator.xiangou.goods_sort.storehome.HomePageBean;
import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

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
