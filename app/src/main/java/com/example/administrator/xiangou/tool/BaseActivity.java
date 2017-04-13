package com.example.administrator.xiangou.tool;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.login.LoginBean;
import com.example.administrator.xiangou.main.User;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/28.
 * 所有activity的基类
 */

public class BaseActivity extends AppCompatActivity {
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

    public static User getbUser() {
        return bUser;
    }
    public static void setbUser(LoginBean.DataBean data) {
        bUser.setUser(data);
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
        this.mToast = Toast.makeText(this,msg,Toast.LENGTH_SHORT);
        this.mToast.setGravity(Gravity.CENTER,0,0);
        LinearLayout toastView = (LinearLayout) this.mToast.getView();
        //你可以在这里放入你的背景
        toastView.setBackgroundResource(R.drawable.toastbg);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher);//这里放你的图片
        toastView.addView(imageView,0);
        mToast.show();
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
}
