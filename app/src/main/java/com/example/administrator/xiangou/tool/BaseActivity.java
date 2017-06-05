package com.example.administrator.xiangou.tool;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.xiangou.main.User;
import com.example.administrator.xiangou.net.RetrofitClient;
import com.example.administrator.xiangou.net.XianGouApiService;

import java.io.Serializable;

import butterknife.ButterKnife;
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
    //    public static ContextUtils bContextUtils;
    public static User bUser;
    public static MySharedPreferences bSharedPreferences;
    public static XianGouApiService mApiService;
    private CustomToast mCustomToast;

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
        bUser = ContextUtils.gUser;
        bSharedPreferences = ContextUtils.gSharedPreferences;
        mApiService = RetrofitClient.getInstance(this).create(XianGouApiService.class);
        ButterKnife.bind(this);
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
    /**
     * 判断用户是否登录
     * @return
     */
    public static boolean isLogined(){
        return bSharedPreferences.getBoolean(MySharedPreferences.STATUS_LOGIN,false);
    }
    /**
     * 注销用户
     * @return
     */
    public void logout(){
        bSharedPreferences.putBoolean(MySharedPreferences.STATUS_LOGIN,false);
        showToast("now "+isLogined()+"");
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
    public void loadImg(String imgUrl, ImageView imageView) {
        if (imgUrl!=null && imageView!=null) {
            mImageLoader.displayImage(this, XianGouApiService.BASEURL + imgUrl, imageView);
        }else {
            showToast("图片资源为空");
        }
    }
    //更新用户信息
    public void upDateUserInfo(String info){
        if (bSharedPreferences.getString("user_info",null)!=null){
            bSharedPreferences.remove("user_info");
        }
        bSharedPreferences.putString("user_info",info);
        if (!bSharedPreferences.getBoolean(MySharedPreferences.STATUS_LOGIN,false)) {
            bSharedPreferences.putBoolean(MySharedPreferences.STATUS_LOGIN, true);
        }
    }
    //将本地存储的用户信息赋值给用户类对象
    public void setbUserBySP(String str) {
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
