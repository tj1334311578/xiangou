package com.example.administrator.xiangou.tool;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.xiangou.login.LoginBean;
import com.example.administrator.xiangou.main.User;
import com.example.administrator.xiangou.net.RetrofitClient;
import com.example.administrator.xiangou.net.XianGouApiService;

import java.io.Serializable;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/2/28.
 * 所有activity的基类
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    private BroadcastReceiver exitReceiver=new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            System.exit(0);
        }
    };

    private  GlideImageLoader mImageLoader;
    private CompositeSubscription mCompositeSubscription;
    public static XianGouApiService mApiService;
    private CustomToast mCustomToast;

    public static User getUser(){
        return ContextUtils.gUser;
    }
    public static MySharedPreferences getSP(){
        return ContextUtils.gSharedPreferences;
    }
    //注册广播
    private void registerExitReceiver() {
        IntentFilter exitFilter = new IntentFilter();
        exitFilter.addAction(Constant.EXIT_APP_ACTION);
        this.registerReceiver(this.exitReceiver, exitFilter);
    }
    //广播注册注销
    private void unRegisterExitReceiver() {
        if (exitReceiver != null) {
            this.unregisterReceiver(this.exitReceiver);
        }
    }

    //退出程序--可以写在退出控件监听中
    public void exit_app(){
        Intent intent =new Intent();
        intent.setAction(Constant.EXIT_APP_ACTION);
        this.sendBroadcast(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        bContextUtils = ContextUtils.getInstance();
        mImageLoader = new GlideImageLoader();
        mApiService = RetrofitClient.getInstance(this).create(XianGouApiService.class);
//        ButterKnife.bind(this);
        registerExitReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onUnsubscribe();//这里调用RXjava取消注册方法
        unRegisterExitReceiver();
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

    public ProgressDialog mProgressDialog;
    public ProgressDialog showProgressDialog() {
        if (this.mProgressDialog==null ) {
            this.mProgressDialog = new ProgressDialog(this);
            this.mProgressDialog.setMessage("拼命加载中...");
            this.mProgressDialog.show();
        }else if (!this.mProgressDialog.isShowing()){
            this.mProgressDialog.show();
        }
        return this.mProgressDialog;
    }
    public ProgressDialog showProgressDialog(CharSequence message) {
        if (this.mProgressDialog==null ) {
            this.mProgressDialog = new ProgressDialog(this);
            this.mProgressDialog.setMessage(message);
            this.mProgressDialog.show();
        }else if (!this.mProgressDialog.isShowing()){
            this.mProgressDialog.show();
        }
        return this.mProgressDialog;
    }
    public void dismissProgressDialog() {
        if (this.mProgressDialog != null && this.mProgressDialog.isShowing()) {
            // progressDialog.hide();会导致android.view.WindowLeaked
            this.mProgressDialog.dismiss();
        }
    }

    /**
     * 页面跳转方法
     * @param context
     */
    public void startNewUI(Class<?> context){
        startActivity(new Intent(this,context));
    }
    public void startNewUICarryStr(Class<?> context, String[] name, Object... str ){
        Intent intent = new Intent(this,context);
        for (int i = 0; i < str.length; i++) {
            if (str[i] instanceof String) {
                intent.putExtra(name[i], str[i].toString());
            }else if (str[i] instanceof Serializable){
                Serializable s = (Serializable) str[i];
                intent.putExtra(name[i],s);
            }else if (str[i] instanceof Bundle){
                Bundle s = (Bundle) str[i];
                intent.putExtra(name[i],s);
            }else if (str[i] instanceof String[]){
                String[] strs = (String[]) str[i];
                intent.putExtra(name[i], strs);
            }
        }
        startActivity(intent);
    }
    public void startNewUICarryStr(Class<?> context, String name, Object str ){
        Intent intent = new Intent(this,context);
        if (str instanceof String) {
            intent.putExtra(name, str.toString());
        }else if (str instanceof Serializable){
            Serializable s = (Serializable) str;
            intent.putExtra(name,s);
        }else if (str instanceof Bundle){
            Bundle s = (Bundle) str;
            intent.putExtra(name,s);
        }else if (str instanceof String[]){
            String[] strs = (String[]) str;
            intent.putExtra(name, strs);
        }
        startActivity(intent);
    }
    public void startNewUIForResult(Class<?> context,int requestCode,String name,Object str ){
        String[] names = {name};
        Object[] strs = {str};
        startNewUIForResult(context,requestCode,names,strs);
    }
    public void startNewUIForResult(Class<?> context,int requestCode,String[] names,Object[] strs ){
        Intent intent = new Intent(this,context);
        for (int i = 0; i < strs.length; i++) {
            if (strs[i] instanceof String) {
                String s= (String) strs[i];
                intent.putExtra(names[i], s);
            }else if (strs[i] instanceof Serializable){
                Serializable s = (Serializable) strs[i];
                intent.putExtra(names[i],s);
            }else if (strs[i] instanceof Bundle){
                Bundle s = (Bundle) strs[i];
                intent.putExtra(names[i],s);
            }
        }
        startActivityForResult(intent,requestCode);
    }

    public <T extends View> T findContentView(int id){
        return findContentView(id,true);
    }
    public <T extends View> T findContentView(int id, boolean toSetClickListener){
        View view = this.findViewById(id);
        if (toSetClickListener) {
            view.setOnClickListener(this);
        }
        return (T) view;
    }
    /**
     * 定义需要的Toast
     * @param msg
     */
    public  void showToast(String msg){
        mCustomToast = CustomToast.createToast(getApplicationContext());
        mCustomToast.setMsgNTime(msg,2000).showToast();
    }
    public  void showToast(String title,String msg){
        mCustomToast = CustomToast.createToast(getApplicationContext());
        mCustomToast.setTitle(title).setMsgNTime(msg).showToast();
    }
    public void cancelToast() {
        if (mCustomToast != null && mCustomToast.getToast()!=null) {
            mCustomToast.getToast().cancel();
        }
    }
    /**
     * 按返回键后立即使Toast不再显示
     */
    @Override
    public void onBackPressed() {
        cancelToast();
        super.onBackPressed();
    }
    public void toastShow(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //双击退出APP
//    public long firstTime=0;
//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        switch (keyCode){
//            case KeyEvent.KEYCODE_BACK:
//                long secondTime = System.currentTimeMillis();
//                if((secondTime-firstTime)>2000){
//                    toastShow("再按一次退出程序!");
//                    firstTime=secondTime;
//                    return true;
//                }else {
//                    exit_app();
////                    finish();
////                    System.exit(0);
//                }
//        }
//        return super.onKeyUp(keyCode, event);
//    }

/**************** 项目 *****************/
    /**
     * 提供图片加载方法
     * @param imgUrl 图片的网址（不需要加baseURL）
     * @param imageView 显示图片的imageview控件
     */
    public void loadImg(Object imgUrl, ImageView imageView) {
        if (imgUrl!=null) {
            if (imgUrl instanceof String) {
                mImageLoader.displayImage(this, XianGouApiService.IMGBASEURL + imgUrl, imageView);
            }else {
                mImageLoader.displayImage(this, imgUrl, imageView);
            }
        }else {
            showToast("图片资源为空");
        }
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

//    //用户登录方法
//    public void callIDlogin(String userName, String password) {
//        Log.e("baseAt", "enter：IDlogin "+userName+" p="+password+" --pwd: "+ ContextUtils.MD5(password));
//        if (userName!=null&&password!=null) {
//            addSubscription( mApiService.loginID(userName, ContextUtils.MD5(password)),
//                    new BaseSubscriber<LoginBean>(this) {
//                        @Override
//                        public void onNext(LoginBean loginBean) {
//                            switch (loginBean.getState().getCode()) {
//                                case 200:
//                                    if (loginBean.getData() != null) {
//                                        if (!getSP().getString("user_info",null)
//                                                .equals(loginBean.getData().toString()) ) {
//                                            setbUserBySP(loginBean.getData().toString());
////                                            upDateUserInfo(loginBean.getData().toString());
////                                            mLoginCall.callSuccess(loginBean.getData());
//                                            Log.e("baseAt", "LoginidSuccess: buser" + getUser().toString());
//                                            return;
//                                        }
//                                    }
//                                    break;
////                                case 100:
////                                default:
////                                    mView.sendFialRequest(loginBean.getState().getMsg());
////                                    break;
//                            }
//                        }
//
//                        @Override
//                        public void onFinish() {
////                            Log.e("baseAt", "onFinish：IDlogin");
////                            mView.hideLoading();
//                        }
//
//                        @Override
//                        public void onError(ExceptionHandle.ResponeThrowable e) {
//                            Log.e("baseAt", e.code + " onError：" + e.getMessage());
////                            mLoginCall.callError(e);
////                            if (e.code == 1000)
////                                mView.sendFialRequest("账号或密码错误");
//                        }
//                    }
//            );
//        }
//
//    }
//    public interface LoginCall{//接口方法回调需要先 实现setLoginCall(LoginCall mLoginCall)方法
//        void callSuccess(LoginBean.DataBean data);
//        void callError(ExceptionHandle.ResponeThrowable e);
//        void callDealMore(Object o);
//    }
//    protected LoginCall mLoginCall;
//    public void setLoginCall(LoginCall mLoginCall){
//        this.mLoginCall = mLoginCall;
//    }
}
