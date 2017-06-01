package com.example.administrator.xiangou.mine.mystore.goodsmanage;

import android.content.Context;

import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class GoodsManageContract {
    interface View extends BaseView {
        void dataToView(List<GoodsitemBean.DataBean> dataList);
    }

    interface  Presenter extends BasePresenter<View> {
        void callGoodsManagerList(int did,String key_word,int page_no,int type);
    }
}
