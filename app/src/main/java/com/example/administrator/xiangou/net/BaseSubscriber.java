package com.example.administrator.xiangou.net;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import rx.Subscriber;

/**
 * @author zhouzongyao
 * @version 1.0
 * @Description
 * @Date 2017-03-23 14:53
 * @email 18482195579@163.com
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {

    private Context context;
    private boolean isNeedCahe;

    /**
     * 这里根据onNext、onStart、onCompleted、onError4个方法的返回信息做了一些相应简单的处理，
     * 并将其归为3个状态方法
     */
//    public abstract void onSuccess(T data);
//    public abstract void onFailure(String msg);
    public abstract void onFinish();//这个方法用于dismiss loadding等
    public abstract void onError(ExceptionHandle.ResponeThrowable e);

    public BaseSubscriber(Context context) {
        this.context = context;
    }
//    @Override
//    public void onNext(T t) {
//        onSuccess(t);
//    }

    @Override
    public void onError(Throwable e) {
        if(e instanceof ExceptionHandle.ResponeThrowable){
            onError((ExceptionHandle.ResponeThrowable)e);
        } else {
            onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
        onFinish();
    }

    @Override
    public void onStart() {
        super.onStart();

//        Toast.makeText(context, "http is start", Toast.LENGTH_SHORT).show();

        // todo some common as show loadding  and check netWork is NetworkAvailable
        // if  NetworkAvailable no !   must to call onCompleted
        if (!NetworkUtil.isNetworkAvailable(context)) {
            Toast.makeText(context, "无网络，读取缓存数据", Toast.LENGTH_SHORT).show();
            onCompleted();
        }
    }

    @Override
    public void onCompleted() {
//        Toast.makeText(context, "http is Complete", Toast.LENGTH_SHORT).show();
        // todo some common as  dismiss loadding
        Log.e("BaseSubscribe", "onCompleted: " );
        onFinish();
    }


}
