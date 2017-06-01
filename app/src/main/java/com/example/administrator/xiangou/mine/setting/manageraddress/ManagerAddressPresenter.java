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
    public void getUserAddressList(int user_id, final String type) {
        if (type.equals("get")) {
            mView.showLoading();
        }
        addSubscription(mApiService.getUserAddrApi(user_id), new BaseSubscriber<UserAddressBean>(mView.getContext()) {
            @Override
            public void onNext(UserAddressBean userAddressBean) {
                if (userAddressBean.getState().getCode() == 200){
                    Log.e("UserAddressBean", "onNext: " +userAddressBean.getData().toString() );
                    mView.dealDataToView(userAddressBean.getData(),type);
                }
            }

            @Override
            public void onFinish() {
                if (type.equals("get")) {
                    mView.hideLoading();
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                mView.sendFialRequest(e.getMessage());
            }
        });
    }

    @Override
    public void setDefaultAddress(final int user_id, int address_id) {
        addSubscription(mApiService.setUserDefaultAddrApi(user_id, address_id), new BaseSubscriber<Captcha>(mView.getContext()) {
            @Override
            public void onNext(Captcha captcha) {
                if (captcha.getState().getCode()==200){
                    getUserAddressList(user_id,"set");
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

    @Override
    public void deleteUserAddress(final int user_id, final int address_id) {
//        mView.showLoading();
        addSubscription(mApiService.delUserAddrApi(user_id, address_id), new BaseSubscriber<Captcha>(mView.getContext()) {
            @Override
            public void onNext(Captcha captcha) {
                if (captcha.getState().getCode()==200){
                    getUserAddressList(user_id,"delete");
                    mView.deleteAddressSuccess("收货地址删除成功！");
                }
            }

            @Override
            public void onFinish() {
//                mView.hideLoading();
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                Log.e("error", "onError: " +e.getMessage());
                mView.sendFialRequest(e.getMessage());
            }

        });
    }

}
