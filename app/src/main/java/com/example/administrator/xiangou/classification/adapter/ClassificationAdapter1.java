package com.example.administrator.xiangou.classification.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.AutoRVAdapter;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.classification.bean.FirstLevelBean;
import com.example.administrator.xiangou.net.XianGouApiService;
import com.example.administrator.xiangou.tool.GlideImageLoader;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 * 推荐商品页
 */
public class ClassificationAdapter1 extends AutoRVAdapter implements RVBaseAdapter.OnItemViewClickListener{
    private static Context context;
    private FirstLevelBean mdata;
    public ClassificationAdapter1(Context context, FirstLevelBean data) {
        super(context);
        this.context=context;
        this.mdata=data;

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return TYPE_1;
            case 1:
                return TYPE_2;
            case 2:
                return TYPE_3;
            default:
                return TYPE_MAIN;
        }
    }

    @Override
    protected int onCreateViewHolderID(int viewType) {
        switch (viewType){
            case TYPE_1:
                return R.layout.goods_classfication_recycle;
            case TYPE_2:
                return R.layout.goods_classfication_item2;
            default:
                return R.layout.goods_classfication_recycle;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (position){
            case 0:
                bindViewHolder1(holder);
                break;
            case 1:
                bindViewHolder2(holder);
                break;
            default:
                bindViewHolder1(holder);
                break;
        }
    }

    private void bindViewHolder2(ViewHolder holder) {

        RecyclerView recy=holder.getRecycleView(R.id.goods_classfication_item2_recycle);
        recy.setLayoutManager(new GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false));
        recy.setAdapter(new Classificationitem2Adapter(context,mdata.getData().getRecommend()));
    }

    private void bindViewHolder1(ViewHolder holder) {

        RecyclerView recy=holder.getRecycleView(R.id.goods_classfication_recycle);
        recy.addItemDecoration(new ItemIntervalDecoration(10,5,10,30));
        recy.setLayoutManager(new GridLayoutManager(context,3, LinearLayoutManager.VERTICAL,false));
        recy.setAdapter(new ClassificationitemAdapter(context,mdata.getData().getHot_cate()));
    }


    @Override
    public void setOnItemViewClick(View view, int pos) {

    }

    private class Classificationitem2Adapter extends AutoRVAdapter {
        private  List<FirstLevelBean.DataBean.RecommendBean> list;
        private Context mContext;
        public Classificationitem2Adapter(Context context, List<FirstLevelBean.DataBean.RecommendBean> list) {
            super(context,list);
            this.list=list;
            this.mContext=context;
        }

        @Override
        protected int onCreateViewHolderID(int viewType) {
            return R.layout.goods_classfication_item2_item;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            new GlideImageLoader().displayImage(mContext,XianGouApiService.BASEURL+list.get(position).getImage(),holder.getImgeView(R.id.classfication_item2_item_img));
            holder.getTextView(R.id.classfication_item2_item_tv).setText(list.get(position).getName());
        }
    }

    private class ClassificationitemAdapter extends AutoRVAdapter {
        private Context mContext;
        private  List<FirstLevelBean.DataBean.HotCateBean> list;
        public ClassificationitemAdapter(Context context, List<FirstLevelBean.DataBean.HotCateBean> list) {
            super(context,list);
            this.list=list;
            mContext=context;
        }

        @Override
        protected int onCreateViewHolderID(int viewType) {
            return R.layout.goods_classfication_item1_item;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            new GlideImageLoader().displayImage(mContext, XianGouApiService.BASEURL+list.get(position).getImage(),holder.getImgeView(R.id.classfication_item1_item_img));
            holder.getTextView(R.id.classfication_item1_item_tv).setText(list.get(position).getName());
        }
    }
}
