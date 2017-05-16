package com.example.administrator.xiangou.goods_details.simplegoodsdetails;

import android.content.Context;

import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SimpleGoodsDetailsContract {
    interface View extends BaseView {
        void sendDataBeanToView(SimpleGoodsDetialBean data);
    }

    interface  Presenter extends BasePresenter<View> {
        //网络请求获取数据方法
        void dealSimpleDetailsCall(int id);
    }
}
