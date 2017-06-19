package com.example.administrator.xiangou.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.administrator.xiangou.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2017/2/24.
 */

public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, final ImageView imageView) {
        //Glide加载图片简单的用法
        SimpleTarget viewTag = new SimpleTarget<GlideBitmapDrawable>() {
            @Override
            public void onResourceReady(GlideBitmapDrawable resource, GlideAnimation<? super GlideBitmapDrawable> glideAnimation) {
                imageView.setImageBitmap(resource.getBitmap());
//                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }

        };
        Glide.with(context)
                .load(path)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.mipmap.default_img)
//                .crossFade() //使图片变化平滑
                .into(viewTag);
    }

    public static Bitmap[] setbackground(Context context, String path){
        Log.e("path", "setbackground: "+path );
        final Bitmap[] bit = new Bitmap[1];
        SimpleTarget viewTag=new SimpleTarget<GlideBitmapDrawable>() {
            @Override
            public void onResourceReady(GlideBitmapDrawable resource, GlideAnimation<? super GlideBitmapDrawable> glideAnimation) {
                bit[0] =resource.getBitmap();
            }
        };
        Glide.with(context)
                .load(path)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.mipmap.default_img)
                .into(viewTag);
        return bit;
    }

//    @Override
//    public ImageView createImageView(Context context) {
//        CustomImageView customImageView = new CustomImageView(context);
//        return customImageView;
////        return super.createImageView(context);
//    }

}
