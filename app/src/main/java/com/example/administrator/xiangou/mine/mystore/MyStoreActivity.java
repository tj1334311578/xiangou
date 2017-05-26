package com.example.administrator.xiangou.mine.mystore;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mine.mystore.datamanager.DataManagerActivity;
import com.example.administrator.xiangou.mine.mystore.storemanager.StoreManagerActivity;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.tool.DrawableTextView;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyStoreActivity extends MVPBaseActivity<MyStoreContract.View, MyStorePresenter> implements MyStoreContract.View {
    private ImageButton back;
    private DrawableTextView storeMange,goodsMange,orderMange,totalData,goodsSales,preferentialMange;
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

    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void onClick(View v) {
//        if (v==back){
//            finish();
//        }
        switch (v.getId()){
            case R.id.seller_center_return:
                finish();
                break;
            case R.id.seller_center_store_management_storemanager://店铺管理
                startNewUI(StoreManagerActivity.class);
                break;
            case R.id.seller_center_store_management_goodsmanager://商品管理

                break;
            case R.id.seller_center_store_management_ordermanager://订单管理

                break;
            case R.id.seller_center_store_management_totaldata://数据统计
                startNewUI(DataManagerActivity.class);
                break;
            case R.id.seller_center_store_management_goodssales://商品销售

                break;
            case R.id.seller_center_store_management_preferentialmanager://优惠管理

                break;
        }
    }
}
