package com.example.administrator.xiangou.goods_details.simplegoodsdetails;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.net.XianGouApiService;
import com.example.administrator.xiangou.tool.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SimpleGoodsDetailsActivity extends MVPBaseActivity<SimpleGoodsDetailsContract.View, SimpleGoodsDetailsPresenter> implements SimpleGoodsDetailsContract.View {
    private int goods_id;
    private Banner banner;
    private ImageView back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_goodsdetails);
        initView();
        initBanner();
    }
    private void initView() {
        goods_id=getIntent().getIntExtra("goods_id",1);
        Log.e("tga", "onCreate: "+goods_id );
        //进行网络请求goods_id的详情
        banner=findContentView(R.id.simple_goodsdetails_banner,false);
        back=findContentView(R.id.simple_goodsdetails_return);
        mPresenter.dealSimpleDetailsCall(goods_id);
    }
//    private void initBanner(List<SimpleGoodsDetialBean.DataBean.GoodsImgBean> goods_img){
    private void initBanner(){
        //设置banner的样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
//        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置指示器位置（当banner模式中有指示器时）
        List<String> imgs=new ArrayList<>();
//        if (goods_img!=null)
//            for (SimpleGoodsDetialBean.DataBean.GoodsImgBean img_url:goods_img) {
//                imgs.add(XianGouApiService.BASEURL+img_url.getImage_url());
//            }else{
        imgs.add(XianGouApiService.BASEURL+"/public/upload/goods/2016/01-13/569611334359e.jpg");
        imgs.add(XianGouApiService.BASEURL+"/public/upload/goods/2016/01-13/569611334359e.jpg");
        imgs.add(XianGouApiService.BASEURL+"/public/upload/goods/2016/01-13/569611334359e.jpg");
        imgs.add(XianGouApiService.BASEURL+"/public/upload/goods/2016/01-13/569611334359e.jpg");
//        }
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImageLoader(new GlideImageLoader());
//        banner.invalidateDrawable(getResources().getDrawable(R.mipmap.girl_h));
        banner.setImages(imgs);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        banner.setDelayTime(3000);
        banner.isAutoPlay(true);
        banner.start();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.simple_goodsdetails_return:
                finish();
        }

    }
    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void sendDataBeanToView(SimpleGoodsDetialBean data) {
        Log.e("tt", "sendDataBeanToView: "+data.toString() );
        //设置banner数据并显示
//        initBanner(data.getData().getGoods_img());
    }
}
