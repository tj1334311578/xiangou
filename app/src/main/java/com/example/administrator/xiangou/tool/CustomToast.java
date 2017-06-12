package com.example.administrator.xiangou.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;

/**
 * @author zhouzongyao
 * @Description 自定义toast
 * @email 18482195579@163.com
 * @Date 2017-06-01 10:31
 */
public class CustomToast {
    private static CustomToast mCustomToast;
    private Toast mToast;
    private Context mContext;

    private View mToastView;
    private TextView mTitleTv;
    private LinearLayout mContentLl;
    private TextView mMsgTv;
    private CustomImageView mIconCIV;

    private CustomToast(Context context) {
        mContext = context;
        getToast();

    }

    public static CustomToast createToast(Context context){
        if (mCustomToast==null){
            mCustomToast = new CustomToast(context);
        }
        return mCustomToast;
    }

    public Toast getToast(){
        if (mToast==null) {
            //获取屏幕高度
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            int height = wm.getDefaultDisplay().getHeight();
            int width = wm.getDefaultDisplay().getWidth();

            //Toast的初始化
            mToast = new Toast(mContext);
            //加载Toast布局
            mToastView = LayoutInflater.from(mContext).inflate(R.layout.custom_toast,null);
            mTitleTv = (TextView) mToastView.findViewById(R.id.title_toast_tv);
            mContentLl = (LinearLayout) mToastView.findViewById(R.id.content_toast_ll);
            mMsgTv = (TextView) mToastView.findViewById(R.id.text_content_toast_tv);
            mMsgTv.setMaxWidth(width/3*2);
            mIconCIV = (CustomImageView) mToastView.findViewById(R.id.icon_content_toast_civ);
            setContentHorizontal(false);


            //默认Toast的X坐标是屏幕水平居中，Y坐标是屏幕高度的1/3
            mToast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, height/3);
            //默认Toast的显示时间为short
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        return mToast;
    }

    /**
     * 设置toast内容和显示时间
     * @param message 内容
     * @param showTime 显示时间
     * @return
     */
    public CustomToast setMsgNTime(String message,int showTime){
        if (mToast!=null) {
            mMsgTv.setText(message);
            if (showTime==0) {
                mToast.setDuration(Toast.LENGTH_SHORT);
            }else {
                mToast.setDuration(showTime);
            }
        }
        return this;
    }
    public CustomToast setMsgNTime(String message){
        return setMsgNTime(message,0);
    }

    /**
     * 显示toast
     * @return
     */
    public CustomToast showToast(){
        if (mToast!=null) {
            mToast.setView(mToastView);
            mToast.show();
        }
        return this;
    }

    /**
     * 设置toast的标题
     * @param title 标题内容
     * @return
     */
    public CustomToast setTitle(String title){
        if (title!=null){
            if (mTitleTv.getVisibility()!=View.VISIBLE){
                mTitleTv.setVisibility(View.VISIBLE);
            }
            mTitleTv.setText(title);
        }
        return this;
    }

    /**
     * 设置图片
     * @return
     */
    public CustomToast setIcon(int drawableId){
        if (mIconCIV.getVisibility()!=View.VISIBLE){
            mIconCIV.setVisibility(View.VISIBLE);
        }
        mIconCIV.setBackgroundResource(drawableId);
        return this;
    }
    public CustomToast setIcon(Drawable drawable){
        if (mIconCIV.getVisibility()!=View.VISIBLE){
            mIconCIV.setVisibility(View.VISIBLE);
        }
        mIconCIV.setBackground(drawable);
        return this;
    }
    public CustomToast setIcon(Bitmap bitmap){
        if (mIconCIV.getVisibility()!=View.VISIBLE){
            mIconCIV.setVisibility(View.VISIBLE);
        }
        mIconCIV.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置图片的形状类型
     * @param type 图片边角类型：
     *             圆-CustomImageView.TYPE_CIRCLE
     *             椭圆-CustomImageView.TYPE_OVAL
     *             圆角矩形-CustomImageView.TYPE_ROUND，需和setIconRoundRadius()方法一起使用
     *             默认矩形-CustomImageView.TYPE_DEFULTE
     * @return
     */
    public CustomToast setIconType(int type){
        if (mIconCIV.getVisibility()!=View.VISIBLE){
            mIconCIV.setVisibility(View.VISIBLE);
        }
        mIconCIV.setType(type);
        return this;
    }

    /**
     * 设置图片大小，单位是dp
     * @param widthDp 图片的宽
     * @param heightDp 高
     * @return
     */
    public CustomToast setIconSize(int widthDp,int heightDp){
        if (mIconCIV.getVisibility()!=View.VISIBLE){
            mIconCIV.setVisibility(View.VISIBLE);
        }
        ViewGroup.LayoutParams params =  mIconCIV.getLayoutParams();
        params.width = ContextUtils.dp2px(widthDp);
        params.height = ContextUtils.dp2px(heightDp);
        mIconCIV.setLayoutParams(params);
        return this;
    }

    /**
     * 给图片设置圆角角度，需要先设置图片类型为圆角矩形
     * @param roundRadius 圆角角度
     * @return
     */
    public CustomToast setIconRoundRadius(int roundRadius){
        if (mIconCIV.getVisibility()!=View.VISIBLE){
            mIconCIV.setVisibility(View.VISIBLE);
        }
        mIconCIV.setmRoundRadius(roundRadius);
        return this;
    }

    /**
     * 设置toast的显示位置
     * @param grivaty
     * @param xOffset X的偏移坐标值
     * @param yOffset Y蛋偏移坐标值
     * @return
     */
    public CustomToast setGrivaty(int grivaty, int xOffset, int yOffset){
        if (mToast!=null) {
            mToast.setGravity(grivaty, xOffset, yOffset);
        }
        return this;
    }


    /**
     * @param setHorizontal 图片和消息的水平排列，默认是垂直，图片在上
     * @return
     */
    public CustomToast setContentHorizontal(boolean setHorizontal){
        if (setHorizontal) {
            mContentLl.setOrientation(LinearLayout.HORIZONTAL);
        }else {
            mContentLl.setOrientation(LinearLayout.VERTICAL);
        }
        return this;
    }
}
