package com.example.administrator.xiangou.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.tool.GlideImageLoader;
import com.example.administrator.xiangou.tool.Search_EditText;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> imgUrls,titles;
    private Banner mBanner;
    private Search_EditText search_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initBanner();
    }

    private void initView() {
        search_editText= (Search_EditText) findViewById(R.id.search);
        search_editText.setDrawableListener(new Search_EditText.DrawableListener() {
            @Override
            public void onDrawableLeftClick(View view) {
                Toast.makeText(MainActivity.this, "you click left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawableRightClick(View view) {
                Toast.makeText(MainActivity.this, "you click right", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initBanner() {
        imgUrls = new ArrayList<>();
        imgUrls.add("http://dynamic-image.yesky.com/740x-/uploadImages/2017/052/41/18XAS82RL2O1_800x1200.jpg");
        imgUrls.add("http://dynamic-image.yesky.com/740x-/uploadImages/2017/049/27/9SI697LWZF0E_800x1200.jpg");
        imgUrls.add("http://dynamic-image.yesky.com/740x-/uploadImages/2017/051/09/S7R05BP4N4X0_800x1200.jpg");
        imgUrls.add("http://dynamic-image.yesky.com/740x-/uploadImages/2017/049/00/61XX007XTFBW_800x1200.jpg");
        imgUrls.add("http://dynamic-image.yesky.com/740x-/uploadImages/2017/049/21/C7UE22883BE7_800x1200.jpg");
        titles = new ArrayList<>();
        titles.add("美女1");
        titles.add("美女2");
        titles.add("美女3");
        titles.add("美女4");
        titles.add("美女5");

        mBanner = (Banner) findViewById(R.id.banner_home).findViewById(R.id.banner);
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(imgUrls);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置轮播时间
        mBanner.setDelayTime(3000);
        //设置自动轮播
        mBanner.isAutoPlay(true);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(titles);
        mBanner.start();
    }
}
