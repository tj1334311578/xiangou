package com.example.administrator.xiangou.tool;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.administrator.xiangou.R;


/**
 * Created by zhouzongyao on 2017/2/24.
 */

public class CustomImageView extends ImageView {
    public static final int TYPE_DEFULTE = 0;//矩形
    public static final int TYPE_CIRCLE = 1;//圆形
    public static final int TYPE_ROUND = 2;//圆角矩形
    public static final int TYPE_OVAL = 3;//椭圆形
    public static final int DEFAUT_ROUND_RADIUS = 10;//默认圆角大小

    private Bitmap bitmap;
    private BitmapShader mBitmapShader;//图形渲染
    private int mWidth,mHeight;
    private Paint mPaint;
    private int mRadius;
    private RectF mRect;
    private int mRoundRadius=DEFAUT_ROUND_RADIUS;;
    private Matrix mMatrix;
    private int mType;//记录是view的形状


    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context,attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomImageView);
        mType = array.getInteger(R.styleable.CustomImageView_civ_type,0);
        mRoundRadius = array.getInteger(R.styleable.CustomImageView_civ_roundradius,0);
        setType(mType);
        setmRoundRadius(mRoundRadius);
        array.recycle();

    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 如果是绘制圆形，则强制宽高大小一致
        if (mType==TYPE_CIRCLE) {
            mWidth = Math.min(getMeasuredWidth(),getMeasuredHeight());
            mRadius = mWidth/2;
            setMeasuredDimension(mWidth,mWidth);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        if (getDrawable()==null) return;
              setBitmapShader();
        switch (mType) {
            case TYPE_DEFULTE:
                canvas.drawRect(mRect,mPaint);
                break;
            case TYPE_CIRCLE:
                canvas.drawCircle(mRadius,mRadius,mRadius,mPaint);
                break;
            case TYPE_ROUND:
                canvas.drawRoundRect(mRect,mRoundRadius,mRoundRadius,mPaint);
                break;
            case TYPE_OVAL:
                canvas.drawOval(mRect,mPaint);
                break;
        }
    }


    //设置BitmapShader
    private void setBitmapShader() {
        Drawable drawable = getDrawable();
        mMatrix = new Matrix();
        if (drawable == null) return;
        bitmap = drawableToBitmap(drawable);
        mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;
        if (mType == TYPE_CIRCLE) {
            // 拿到bitmap宽或高的小值
            int minSize = Math.min(bitmap.getWidth(),bitmap.getHeight());
            scale = mWidth*1.0f /minSize;
        }else {//if (mType ==TYPE_ROUND || mType ==TYPE_OVAL || mType == TYPE_DEFULTE)
            // 如果图片的宽或者高与view的宽高不匹配，计算出需要缩放的比例；
            // 缩放后的图片的宽高，一定要大于我们view的宽高；所以我们这里取大值；
            scale = Math.max( getWidth()*1.0f / bitmap.getWidth(),
                    getHeight()*1.0f / bitmap.getHeight() );
        }
        mMatrix.setScale(scale,scale);
        mBitmapShader.setLocalMatrix(mMatrix);
        mPaint.setShader(mBitmapShader);
    }

    /**
     * drawable转bitmap
     * @return
     * @param drawable
     */
    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable){
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            return bitmapDrawable.getBitmap();
        }
        int bitmapWidth = drawable.getIntrinsicWidth();
        int bitmapHeight = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888:Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(bitmapWidth,bitmapHeight, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0,0,bitmapWidth,bitmapHeight);
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRect = new RectF(0,0,getWidth(),getHeight());
    }

    public int getType() {
        return mType;
    }

    /**
     * 设置图片类型：圆形、圆角矩形、椭圆形
     * @param mType
     */
    public void setType(int mType) {
        if (this.mType != mType) {
            this.mType =mType;
//            Log.e("setType", "setType: "+ mType);
            invalidate();
        }
    }

    public int getmRoundRadius() {
        return mRoundRadius;
    }

    /**
     * 设置圆角大小
     * @param mRoundRadius
     */
    public void setmRoundRadius(int mRoundRadius) {
        this.mRoundRadius = mRoundRadius;
        invalidate();
    }
}
