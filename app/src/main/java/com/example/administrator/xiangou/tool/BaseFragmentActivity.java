package com.example.administrator.xiangou.tool;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.login.LoginBean;
import com.example.administrator.xiangou.main.User;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/20.
 */

public abstract class BaseFragmentActivity extends FragmentActivity implements View.OnClickListener {
    private BroadcastReceiver exitReceiver=new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            System.exit(0);
        }
    };
    private Toast mToast;

    //    public static ContextUtils bContextUtils;
    public static User bUser;
    public static MySharedPreferences bSharedPreferences;

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
        bUser = ContextUtils.gUser;
        bSharedPreferences = ContextUtils.gSharedPreferences;
        ButterKnife.bind(this);
        registerExitReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterExitReceiver();
    }

    public ProgressDialog mProgressDialog;
    public ProgressDialog showProgressDialog() {
        this.mProgressDialog = new ProgressDialog(this);
        this.mProgressDialog.setMessage("拼命加载中...");
        this.mProgressDialog.show();
        return this.mProgressDialog;
    }
    public ProgressDialog showProgressDialog(CharSequence message) {
        this.mProgressDialog = new ProgressDialog(this);
        this.mProgressDialog.setMessage(message);
        this.mProgressDialog.show();
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
    public void startNewUICarryStr(Class<?> context, String name, String str ){
        Intent intent = new Intent(this,context);
        intent.putExtra(name,str);
        startActivity(intent);
    }
    public void startNewUICarryStrs(Class<?> context, String name, String[] strs ){
        Intent intent = new Intent(this,context);
        intent.putExtra(name, strs);
        startActivity(intent);
    }
    public void startNewUIForResult(Class<?> context,int code){
        startActivityForResult(new Intent(this,context),code);
    }
    public void startNewUIForResult(Class<?> context,int code,Bundle options){
        startActivityForResult(new Intent(this,context),code,options);
    }

    /**
     * Toast
     * @param msg
     */
    public  void showToast(String msg){
        if (mToast==null) {
            mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
            LinearLayout toastView = (LinearLayout) mToast.getView();
            toastView.setBackgroundResource(R.drawable.toastbg); //你可以在这里放入你的背景
            toastView.setPadding(ContextUtils.px2dp(8), ContextUtils.px2dp(0), ContextUtils.px2dp(8), ContextUtils.px2dp(0));
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.ic_launcher);
            toastView.addView(imageView, 0);
        }else {
            mToast.setText(msg);
        }
        mToast.show();
    }
    public void toastShow(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
//        startNewUI(IDLoginActivity.class);
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

    //项目：

    public User getbUser() {
        return bUser;
    }
    public void setbUser(LoginBean.DataBean data) {
        bUser.setUser(data);
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
