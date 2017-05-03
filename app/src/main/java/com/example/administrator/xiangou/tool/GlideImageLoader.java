package com.example.administrator.xiangou.tool;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2017/2/24.
 */

public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide加载图片简单的用法
        Glide.with(context).load(path).into(imageView);
    }

//    @Override
//    public ImageView createImageView(Context context) {
//        return super.createImageView(context);
//    }

}
