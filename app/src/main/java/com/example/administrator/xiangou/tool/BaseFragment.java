package com.example.administrator.xiangou.tool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.main.User;

import java.io.Serializable;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @author zhouzongyao
 * @Description 封装一些简单重复的操作
 * @email 18482195579@163.com
 * @Date 2017-03-24 9:42
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    public Activity mActivity;

    private CompositeSubscription mCompositeSubscription;
    public User bUser;
    public MySharedPreferences bSharedPreferences;
    public View mContextView;
    private CustomToast mCustomToast;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bUser = ContextUtils.gUser;
        bSharedPreferences = ContextUtils.gSharedPreferences;
        Log.e("onCreate", "onCreate: "+bUser.toString() );
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container,0);
    }
    public View setContextView(LayoutInflater inflater,ViewGroup container, int layoutId){
        mContextView = inflater.inflate(layoutId,container,false);
        initView();
        return mContextView;
    }

    public abstract void initView();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(getContext(),view);
        mActivity = getActivity();
    }

//    public ProgressDialog mProgressDialog;
//    public ProgressDialog showProgressDialog() {
//        mProgressDialog = new ProgressDialog(mActivity);
//        mProgressDialog.setMessage("拼命加载中...");
//        mProgressDialog.show();
//        return mProgressDialog;
//    }
//    public ProgressDialog showProgressDialog(CharSequence message) {
//        mProgressDialog = new ProgressDialog(mActivity);
//        mProgressDialog.setMessage(message);
//        mProgressDialog.show();
//        return mProgressDialog;
//    }
//    public void dismissProgressDialog() {
//        if (mProgressDialog != null && mProgressDialog.isShowing()) {
//            // progressDialog.hide();会导致android.view.WindowLeaked
//            mProgressDialog.dismiss();
//        }
//    }


    @Override
    public void onDestroy() {
        super.onDestroy();
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

    //还可以把统一的toolbar等控件在此初始化
    //如public Toolbar initToolBar(View view, String title)
    public void setTextToTv(TextView textView, Object data){
        textView.setText(data + "");
    }
    public <T extends View> T findContentView(int id){
        return findContentView(id,true);
    }
    public <T extends View> T findContentView(int id, boolean toSetClickListener){
        View view = mContextView.findViewById(id);
        if (toSetClickListener) {
            view.setOnClickListener(this);
        }
        return (T) view;
    }

    /**
     * 页面跳转方法
     */
    public void startNewUI(Class<?> context){
        startActivity(new Intent(getContext(),context));
    }
    public void startNewUICarryStr(Class<?> context, String name, Object str ){
        Intent intent = new Intent();
        if (context != null)
            intent.setClass(mActivity,context);
        if (str instanceof String) {
            intent.putExtra(name, str.toString());
        }else if (str instanceof Integer){
            int s = (Integer) str;
            intent.putExtra(name,s);
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
    public void startNewUIForResult(Class<?> context,int code,String name,Object str){
        Intent intent = new Intent(mActivity,context);
        if (str instanceof String) {
            String s= (String) str;
            intent.putExtra(name, s);
        }else if (str instanceof Serializable){
            Serializable s = (Serializable) str;
            intent.putExtra(name,s);
        }else if (str instanceof Bundle){
            Bundle s = (Bundle) str;
            intent.putExtra(name,s);
        }
        startActivityForResult(intent,code);
    }

    /**
     * 自定义出现位置为屏幕中间的Toast
     * @param msg
     */
    public  void showToast(String msg){
        mCustomToast = CustomToast.createToast(getContext());
        mCustomToast.setTitle("温馨提示").setIcon(R.mipmap.icon_app).setMsgNTime(msg).showToast();
    }
    public  void showToast(String title,String msg){
        mCustomToast = CustomToast.createToast(getContext());
        mCustomToast.setTitle(title).setMsgNTime(msg).showToast();
    }

    /**
     * 按返回键后立即使Toast不再显示
     */

    public void toastShow(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    //判断用户是否登录
    public boolean isLogined(){
        return bSharedPreferences.getBoolean(MySharedPreferences.STATUS_LOGIN,false);
    }
    //判断用户注销
    public void logout(){
        bSharedPreferences.putBoolean(MySharedPreferences.STATUS_LOGIN,false);
        showToast("now "+isLogined()+"");
//        startNewUI(IDLoginActivity.class);
    }
//项目：

    //将本地存储的用户信息赋值给用户类对象
    public void setbUserBySP(String str){
        String[] user = str.split(",");
        Log.e("userinfo", "setbUserBySP: " + user.toString());
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
