package com.example.administrator.xiangou.mine.mystore.goodsmanage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage.AddGoodsManageActivity;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.List;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class GoodsManageActivity extends MVPBaseActivity<GoodsManageContract.View, GoodsManagePresenter> implements GoodsManageContract.View {
    private ImageView backBtn;
    private Button addGoodsBtn;
    private RecyclerView recycle;
    private TextView titleTv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_management_page);
        mPresenter.callGoodsManagerList(1,null,0,1);
        initView();
    }

    private void initView() {
        backBtn=findContentView(R.id.store_headback);
        addGoodsBtn=findContentView(R.id.goods_management_page_addBtn);
        recycle=findContentView(R.id.goods_management_page_recycle,false);
        titleTv=findContentView(R.id.store_headTitleTv,false);
        titleTv.setText("商品管理");
        recycle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));//设置纵向布局
//        recycle.setAdapter(new GoodsManageMentAdapter(this,data));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.store_headback://返回上一级
                finish();
                break;
            case R.id.goods_management_page_addBtn://添加商品控件
                startNewUICarryStr(AddGoodsManageActivity.class,"statu",0);
                break;
        }
    }

    @Override
    public void sendFialRequest(String message) {

    }

//    @Override
//    public void dataToView(GoodsitemBean data) {
////        recycle.setAdapter(new  GoodsManageMentAdapter(this,data.getData()));
//    }

    @Override
    public void dataToView(List<GoodsitemBean.DataBean> dataList) {
        recycle.setAdapter(new GoodsManageMentAdapter(this,dataList));
        recycle.addItemDecoration(new ItemIntervalDecoration(0,10,0,0));
    }
}
