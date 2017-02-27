package com.example.administrator.xiangou.tool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/2/23.
 */

public class Search_EditText extends EditText{
//    private DrawableLeftListener mLeftListener ;
//    private DrawableRightListener mRightListener ;
    private  DrawableListener mlistener;

    final int DRAWABLE_LEFT = 0;
    final int DRAWABLE_TOP = 1;
    final int DRAWABLE_RIGHT = 2;
    final int DRAWABLE_BOTTOM = 3;

    @SuppressLint("NewApi")
    public Search_EditText(Context context, AttributeSet attrs, int defStyleAttr,
                           int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public Search_EditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Search_EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Search_EditText(Context context) {
        super(context);
    }
    public void setDrawableListener(DrawableListener listener){
        if (listener instanceof DrawableListener)
            this.mlistener=listener;
    }

//    public void setDrawableLeftListener(DrawableLeftListener listener) {
//        this.mLeftListener = listener;
//    }
//
//    public void setDrawableRightListener(DrawableRightListener listener) {
//        this.mRightListener = listener;
//    }
    public interface  DrawableListener{
    void onDrawableLeftClick(View view);
    void onDrawableRightClick(View view);
}
//    public interface DrawableLeftListener {
//        public void onDrawableLeftClick(View view) ;
//    }
//
//    public interface DrawableRightListener {
//        public void onDrawableRightClick(View view) ;
//    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (mlistener!=null){
                    Drawable DrawableRight=getCompoundDrawables()[DRAWABLE_RIGHT];
                    Drawable drawableLeft = getCompoundDrawables()[DRAWABLE_LEFT] ;
                    if (drawableLeft!=null&& event.getRawX() <= (getLeft() + drawableLeft.getBounds().width())){
                        mlistener.onDrawableLeftClick(this) ;
                        return true ;
                    }else if (DrawableRight != null && event.getRawX() >= (getRight() - DrawableRight.getBounds().width())) {
                        mlistener.onDrawableRightClick(this) ;
                        return true ;
                    }
//
                }
//                if (mRightListener != null) {
//                    Drawable drawableRight = getCompoundDrawables()[DRAWABLE_RIGHT] ;
//                    if (drawableRight != null && event.getRawX() >= (getRight() - drawableRight.getBounds().width())) {
//                        mRightListener.onDrawableRightClick(this) ;
//                        return true ;
//                    }
//                }
//
//                if (mLeftListener != null) {
//                    Drawable drawableLeft = getCompoundDrawables()[DRAWABLE_LEFT] ;
//                    if (drawableLeft != null && event.getRawX() <= (getLeft() + drawableLeft.getBounds().width())) {
//                        mLeftListener.onDrawableLeftClick(this) ;
//                        return true ;
//                    }
//                }
                break;
        }

        return super.onTouchEvent(event);
    }
}
