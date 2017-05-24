package com.example.administrator.xiangou.mine.setting.manageraddress.editaddress;

import android.util.Log;

import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.mine.ToApplyStoreBean;
import com.example.administrator.xiangou.mine.setting.manageraddress.AddressBean;
import com.example.administrator.xiangou.mine.setting.manageraddress.EditAddressEnterBean;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class EditAddressPresenter extends BasePresenterImpl<EditAddressContract.View> implements EditAddressContract.Presenter{

    @Override
    public void enterEditAddress(int user_id, int address_id) {
        mView.showLoading();
        addSubscription(mApiService.enterEditAddrApi(user_id, address_id), new BaseSubscriber<EditAddressEnterBean>(mView.getContext()) {
            @Override
            public void onNext(EditAddressEnterBean addressEnterBean) {
                switch (addressEnterBean.getState().getCode()){
                    case 200:
                        List<ToApplyStoreBean.DataBean> beanList = new ArrayList<>();
                        for (EditAddressEnterBean.DataBean.AreaBean bean:addressEnterBean.getData().getArea()
                             ) {
                            ToApplyStoreBean.DataBean data = new ToApplyStoreBean.DataBean();
                            data.setName(bean.getName());
                            data.setRegion_id(bean.getRegion_id());
                            beanList.add(data);
                        }
                        mView.enterSuccess(beanList);
                        break;
                }
            }

            @Override
            public void onFinish() {
                mView.hideLoading();
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                mView.sendFialRequest(e.getMessage());
            }
        });
    }

    @Override
    public void commitEditAddress(AddressBean info) {
        addSubscription(mApiService.saveUserAddrApi(info), new BaseSubscriber<Captcha>(mView.getContext()) {
            @Override
            public void onNext(Captcha captcha) {
                if (captcha.getState().getCode()==200){
                    mView.commitSuccess(captcha);
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
    public void getAreaList(Observable observable, final int type) {
        addSubscription(observable, new BaseSubscriber<ToApplyStoreBean>(mView.getContext()) {
            @Override
            public void onNext(ToApplyStoreBean toApplyStoreBean) {
                Log.e("ToApplyStore", "onNext: " +toApplyStoreBean.getData().toString());
                switch (toApplyStoreBean.getState().getCode()){
                    case 200:
                        mView.bindListDataToView(toApplyStoreBean.getData(),type);
                        break;
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
}
