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
        void dealDataToView(List<UserAddressBean.DataBean> data, String type);
        void setDefaultAddressSuccess(String str, int position);
        void deleteAddressSuccess(String str, int address_id, int position);
    }

    interface  Presenter extends BasePresenter<View> {
        void getUserAddressList(int user_id, String type);
        void setDefaultAddress(int user_id, int address_id, int position);
        void deleteUserAddress(int user_id, int address_id,int position);
    }
}
