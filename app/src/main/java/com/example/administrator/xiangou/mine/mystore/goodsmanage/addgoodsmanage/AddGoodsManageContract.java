package com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage;

import android.content.Context;

import com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage.bean.IntoAddGoodPageBean;
import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

import okhttp3.ResponseBody;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddGoodsManageContract {
    interface View extends BaseView {
        void dataToView(IntoAddGoodPageBean.DataBean data);
    }

    interface  Presenter extends BasePresenter<View> {
        void callIntoAddGoodPage(int did,int goods_id);
    }
}
