package com.example.administrator.xiangou.mvp;

import com.example.administrator.xiangou.net.MyApiService;
import com.example.administrator.xiangou.net.RetrofitClient;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class BasePresenterImpl<V extends BaseView> implements BasePresenter<V>{
    protected V mView;
    protected MyApiService mApiService;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(V view) {
        mView=view;
        mApiService = RetrofitClient.getInstance(mView.getContext()).create(MyApiService.class);
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


}
