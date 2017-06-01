package com.example.administrator.xiangou.mine.mystore.goodsmanage;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.tool.CustomImageView;
import com.example.administrator.xiangou.tool.DrawableTextView;

import java.util.List;

/**
 * 作者： tj on 2017/5/31.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */
public class GoodsManageMentAdapter extends RVBaseAdapter<GoodsitemBean.DataBean> {
    private TextView description,nowprice,oldprice,stock;
    private CustomImageView goods_img;
    private DrawableTextView leftBtn,centerBtn,rightBtn;
    public GoodsManageMentAdapter(Context context, List<GoodsitemBean.DataBean> data) {
        super(context, R.layout.goods_management_item,data);
    }

    @Override
    protected void bindData(RVBaseViewHolder holder, GoodsitemBean.DataBean dataBean, int position) {
        description=holder.getTextView(R.id.goods_management_item_description_tv);
        nowprice=holder.getTextView(R.id.goods_management_item_newprice);
        oldprice=holder.getTextView(R.id.goods_management_item_oldprice);
        oldprice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG| Paint.ANTI_ALIAS_FLAG);
        stock=holder.getTextView(R.id.goods_management_item_Inventory_show);
        goods_img=holder.getCustomView(R.id.goods_management_item_img);
        leftBtn=holder.getDrawableTextView(R.id.goods_management_item_eidt);
        centerBtn=holder.getDrawableTextView(R.id.goods_management_upordownBtn);
        rightBtn=holder.getDrawableTextView(R.id.goods_management_delBtn);
        if (dataBean.getIs_on_sale()==1){//商品已上架
            centerBtn.setText("下架");
        }else{//商品状态为下架
            centerBtn.setText("上架");
        }
        description.setText(dataBean.getGoods_name());
        nowprice.setText(dataBean.getShop_price());
        oldprice.setText(dataBean.getMarket_price());
        stock.setText(dataBean.getStore_count()+"");
        loadImg(dataBean.getOriginal_img(),goods_img);
    }

}
