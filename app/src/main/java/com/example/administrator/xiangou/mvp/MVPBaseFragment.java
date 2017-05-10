package com.example.administrator.xiangou.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.administrator.xiangou.tool.BaseActivity;
import com.example.administrator.xiangou.tool.BaseFragment;

import java.lang.reflect.ParameterizedType;


public abstract class MVPBaseFragment<V extends BaseView,T extends BasePresenterImpl<V>> extends BaseFragment implements BaseView{

    public T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter= getInstance(this,1);
        mPresenter.attachView((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null)
            mPresenter.detachView();
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    public  <T> T getInstance(Object o, int i) {
            try {

                return ((Class<T>) ((ParameterizedType) (o.getClass()
                        .getGenericSuperclass())).getActualTypeArguments()[i])
                        .newInstance();
            } catch (Fragment.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
            return null;
    }

    @Override
    public void showLoading() {
        BaseActivity activity = (BaseActivity) getActivity();
        activity.showProgressDialog();
    }

    @Override
    public void hideLoading() {
        BaseActivity activity = (BaseActivity) getActivity();
        activity.dismissProgressDialog();
    }
}
