package com.example.administrator.xiangou.mine.couponpage;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class CouponPageActivity extends MVPBaseActivity<CouponPageContract.View, CouponPagePresenter> implements CouponPageContract.View {
    private ImageView back;
    private TextView centerTv,rightTv;
    private RecyclerView recycle,unrecycle;
    private List<CouponBean> valid_lists,invalid_lists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.couponpage);
        initView();
    }

    private void initView() {
        back=findContentView(R.id.setting_head_back);
        rightTv=findContentView(R.id.setting_head_right);
        centerTv=findContentView(R.id.setting_head_center,false);
        recycle=findContentView(R.id.couponpage_Recycle,false);
        unrecycle=findContentView(R.id.couponpage_unRecycle,false);
        centerTv.setText("我的优惠券");
        rightTv.setText("使用规则");
        rightTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);

        valid_lists=new ArrayList<>();
        valid_lists.add(new CouponBean(1,10.00,199,"2017.01.01～2017.06.01",true));
        valid_lists.add(new CouponBean(2,10.00,199,"2017.01.01～2017.06.01",true));
        valid_lists.add(new CouponBean(1,10.00,199,"2017.01.01～2017.06.01",true));
        invalid_lists=new ArrayList<>();
        invalid_lists.add(new CouponBean(1,10.00,199,"2017.01.01～2017.06.01",false));
        invalid_lists.add(new CouponBean(2,10.00,199,"2017.01.01～2017.06.01",false));
        invalid_lists.add(new CouponBean(1,10.00,199,"2017.01.01～2017.06.01",false));
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setAdapter(new CouponPageAdapter(this,valid_lists));
        unrecycle.setLayoutManager(new LinearLayoutManager(this));
        unrecycle.setAdapter(new CouponPageAdapter(this,invalid_lists));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_head_back:
                finish();
        }
    }

    @Override
    public void sendFialRequest(String message) {

    }
}
