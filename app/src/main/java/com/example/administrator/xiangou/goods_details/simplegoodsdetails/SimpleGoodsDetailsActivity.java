package com.example.administrator.xiangou.goods_details.simplegoodsdetails;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.net.XianGouApiService;
import com.example.administrator.xiangou.tool.GlideImageLoader;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;
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
    private ImageView back,share,storelogo;
    private TextView goodsDetailsdescription,nowprice,oldprice,deliverGoods_Tv,salesvolume_Tv,attentionquantity_Tv,ensure_Tv,
            refund_Tv,storename_Tv,distance_Tv,totalsales_Tv,attention_Tv,comments_sum_Tv,colorandsize_Tv;
    private LinearLayout colorandsize_ll,parameter_ll,getcoupons_ll,comments_head;
    private RecyclerView couponRecycle,commentsRecycle;
    private RatingBar store_ratb;
    private Button goStore_Btn;
    private RelativeLayout comments_rl;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_goodsdetails);
        initView();
//        initBanner();
    }
    private void initView() {
        goods_id=getIntent().getIntExtra("goods_id",1);
        Log.e("tga", "onCreate: "+goods_id );

        banner=findContentView(R.id.simple_goodsdetails_banner,false);
        back=findContentView(R.id.simple_goodsdetails_return);
        //分享按钮
        share=findContentView(R.id.simple_goodsdetails_share);
        goodsDetailsdescription=findContentView(R.id.simple_goodsdetails_description,false);
        nowprice=findContentView(R.id.simple_goodsdetails_storeprice,false);
        oldprice=findContentView(R.id.simple_goodsdetails_oldprice,false);
        deliverGoods_Tv=findContentView(R.id.simple_goodsdetails_deliver,false);
        salesvolume_Tv=findContentView(R.id.simple_goodsdetails_salesvolume,false);
        attentionquantity_Tv=findContentView(R.id.simple_goodsdetails_attentionquantity,false);
        ensure_Tv=findContentView(R.id.simple_goodsdetails_deliver_center,false);
        refund_Tv=findContentView(R.id.simple_goodsdetails_refund,false);
        colorandsize_ll=findContentView(R.id.simple_goodsdetails_colorandsize_ll);
        parameter_ll=findContentView(R.id.simple_goodsdetails_parameter_ll);
        getcoupons_ll=findContentView(R.id.simple_goodsdetails_getcoupons_ll);
        couponRecycle=findContentView(R.id.simple_goodsdetails_getcoupons_recycle,false);
        storelogo=findContentView(R.id.simple_goodsdetails_storelogo,false);
        storename_Tv=findContentView(R.id.simple_goodsdetails_storename,false);
        store_ratb=findContentView(R.id.simple_goodsdetails_rank,false);
        distance_Tv=findContentView(R.id.simple_goodsdetails_distance,false);
        totalsales_Tv=findContentView(R.id.simple_goodsdetails_totalsales_num,false);
        attention_Tv=findContentView(R.id.simple_goodsdetails_total_attention_num,false);
        goStore_Btn=findContentView(R.id.simple_goodsdetails_gotoStore,false);
        comments_sum_Tv=findContentView(R.id.simple_goodsdetails_comments_sum,false);
        comments_rl=findContentView(R.id.simple_goodsdetails_comments_rl);
        comments_head=findContentView(R.id.simple_goodsdetails_comments_head,false);
        commentsRecycle=findContentView(R.id.simple_goodsdetails_comments_recycle,false);
        colorandsize_Tv=findContentView(R.id.simple_goodsdetails_colorandsize_Tv,false);
        //进行网络请求goods_id的详情
        mPresenter.dealSimpleDetailsCall(goods_id,0,null,null,1);
    }
    private void initBanner(List<SimpleGoodsDetialBean.DataBean.GoodsImgBean> goods_img){
//    private void initBanner(){
        //设置banner的样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
//        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置指示器位置（当banner模式中有指示器时）
        List<String> imgs=new ArrayList<>();
        if (goods_img!=null)
            for (SimpleGoodsDetialBean.DataBean.GoodsImgBean img_url:goods_img) {
                imgs.add(XianGouApiService.BASEURL+img_url.getImage_url());
            }else{
        imgs.add(XianGouApiService.BASEURL+"/public/upload/goods/2016/01-13/569611334359e.jpg");
        imgs.add(XianGouApiService.BASEURL+"/public/upload/goods/2016/01-13/569611334359e.jpg");
        imgs.add(XianGouApiService.BASEURL+"/public/upload/goods/2016/01-13/569611334359e.jpg");
        imgs.add(XianGouApiService.BASEURL+"/public/upload/goods/2016/01-13/569611334359e.jpg");
        }
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
        initBanner(data.getData().getGoods_img());
        loadDetialsShow(data);
    }
    //加载详情信息到Ui界面
    private void loadDetialsShow(SimpleGoodsDetialBean data) {
        goodsDetailsdescription.setText(data.getData().getGoods_name());
        nowprice.setText("￥"+data.getData().getShop_price());
        oldprice.setText("￥"+data.getData().getMarket_price());
        oldprice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        colorandsize_Tv.setText("\""+data.getData().getFilter_spec().get颜色().get(0).getItem()+"\" "+"\""+data.getData().getFilter_spec().get尺寸().get(0).getItem()+"\"");
        Log.e("detials", "loadDetialsShow: "+data.getData().getFilter_spec().get颜色().get(0).getItem()+"size:"+ data.getData().getFilter_spec().get尺寸().get(0).getItem());
        //添加优惠券栏目
        if (data.getData().getCoupon()!=null){
            couponRecycle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
            couponRecycle.setAdapter(new CouponAdapter(this,data.getData().getCoupon()));
            couponRecycle.addItemDecoration(new ItemIntervalDecoration(0,17,0,0));
        }else{
            getcoupons_ll.setVisibility(View.GONE);
        }
        //添加商店logo图片
        if (!data.getData().getLogo().equals(""))
            new GlideImageLoader().displayImage(this, XianGouApiService.BASEURL+data.getData().getLogo(),storelogo);//加载店铺logo

        storename_Tv.setText(data.getData().getName());
        distance_Tv.setText("距离当前位置：<"+data.getData().getDistance()+"m");
        attentionquantity_Tv.setText("关注量"+data.getData().getFavorite()+"人");
        salesvolume_Tv.setText("销量"+data.getData().getSales_sum()+"件");

        //设置店铺销量和关注量
        totalsales_Tv.setText(data.getData().getStore_total_sale());
        Log.e("comment", "loadDetialsShow: "+data.getData().getComment().size()+"\n"+data.getData().getComment().toString());
        attention_Tv.setText(data.getData().getStore_follow()+"");
        if (data.getData().getComment().size()>0) {
            Log.e("comment", "loadDetialsShow: "+data.getData().getComment().size() );
            comments_sum_Tv.setText("买家评论（" + data.getData().getComment().size()+ "）");
            //设置评论栏目
            commentsRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            commentsRecycle.setAdapter(new CommentsAdapter(this, data.getData().getComment()));
//            commentsRecycle.addItemDecoration(new ItemIntervalDecoration(0,15,0,0));
        }else{
            comments_rl.setVisibility(View.GONE);
            comments_head.setVisibility(View.GONE);
            commentsRecycle.setVisibility(View.GONE);
        }
    }
}
