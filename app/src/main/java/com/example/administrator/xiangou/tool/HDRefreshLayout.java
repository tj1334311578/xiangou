package com.example.administrator.xiangou.tool;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/4/5.
 */

public class HDRefreshLayout extends ViewGroup {

    public static final int STATE_AIDL = 0x1;//初始状态
    public static final int STATE_PULL_REFRESH = 0x2;//继续上拉或者下拉进行刷新
    public static final int STATE_RELEASH_TO_REFRESH = 0x3;//松开以刷新
    public static final int STATE_REFRESHING = 0x4;//
    public static final int STATE_REFRESH_SUCCESS = 0x5;//
    public static final int STATE_REFRESH_FAIL = 0x6;//

    private int curState = STATE_AIDL;//当前的状态
    private RefreshLoadingLayout refreshView;//下拉的视图
    private RefreshLoadingLayout loadView;//上拉的视图
    private View pullableView;
    private int refreshViewHeight;
    private int loadViewHeight;
    private int startY;
    private int lastY;
    private int ratio = 3;//定值没有做变化
    private boolean pullDonw = true;//目前是不是下拉状态

    public HDRefreshLayout(Context context) {
        super(context);
    }

    public HDRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    /**
     * 声明一个initViews方法创建我们的RefreshLayoutHeader和Footer
     * @param context
     */
    private  void initViews(Context context){
        setBackgroundColor(Color.WHITE);
        refreshView = new RefreshLayoutHeader(context);
        loadView = new RefreshLayoutFooter(context);
        addView(refreshView);
        addView(loadView);
    }

    /**
     * 在onMeasure方法里面获取中间的pullableView,并且得到RefreshLayoutHeader和Footer 的高度
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        pullableView = getChildAt(2);
        refreshViewHeight = refreshView.getMeasuredHeight();
        loadViewHeight = loadView.getMeasuredHeight();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 获得了相应的高度之后,我们重写ViewGroup 的onLayout方法,来布局我们的RefreshLayoutHeader,
     * Footer以及PullableView的位置
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        refreshView.layout(l, t - refreshViewHeight, r, t);
        loadView.layout(l, b, r, b + loadViewHeight);
        pullableView.layout(l, t, r, b);
    }

    /**
     * viewgroup的事件拦截器
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev);
        //如果现在正在刷新或者刷新成功这段时间,不允许继续操
        if (curState==STATE_REFRESHING || curState==STATE_REFRESH_SUCCESS||curState==STATE_REFRESH_FAIL){
            return true ;
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = (int) ev.getY();
                startY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dis = (int) (ev.getY() - lastY);
                IPullable iPullable = (IPullable) pullableView;
                if (dis > 0 && iPullable.canPullDown()) {//向下拉
                    pullDonw = true;
                    return true;
                } else if (dis < 0 && iPullable.canPullUp()) {//向上拉
                    pullDonw = false;
                    return true;
                }
                lastY = (int) ev.getY();
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        if (curState == STATE_REFRESHING || curState == STATE_REFRESH_SUCCESS || curState == STATE_REFRESH_FAIL) {
            return true;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int pullY = (int) (event.getY() - startY);
                if (pullDonw && pullY > 0) {//向下拉
                    scrollTo(0, -pullY / ratio);
                    if (pullY / ratio < refreshViewHeight) {
                        curState = STATE_PULL_REFRESH;
                    } else if (pullY / ratio > refreshViewHeight) {
                        curState = STATE_RELEASH_TO_REFRESH;
                    }
                    refreshView.setState(curState);
                } else if (!pullDonw && pullY < 0) {//向上拉
                    scrollTo(0, -pullY / ratio);
                    if (Math.abs(pullY) / ratio < loadViewHeight) {
                        curState = STATE_PULL_REFRESH;
                    } else if (Math.abs(pullY) / ratio > loadViewHeight) {
                        curState = STATE_RELEASH_TO_REFRESH;
                    }
                    loadView.setState(curState);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (curState == STATE_RELEASH_TO_REFRESH) {
                    curState = STATE_REFRESHING;
                    if (pullDonw) {
                        if (onHDRefreshListener != null) {
                            onHDRefreshListener.onRefresh();
                        }
                        scrollTo(0, -refreshViewHeight);
                        refreshView.setState(curState);
                    } else {
                        if (onHDRefreshListener != null) {
                            onHDRefreshListener.onLoadMore();
                        }
                        scrollTo(0, loadViewHeight);
                        loadView.setState(curState);
                    }
                } else {
                    curState = STATE_AIDL;
                    scrollTo(0, 0);
                }
                break;
        }
        return true;
    }

    /**
     * 全局变量定义接口回调
     *
     */
    public interface OnHDRefreshListener{
        void onRefresh();
        void onLoadMore();
    }
    OnHDRefreshListener onHDRefreshListener;
    public void setOnHDRefreshListener(OnHDRefreshListener onHDRefreshListener){
        this.onHDRefreshListener=onHDRefreshListener;
    }

    public void onRefreshFinish(boolean success){
        if (success) {
            curState=STATE_REFRESH_SUCCESS;
        }else{
            curState=STATE_REFRESH_FAIL;
        }
        if (pullDonw){
            refreshView.setState(curState);
        }else{
            loadView.setState(curState);
        }
        new Handler().postDelayed(new TimerTask() {
            @Override
            public void run() {
                scrollTo(0,0);
                curState=STATE_AIDL;
            }
        },1000);
    }
}
