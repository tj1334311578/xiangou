package com.example.administrator.xiangou.home.adapter;

import android.content.Context;
import android.view.View;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.home.model.HomeDataBean;

import java.util.List;

/**
 * @author zhouzongyao
 * @Description 首页推荐的Rv适配器
 * @email 18482195579@163.com
 * @Date 2017-06-05 16:36
 */
public class RecommendAdapterRv extends RVBaseAdapter<HomeDataBean.DataBean.RecommenedListBean>{

    public RecommendAdapterRv(Context context, int mLayoutResId, List<HomeDataBean.DataBean.RecommenedListBean> mDatas) {
        super(context, mLayoutResId, mDatas);
    }

    @Override
    protected void bindData(RVBaseViewHolder holder, HomeDataBean.DataBean.RecommenedListBean recommenedListBean, int position) {
        if (recommenedListBean.getOriginal_img()!=null)
            loadImg(recommenedListBean.getOriginal_img(),holder.getCustomView(R.id.img_item_recommend_home_civ));
        if (recommenedListBean.getIs_new()==1){
            holder.getTextView(R.id.status_item_recommend_home_tv).setVisibility(View.VISIBLE);
        }
        holder.getTextView(R.id.description_item_recommend_home_tv).setText(recommenedListBean.getGoods_name());
        holder.getTextView(R.id.price_item_recommend_home_tv).setText("￥"+recommenedListBean.getShop_price());
        holder.getTextView(R.id.distance_item_recommend_home_tv).setText("宝贝的位子：< "+recommenedListBean.getDistance()+"m");
    }
}
