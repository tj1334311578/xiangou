package com.example.administrator.xiangou.mvp;

import android.util.Log;

import com.example.administrator.xiangou.main.User;
import com.example.administrator.xiangou.net.RetrofitClient;
import com.example.administrator.xiangou.net.XianGouApiService;
import com.example.administrator.xiangou.tool.BaseActivity;
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
    public static User bUser = BaseActivity.bUser;
    public static MySharedPreferences bSharedPreferences = BaseActivity.bSharedPreferences;

    @Override
    public void attachView(V view) {
        mView=view;
        mApiService = RetrofitClient.getInstance(mView.getContext()).create(XianGouApiService.class);
        RetrofitClient.addCookie();
    }

    @Override
    public void detachView() {
        mView=null;
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

    //更新用户信息
    public void upDateUserInfo(String info){
        Log.e("", "upDateUserInfo: save0" );
        if (bSharedPreferences.getString("user_info",null)!=null&&!bSharedPreferences.getString("user_info",null).equals(info)){
            bSharedPreferences.putString("user_info",info);
        }
        Log.e("info", "upDateUserInfo: save");
        if (!bSharedPreferences.getBoolean(MySharedPreferences.STATUS_LOGIN,false)) {
            bSharedPreferences.putBoolean(MySharedPreferences.STATUS_LOGIN, true);
        }
    }
    //用户信息
    public String getUserInfo(){
        return bSharedPreferences.getString("user_info",null);
    }
    //将本地存储的用户信息赋值给用户类对象
    public void setbUserBySP(String str) {
        if (!bUser.toString().equals(str)) {
            String[] user = str.split(",");
            bUser.setUser_id(Integer.parseInt(user[0]));
            bUser.setSex(Integer.parseInt(user[1]));
            bUser.setMobile(user[2]);
            bUser.setNickname(user[3]);
            bUser.setType(Integer.parseInt(user[4]));
            bUser.setStatus(Integer.parseInt(user[5]));
            bUser.setHead_pic(user[6]);
            bUser.setCoupon_count(Integer.parseInt(user[7]));
            bUser.setFollow(Integer.parseInt(user[8]));
            bUser.setWaitPay(Integer.parseInt(user[9]));
            bUser.setWaitSend(Integer.parseInt(user[10]));
            bUser.setWaitReceive(Integer.parseInt(user[11]));
            bUser.setWaitCcomment(Integer.parseInt(user[12]));
            bUser.setOrder_count(Integer.parseInt(user[13]));
            bUser.setRefund(Integer.parseInt(user[14]));
            bUser.setExperience(Integer.parseInt(user[15]));
            bUser.setLevel(Integer.parseInt(user[16]));
        }
    }
}
