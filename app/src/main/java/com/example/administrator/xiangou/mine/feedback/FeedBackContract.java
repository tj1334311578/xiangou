package com.example.administrator.xiangou.mine.feedback;

import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FeedBackContract {
    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {
        void setHeadView();
    }
}
