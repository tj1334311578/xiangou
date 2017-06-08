package com.example.administrator.xiangou.mvp;

import com.example.administrator.xiangou.main.User;
import com.example.administrator.xiangou.net.RetrofitClient;
import com.example.administrator.xiangou.net.XianGouApiService;
import com.example.administrator.xiangou.tool.ContextUtils;
import com.example.administrator.xiangou.tool.MySharedPreferences;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class BasePresenterImpl<V extends BaseView> implements BasePresenter<V>{
    protected V mView;
    protected XianGouApiService mApiService;
    private CompositeSubscription mCompositeSubscription;

    public static User getUser(){
        return ContextUtils.gUser;
    }
    public static MySharedPreferences getSP(){
        return ContextUtils.gSharedPreferences;
    }

    @Override
    public void attachView(V view) {
        mView=view;
        mApiService = RetrofitClient.getInstance(mView.getContext()).create(XianGouApiService.class);
//        RetrofitClient.addCookie();
    }

    @Override
    public void detachView() {
        mView=null;
        mApiService = null;
        onUnsubscribe();//这里调用RXjava取消注册方法
    }

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    //用户信息
    public String getUserInfo(){
        return getSP().getString("user_info",null);
    }
    //将本地存储的用户信息赋值给用户类对象
    public void setbUserBySP(Object obj) {
        if (obj instanceof String){
            getUser().setbUserBySP((String) obj);
        }else if (obj instanceof LoginBean.DataBean){
            getUser().setUser((LoginBean.DataBean) obj);
        }
        Log.e("baseAt", "setbUserBySP: success " +getUser().toString());
    }
}
