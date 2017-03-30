package com.example.administrator.xiangou.tool;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;

import com.example.administrator.xiangou.R;

/**
 * Created by Administrator on 2017/3/8.
 */

public class SelectImageView extends ImageView {
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private int mRadius;//圆半径
    private RectF mRect;//矩形凹行大小
    private int mRoundRadius;//圆角大小
    private BitmapShader mBitmapShader;//图像渲染
    private Matrix mMatrix;
    private int mType;//记录是圆形还是圆角矩形
    public static final int TYPE_CIRCLE=0;//圆
    public static final int TYPE_ROUND=1;//圆角矩形
    public static final int TYPE_OVAL=2;//椭圆
    public static final int TYPE_RECT=3;//矩形
    public static final int DEFAULT_ROUND_RADUIS=10;
    public SelectImageView(Context context) {
        super(context);
    }

    public SelectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
        initView();
    }

    public SelectImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
        initView();
    }

    private void init(AttributeSet attrs) {
        TypedArray t=getContext().obtainStyledAttributes(attrs, R.styleable.SelectImageView);
        int type=t.getInt(R.styleable.SelectImageView_ImageType,0);
        setType(type);
        int radius=t.getDimensionPixelSize(R.styleable.SelectImageView_ImageRoundRadius,DEFAULT_ROUND_RADUIS);
        this.mRoundRadius=radius;
//        Log.e("type", "init: " + type+" radius: "+radius+"  mRoundRadius: "+this.mRoundRadius);
        t.recycle();
    }

    private void initView() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mMatrix=new Matrix();
//        mRoundRadius=DEFAULT_ROUND_RADUIS;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //如果绘制圆形，则强制宽高大小一致
        if (mType==TYPE_CIRCLE)
        {
            mWidth=Math.min(getMeasuredWidth(),getMeasuredHeight());
            mRadius=mWidth/2;
            setMeasuredDimension(mWidth,mWidth);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (null==getDrawable())
            return;
        setBitmapShader();
        switch(mType){
            case TYPE_CIRCLE:
                canvas.drawCircle(mRadius,mRadius,mRadius,mPaint);
                break;
            case TYPE_ROUND:
                mPaint.setColor(Color.RED);
                canvas.drawRoundRect(mRect,mRoundRadius,mRoundRadius,mPaint);
                break;
            case TYPE_OVAL:
                canvas.drawOval(mRect,mPaint);
                break;
            case TYPE_RECT:
                canvas.drawRect(mRect,mPaint);
                break;
            default:
                return;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRect=new RectF(0,0,getWidth(),getHeight());
    }
    /**
     * 设置BitmapShader
     */
    private void setBitmapShader(){
        Drawable drawable =getDrawable();
        if (null==drawable)
            return;
        Bitmap bitmap=drawableToBitmap(drawable);
        //将bit作为着色器来创建一个BitmapShader
        Log.e("null", "setBitmapShader: "+drawable );
        mBitmapShader=new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale=1.0f;
        switch(mType){
            case TYPE_CIRCLE:
                //拿到bitmap宽或高的值
                int bSize=Math.min(bitmap.getWidth(),bitmap.getHeight());
                scale=mWidth * 1.0f/bSize;
                break;
            case TYPE_ROUND:
                //如果图片的宽或者高与view的宽高不匹配，计算出需要缩放的比例；缩放后的图片的宽高，一定要大于我们view的宽高；所以我们这里取大值；
                scale=Math.max(getWidth() * 1.0f/bitmap.getWidth(),getHeight() * 1.0f/bitmap.getHeight());
                break;
            case TYPE_OVAL:
                scale=Math.max(getWidth() * 1.0f/bitmap.getWidth(),getHeight() * 1.0f/bitmap.getHeight());
                break;
            default:
                break;
        }
        // shader的变换矩阵，我们这里主要用于放大或者缩小
//        mMatrix=new Matrix();
//        Log.e("mMatrix", "setBitmapShader: "+mMatrix +mPaint);
        mMatrix.setScale(scale,scale);
        //设置变换矩阵
        mBitmapShader.setLocalMatrix(mMatrix);
        mPaint.setShader(mBitmapShader);
    }

    /**
     * 将drawable转换成bitmap
     * @param drawable
     * @return
     */
    private Bitmap drawableToBitmap(Drawable drawable) {
        if(drawable instanceof BitmapDrawable){
            BitmapDrawable bitmapDrawable= (BitmapDrawable) drawable;
            return bitmapDrawable.getBitmap();
        }
        int w=drawable.getIntrinsicWidth();
        int h=drawable.getIntrinsicHeight();
        Bitmap bitmap=Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        Canvas canvas =new Canvas(bitmap);
        drawable.setBounds(0,0,w,h);
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 单位dp转px
     * @param dp
     * @return
     */
    public int dpTodx(int dp){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getResources().getDisplayMetrics());
    }

    /**
     * 设置图片类型：圆形、圆角矩形、椭圆形//已封装
     * 、矩形、正方形  //未添加
     * @param type
     */
    public void setType(int type){
        if(this.mType!=type)
        {
            this.mType=type;
            invalidate();
        }
    }

    /**
     * 设置圆角
     * @param mRoundRadius
     */
    public void setRoundRadius(int mRoundRadius){
        if (this.mRoundRadius!=mRoundRadius){
            this.mRoundRadius=mRoundRadius;
            invalidate();
        }
    }

}
