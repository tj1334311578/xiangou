package com.example.administrator.xiangou.mine.setting.manageraddress;

import android.util.Log;

import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.mine.setting.manageraddress.model.UserAddressBean;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ManagerAddressPresenter extends BasePresenterImpl<ManagerAddressContract.View> implements ManagerAddressContract.Presenter{

    @Override
    public void getUserAddressList(int user_id) {
        addSubscription(mApiService.getUserAddrApi(user_id), new BaseSubscriber<UserAddressBean>(mView.getContext()) {
            @Override
            public void onNext(UserAddressBean userAddressBean) {
                if (userAddressBean.getState().getCode() == 200){
                    mView.dealDataToView(userAddressBean.getData());
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {

            }
        });
    }

    @Override
    public void setDefaultAddress(int user_id, int address_id) {
        addSubscription(mApiService.setUserDefaultAddrApi(user_id, address_id), new BaseSubscriber<Captcha>(mView.getContext()) {
            @Override
            public void onNext(Captcha captcha) {
                if (captcha.getState().getCode()==200){
                    mView.setDefaultAddressSuccess("默认收货地址设置成功！");
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                Log.e("error", "onError: " +e.getMessage());
                mView.sendFialRequest(e.getMessage());
            }

        });
    }

}
