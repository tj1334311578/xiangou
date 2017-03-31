package com.example.administrator.xiangou.tool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.xiangou.R;

import butterknife.ButterKnife;

/**
 * @author zhouzongyao
 * @Description 封装一些简单重复的操作
 * @email 18482195579@163.com
 * @Date 2017-03-24 9:42
 */
public class BaseFragment extends Fragment {
    public Activity mActivity;
    private Toast mToast;


    public BaseActivity mBaseActivity;//这里是为了引用BaseActivity的ProgressDialog
    public MySharedPreferences mSharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = ContextUtils.mSharedPreferences;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(getContext(),view);
        mActivity = getActivity();
        mBaseActivity = new BaseActivity();
    }

    //还可以把统一的toolbar等控件在此初始化
    //如public Toolbar initToolBar(View view, String title)

    /**
     * 页面跳转方法
     * @param context
     */
    public void startNewUI(Class<?> context){
        startActivity(new Intent(getContext(),context));
    }
    public void startNewUIForResult(Class<?> context,int code){
        startActivityForResult(new Intent(getContext(),context),code);
    }
    public void startNewUIForResult(Class<?> context,int code,Bundle options){
        startActivityForResult(new Intent(getContext(),context),code,options);
    }

    /**
     * 自定义出现位置为屏幕中间的Toast
     * @param msg
     */
    public  void showToast(String msg){
        mToast = Toast.makeText(mActivity,msg,Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER,0,0);
        LinearLayout toastView = (LinearLayout) mToast.getView();
        toastView.setBackgroundResource(R.drawable.toastbg); //你可以在这里放入你的背景
        ImageView imageView = new ImageView(mActivity);
        imageView.setImageResource(R.mipmap.ic_launcher);
        toastView.addView(imageView,0);
        mToast.show();
    }
    public void toastShow(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }
}
