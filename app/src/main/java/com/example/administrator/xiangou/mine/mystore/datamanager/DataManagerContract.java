package com.example.administrator.xiangou.mine.mystore.datamanager;

import android.content.Context;

import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DataManagerContract {
    interface View extends BaseView {
        void dataToView(TotalDataBean dataInfo);
    }

    interface  Presenter extends BasePresenter<View> {
        void requestDataInfo(int did,String Time);
    }
}
