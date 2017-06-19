package com.example.administrator.xiangou.goodsdetails.simplegoodsdetails;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.goods_sort.storehome.StoreHomeActivity;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.adapter.CommentsAdapter;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.adapter.CouponAdapter;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.adapter.CouponDialogAdapter;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.adapter.SimpleGoodsParameterAdapter;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsbean.SimpleGoodsDetialBean;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsdetailscomment.GoodsDetailsCommentActivity;
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

    private SimpleGoodsDetialBean mData;
    private Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_goodsdetails);
        initView();
//        initBanner();
    }
    private void initView() {
        goods_id=getIntent().getIntExtra("goods_id",0);
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
        goStore_Btn=findContentView(R.id.simple_goodsdetails_gotoStore);
        comments_sum_Tv=findContentView(R.id.simple_goodsdetails_comments_sum,false);
        comments_rl=findContentView(R.id.simple_goodsdetails_comments_rl,false);
        comments_head=findContentView(R.id.simple_goodsdetails_comments_head);
        commentsRecycle=findContentView(R.id.simple_goodsdetails_comments_recycle,false);
        colorandsize_Tv=findContentView(R.id.simple_goodsdetails_colorandsize_Tv,false);
        //进行网络请求goods_id的详情
        if (goods_id!=0)
        mPresenter.dealSimpleDetailsCall(goods_id,getUser().getUser_id(),null,null,1);
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
                imgs.add(XianGouApiService.IMGBASEURL +img_url.getImage_url());
            }else{
        imgs.add(XianGouApiService.IMGBASEURL +"/public/upload/goods/2016/01-13/569611334359e.jpg");
        imgs.add(XianGouApiService.IMGBASEURL +"/public/upload/goods/2016/01-13/569611334359e.jpg");
        imgs.add(XianGouApiService.IMGBASEURL +"/public/upload/goods/2016/01-13/569611334359e.jpg");
        imgs.add(XianGouApiService.IMGBASEURL +"/public/upload/goods/2016/01-13/569611334359e.jpg");
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
            case R.id.simple_goodsdetails_return://返回上一层
                finish();
                break;
            case R.id.simple_goodsdetails_parameter_ll://产品参数
                Log.e("mData", "onClick: "+mData.toString() );
                if (mData.getData().getGoods_attr()!=null)
                showParameter(mData);
                break;
            case R.id.simple_goodsdetails_getcoupons_ll://优惠券详情
                if (mData.getData().getCoupon()!=null&&mData.getData().getCoupon().size()>0)
                showCoupon(mData.getData().getCoupon());
                break;
            case R.id.simple_goodsdetails_comments_head://买家评论
//                if (mData.getData().getComment().size()>0)
                 showComment();
                break;
            case R.id.simple_goodsdetails_colorandsize_ll://颜色尺码
                showColorAndSizeDialog();
                break;
            case R.id.simple_goodsdetails_gotoStore://进店逛逛
                if (mData!=null){
                    startNewUICarryStr(StoreHomeActivity.class,"store_id",mData.getData().getStore_id());
                }
                break;
        }
    }

    private void showColorAndSizeDialog() {
        creatdialog();
        final View location=LayoutInflater.from(this).inflate(R.layout.simple_goodsdetails_colorandsize,null);
        dialog.setContentView(location);
        //设置点击外的事件
        location.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height=location.findViewById(R.id.simple_goodsdetails_colorandsize_rl).getTop();
                int y= (int) event.getY();
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if (y<height){
                        dialog.dismiss();
                    }
                }
                return true;
            }
        });
        ImageView addBtn,reduceBtn,cancelBtn;
        TextView totalValue,operand,addToCartBtn,buyNowBtn;
        addBtn= (ImageView) location.findViewById(R.id.simple_goodsdetails_colorandsize_addoperand);
        //可能出现问题
        reduceBtn= (ImageView) location.findViewById(R.id.simple_goodsdetails_colorandsize_addoperand);
        cancelBtn=(ImageView) location.findViewById(R.id.simple_goodsdetails_colorandsize_cancel);
        //退出dialog
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        totalValue= (TextView) location.findViewById(R.id.simple_goodsdetails_colorandsize_numbervalue);
        operand= (TextView) location.findViewById(R.id.simple_goodsdetails_colorandsize_operand);
        addToCartBtn= (TextView) location.findViewById(R.id.simple_goodsdetails_colorandsize_addtocart);
        buyNowBtn= (TextView) location.findViewById(R.id.simple_goodsdetails_colorandsize_buynow);

        RecyclerView colorrecycle,sizerecycle;
        colorrecycle= (RecyclerView) location.findViewById(R.id.simple_goodsdetails_colorandsize_colorrecycle);
        sizerecycle= (RecyclerView) location.findViewById(R.id.simple_goodsdetails_colorandsize_sizerecycle);
        colorrecycle.addItemDecoration(new ItemIntervalDecoration(0,0,0,10));
        sizerecycle.addItemDecoration(new ItemIntervalDecoration(0,0,0,10));
        colorrecycle.setLayoutManager(new GridLayoutManager(this,5,LinearLayoutManager.VERTICAL,false));
        sizerecycle.setLayoutManager(new GridLayoutManager(this,5,LinearLayoutManager.VERTICAL,false));

        /**模拟数据*/
        List<String> sizes=new ArrayList<>(),colors=new ArrayList<>();
        sizes.add("S");
        sizes.add("M");
        sizes.add("L");
        sizes.add("XL");
        sizes.add("XXL");
        sizes.add("XXXL");

        colors.add("白色");
        colors.add("红色");
        colors.add("黑色");
        colors.add("蓝色");
        colors.add("灰色");
        /**模拟数据*/
        colorrecycle.setAdapter(new ColorandSizeAdapter(dialog.getContext(),colors));
        sizerecycle.setAdapter(new ColorandSizeAdapter(dialog.getContext(),sizes));

    }

    //跳转到评论详情页
    private void showComment() {
        Log.e("showcomment", "showComment: "+goods_id );
        startNewUICarryStr(GoodsDetailsCommentActivity.class,"goods_id",goods_id);
    }

    private void showCoupon(List<SimpleGoodsDetialBean.DataBean.CouponBean> coupon) {
        creatdialog();
        final View location=LayoutInflater.from(this).inflate(R.layout.simple_goodsdetails_coupon_dialog,null);
        dialog.setContentView(location);
        //设置dialog外的点击事件
        location.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height=location.findViewById(R.id.simple_goodsdetails_coupon_dialog_ll).getTop();
                int y= (int) event.getY();
                if (event.getAction()==MotionEvent.ACTION_UP)
                    if (y<height)
                        dialog.dismiss();
                return true;
            }
        });
        ImageButton cancel= (ImageButton) location.findViewById(R.id.simple_goodsdetails_coupon_dialog_cancel);
        RecyclerView recycle= (RecyclerView) location.findViewById(R.id.simple_goodsdetails_coupon_dialog_recycle);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        recycle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recycle.setAdapter(new CouponDialogAdapter(this,coupon));
        recycle.addItemDecoration(new ItemIntervalDecoration(0,15,0,0));
    }

    //配置dialog基本数据属性
    public void creatdialog(){
        dialog = new Dialog(this, R.style.custom_dialog);
        dialog.show();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        //设置全屏
        WindowManager wm=getWindowManager();
        Display display=wm.getDefaultDisplay();
        WindowManager.LayoutParams lp= dialog.getWindow().getAttributes();
        lp.width=display.getWidth();//设置宽度
        dialog.getWindow().setAttributes(lp);
        //全屏無padding
        dialog.getWindow().getDecorView().setPadding(0,0,0,0);
    }

    private void showParameter(SimpleGoodsDetialBean mData) {
        //创建dialog；
        creatdialog();

        final View location= LayoutInflater.from(this).inflate(R.layout.simple_goodsdetails_productparameter,null);
        dialog.setContentView(location);
        Button OK_Button= (Button) location.findViewById(R.id.simplegoodsdetails_productparameter_button);
        ListView listView = (ListView) location.findViewById(R.id.simplegoodsdetails_productparameter_listview);
        if (mData.getData().getGoods_attr().size()>0)
        listView.setAdapter(new SimpleGoodsParameterAdapter(this,mData.getData().getGoods_attr()));

        OK_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //设置点击区域外的监听
        location.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height=location.findViewById(R.id.simplegoodsdetails_productparameter_Rl).getTop();
                int y= (int) event.getY();
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if (y<height){
                        dialog.dismiss();
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void sendDataBeanToView(SimpleGoodsDetialBean data) {
        Log.e("tt", "sendDataBeanToView: "+data.toString() );
        mData=data;
        //设置banner数据并显示
        initBanner(data.getData().getGoods_img());
        loadDetialsShow(data);
    }
    //加载详情信息到Ui界面
    private void loadDetialsShow(SimpleGoodsDetialBean data) {
        Log.e("load", "loadDetialsShow:已进入 " );
        goodsDetailsdescription.setText(data.getData().getGoods_name());
        nowprice.setText("￥"+data.getData().getShop_price());
        oldprice.setText("￥"+data.getData().getMarket_price());
        oldprice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
//        Log.e("detials", "loadDetialsShow: "+data.getData().getFilter_spec().get颜色().get(0).getItem()+"size:"+ data.getData().getFilter_spec().get尺寸().get(0).getItem());
        if (data.getData().getFilter_spec()!=null&&data.getData().getFilter_spec().get颜色().get(0).getItem().equals("")&&data.getData().getFilter_spec().get尺寸().get(0).getItem().equals("")) {
            colorandsize_Tv.setText("\""+data.getData().getFilter_spec().get颜色().get(0).getItem()+"\" "+"\""+data.getData().getFilter_spec().get尺寸().get(0).getItem()+"\"");
        }
        //添加优惠券栏目
        if (data.getData().getCoupon()!=null){
            Log.e("coupon", "loadDetialsShow:coupon不为空 "+data.getData().getCoupon().toString() );
            couponRecycle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
            couponRecycle.setAdapter(new CouponAdapter(this,data.getData().getCoupon()));
            couponRecycle.addItemDecoration(new ItemIntervalDecoration(17,0,0,0));
        }else{
            getcoupons_ll.setVisibility(View.GONE);
        }
        //添加商店logo图片
        if (!data.getData().getLogo().equals("")) {
            new GlideImageLoader().displayImage(this, XianGouApiService.IMGBASEURL + data.getData().getLogo(), storelogo);//加载店铺logo
        }
        storename_Tv.setText(data.getData().getName());
        distance_Tv.setText("距离当前位置：<"+data.getData().getDistance()+"m");
        attentionquantity_Tv.setText("关注量"+data.getData().getFavorite()+"人");
        salesvolume_Tv.setText("销量"+data.getData().getSales_sum()+"件");

        Log.e("fieff", "loadDetialsShow: "+"添加商店logo图片" +"data.getData().getStore_total_sale():"+data.getData().getStore_total_sale().toString());
        //设置店铺销量和关注量
        totalsales_Tv.setText(data.getData().getStore_total_sale());
        Log.e("fieff", "loadDetialsShow: "+"添加商店logo图片" );
//        Log.e("comment", "loadDetialsShow: "+data.getData().getComment().size()+"\n"+data.getData().getComment().toString());//无comment属性报1000错误
        attention_Tv.setText(data.getData().getStore_follow()+"");
        if (data.getData().getComment()!=null&&data.getData().getComment().size()>0) {
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
