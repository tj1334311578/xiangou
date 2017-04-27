package com.example.administrator.xiangou.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.xiangou.mine.store_application.PopupWindowsBaseActivity;
import com.example.administrator.xiangou.tool.BaseActivity;

import java.lang.reflect.ParameterizedType;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


public abstract class MVPBaseActivity<V extends BaseView,T extends BasePresenterImpl<V>> extends PopupWindowsBaseActivity implements BaseView{
    public T mPresenter;
    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter= getInstance(this,1);
        mPresenter.attachView((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null)
        mPresenter.detachView();
        hideLoading();
        onUnsubscribe();
    }

    @Override
    public Context getContext(){
        return this;
    }

    public  <T> T getInstance(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void onUnsubscribe() {
        //取消注册，以避免内存泄露
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
    public void addSubscription(Subscription subscription) {
        //        if (mCompositeSubscription == null) {
        mCompositeSubscription = new CompositeSubscription();
        //        }
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }
    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }
}
