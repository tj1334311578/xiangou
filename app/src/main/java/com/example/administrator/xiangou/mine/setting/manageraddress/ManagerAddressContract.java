package com.example.administrator.xiangou.mine.setting.manageraddress;

import com.example.administrator.xiangou.mine.setting.manageraddress.model.UserAddressBean;
import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ManagerAddressContract {
    interface View extends BaseView {
        void dealDataToView(List<UserAddressBean.DataBean> data);
        void setDefaultAddressSuccess(String str);
    }

    interface  Presenter extends BasePresenter<View> {
        void getUserAddressList(int user_id);
        void setDefaultAddress(int user_id,int address_id);
    }
}
