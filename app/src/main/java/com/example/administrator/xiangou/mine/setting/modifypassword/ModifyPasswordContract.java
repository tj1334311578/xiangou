package com.example.administrator.xiangou.mine.setting.modifypassword;

import android.content.Context;

import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ModifyPasswordContract {
    interface View extends BaseView {
        void getCode(int code);
    }

    interface  Presenter extends BasePresenter<View> {
       void modifyPassword(int user_id,String oldpassword,String password);
    }
}
