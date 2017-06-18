package com.example.administrator.xiangou.mine.mystore;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mine.mystore.couponmanager.CouponManagerActivity;
import com.example.administrator.xiangou.mine.mystore.datamanager.DataManagerActivity;
import com.example.administrator.xiangou.mine.mystore.goodsmanage.GoodsManageActivity;
import com.example.administrator.xiangou.mine.mystore.ordermanager.OrderManagerActivity;
import com.example.administrator.xiangou.mine.mystore.storemanager.StoreManagerActivity;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.tool.CustomImageView;
import com.example.administrator.xiangou.tool.DrawableTextView;

import static com.example.administrator.xiangou.tool.MySharedPreferences.KEY_STOREIMG;

public class MyStoreActivity extends MVPBaseActivity<MyStoreContract.View, MyStorePresenter> implements MyStoreContract.View {
    private ImageButton back;
    private DrawableTextView storeMange,goodsMange,orderMange,totalData,goodsSales,preferentialMange;
    private CustomImageView mStoreIcon;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_center);
        initView();
    }

    private void initView() {
        back=findContentView(R.id.seller_center_return);
        storeMange=findContentView(R.id.seller_center_store_management_storemanager);
        goodsMange=findContentView(R.id.seller_center_store_management_goodsmanager);
        orderMange=findContentView(R.id.seller_center_store_management_ordermanager);
        totalData=findContentView(R.id.seller_center_store_management_totaldata);
        goodsSales=findContentView(R.id.seller_center_store_management_goodssales);
        preferentialMange=findContentView(R.id.seller_center_store_management_preferentialmanager);

        mStoreIcon = findContentView(R.id.seller_center_userImg,false);

    }

    @Override
    protected void onStart() {
        super.onStart();
        initImageView(mStoreIcon);
    }
    //刷新图片方法
    private void initImageView(ImageView imageView){
        Uri uri = getSP().getImgUri(KEY_STOREIMG);
        if (uri!=null){
            Log.e("msatloadimg", "initImageView: by uri "+uri );
            //            ImageUtils.loadLocationImg(getContext(),uri,imageView);
            loadImg(uri,imageView);
        }else if (getUser().getHead_pic()!=null){
            Log.e("msatloadimg", "initImageView: by getHead_pic " );
            loadImg(getUser().getHead_pic(),imageView);
        }
    }
    @Override
    public void sendFialRequest(String message) {
        showToast(message);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.seller_center_return:
                finish();
                break;
            case R.id.seller_center_store_management_storemanager://店铺管理
                startNewUI(StoreManagerActivity.class);
                break;
            case R.id.seller_center_store_management_goodsmanager://商品管理
                startNewUI(GoodsManageActivity.class);
                break;
            case R.id.seller_center_store_management_ordermanager://订单管理
                startNewUI(OrderManagerActivity.class);
                break;
            case R.id.seller_center_store_management_totaldata://数据统计
                startNewUI(DataManagerActivity.class);
                break;
            case R.id.seller_center_store_management_goodssales://商品销售

                break;
            case R.id.seller_center_store_management_preferentialmanager://优惠管理
                startNewUI(CouponManagerActivity.class);
                break;
        }
    }
}
