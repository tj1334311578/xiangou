package com.example.administrator.xiangou.mine.setting.manageraddress.editaddress;

import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.mine.ToApplyStoreBean;
import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

import org.json.JSONObject;

import java.util.List;

import rx.Observable;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class EditAddressContract {
    interface View extends BaseView {
        void commitSuccess(Captcha captcha);
        void enterSuccess(List<ToApplyStoreBean.DataBean> addressEnterBean);

        //bind地区列表数据
        void bindListDataToView(List<ToApplyStoreBean.DataBean> data, int type);
    }

    interface  Presenter extends BasePresenter<View> {
        //进入编辑页请求
        void enterEditAddress(int user_id,int address_id);
        //保存地址请求
        void commitEditAddress(JSONObject info);

        //获取地区列表
        void getAreaList(Observable observable, int type);
    }
}
