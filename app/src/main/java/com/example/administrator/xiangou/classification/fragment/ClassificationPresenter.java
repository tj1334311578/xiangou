package com.example.administrator.xiangou.classification.fragment;

import android.util.Log;

import com.example.administrator.xiangou.classification.bean.FirstLevelBean;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

public class ClassificationPresenter extends BasePresenterImpl<ClassificationContract.View> implements ClassificationContract.Presenter{

    @Override
    public void callclassificationarray(int cat_id) {
        addSubscription(mApiService.callClassification(cat_id),
                new BaseSubscriber<FirstLevelBean>(mView.getContext()) {
                    @Override
                    public void onNext(FirstLevelBean firstLevelBean) {
                        if (firstLevelBean.getState().getCode()==200)
                        {
                            mView.datatoView(firstLevelBean);
                        }
                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.e("onError", "onError: "+e.toString() );
                    }
                });
    }
}
