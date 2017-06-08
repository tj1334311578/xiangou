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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.login.LoginBean;
import com.example.administrator.xiangou.main.User;
import com.example.administrator.xiangou.net.XianGouApiService;

import java.io.Serializable;

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

    private  GlideImageLoader mImageLoader;
    private CompositeSubscription mCompositeSubscription;
    public View mContextView;
    private CustomToast mCustomToast;

    public static User getUser(){
        return ContextUtils.gUser;
    }
    public static MySharedPreferences getSP(){
        return ContextUtils.gSharedPreferences;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageLoader = new GlideImageLoader();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container,0);
    }
    public View setContextView(LayoutInflater inflater,ViewGroup container, int layoutId){
        mContextView = inflater.inflate(layoutId,container,false);
//        ButterKnife.bind(getContext(),mContextView);
        initView();
        return mContextView;
    }

    public abstract void initView();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ButterKnife.bind(getContext(),view);
        mActivity = getActivity();
    }

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
        mCustomToast.setMsgNTime(msg,2000).showToast();
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


    /**************** 项目 *****************/
    /**
     * 提供图片加载方法
     * @param imgUrl 图片的网址（不需要加baseURL）
     * @param imageView 显示图片的imageview控件
     */
    public void loadImg(String imgUrl, ImageView imageView) {
        if (imgUrl!=null) {
            mImageLoader.displayImage(getContext(), XianGouApiService.IMGBASEURL + imgUrl, imageView);
        }else {
            showToast("图片资源为空");
        }
    }

    //将本地存储的用户信息赋值给用户类对象
    public void setbUserBySP(Object obj) {
//        String[] user = str.split(",");
//        getUser().setUser_id(Integer.parseInt(user[0]));
//        getUser().setSex(Integer.parseInt(user[1]));
//        getUser().setMobile(user[2]);
//        String nickName = user[3];
//        if (nickName.charAt(0) == '\"'){
//            String[] as = nickName.split("\"");
//            nickName = as[0];
//            for (String s:as
//                    ) {
//                if (nickName.length()<s.length()){
//                    nickName = s;
//                }
//            }
//        }
//        getUser().setNickname(nickName);
//        getUser().setNickname(user[3]);
//        getUser().setType(Integer.parseInt(user[4]));
//        getUser().setStatus(Integer.parseInt(user[5]));
//        getUser().setHead_pic(user[6]);
//        getUser().setCoupon_count(Integer.parseInt(user[7]));
//        getUser().setFollow(Integer.parseInt(user[8]));
//        getUser().setWaitPay(Integer.parseInt(user[9]));
//        getUser().setWaitSend(Integer.parseInt(user[10]));
//        getUser().setWaitReceive(Integer.parseInt(user[11]));
//        getUser().setWaitCcomment(Integer.parseInt(user[12]));
//        getUser().setOrder_count(Integer.parseInt(user[13]));
//        getUser().setRefund(Integer.parseInt(user[14]));
//        getUser().setExperience(Integer.parseInt(user[15]));
//        getUser().setLevel(Integer.parseInt(user[16]));
        if (obj instanceof String){
            getUser().setbUserBySP((String) obj);
        }else {
            getUser().setUser((LoginBean.DataBean) obj);
        }
        Log.e("baseAt", "setbUserBySP: success " +getUser().toString());
    }

    //用户登录方法
//    public void callIDlogin(String userName, String password) {
//        Log.e("baseAt", "enter：IDlogin "+userName+" p="+password+" --pwd: "+ ContextUtils.MD5(password));
//        if (userName!=null&&password!=null) {
//            addSubscription( mApiService.loginID(userName, ContextUtils.MD5(password)),
//                    new BaseSubscriber<LoginBean>(getContext()) {
//                        @Override
//                        public void onNext(LoginBean loginBean) {
//                            switch (loginBean.getState().getCode()) {
//                                case 200:
//                                    if (loginBean.getData() != null) {
//                                        if (!bSharedPreferences.getString("user_info", null).equals(loginBean.getData().toString())) {
//                                            setbUserBySP(loginBean.getData().toString());
//                                            upDateUserInfo(loginBean.getData().toString());
//                                            // mView.ReLoginidSuccess(loginBean.getData());
//                                            mLoginCall.callSuccess(loginBean.getData());
//                                            Log.e("baseAt", "LoginidSuccess: buser" + bUser.toString());
//                                            return;
//                                        }
//                                    }
//                                    break;
//                                case 100:
//                                default:
//                                    //                                    mView.sendFialRequest(loginBean.getState().getMsg());
//                                    break;
//                            }
//                        }
//
//                        @Override
//                        public void onFinish() {
//                            Log.e("baseAt", "onFinish：IDlogin");
//                            //                            mView.hideLoading();
//                        }
//
//                        @Override
//                        public void onError(ExceptionHandle.ResponeThrowable e) {
//                            Log.e("baseAt", e.code + " onError：" + e.getMessage());
//                            mLoginCall.callError(e);
//                            //                            if (e.code == 1000)
//                            //                                mView.sendFialRequest("账号或密码错误");
//                        }
//                    }
//            );
//        }
//
//    }
//    public interface LoginCall{
//        void callSuccess(LoginBean.DataBean data);
//        void callError(ExceptionHandle.ResponeThrowable e);
//        void callDealMore(Object o);
//    }
//    protected LoginCall mLoginCall;
//    public void setLoginCall(LoginCall mLoginCall){
//            this.mLoginCall = mLoginCall;
//        }
}
