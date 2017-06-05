package com.example.administrator.xiangou.classification.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
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
public class ClassificationAdapter extends AutoRVAdapter implements RVBaseAdapter.OnItemViewClickListener{
    private static Context context;
    private FirstLevelBean mdata;
    public ClassificationAdapter(Context context, FirstLevelBean data) {
        super(context);
        this.context=context;
        this.mdata=data;
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    protected int onCreateViewHolderID(int viewType) {
        return R.layout.goods_classfication_item1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        new GlideImageLoader().displayImage(context, XianGouApiService.BASEURL+mdata.getData().getAdv().getImg_url(),holder.getImgeView(R.id.goods_item1_img));

        RecyclerView recy=holder.getRecycleView(R.id.goods_classfication_item1_recycle);
        recy.setLayoutManager(new GridLayoutManager(context,3, GridLayoutManager.VERTICAL,false));
        recy.addItemDecoration(new ItemIntervalDecoration(0,10,0,20));
        recy.setAdapter(new ClassificationAdapter2(context,mdata.getData().getCate()));
    }

    @Override
    public void setOnItemViewClick(View view, int pos) {

    }

    private class ClassificationAdapter2 extends AutoRVAdapter {
        private  List<FirstLevelBean.DataBean.CateBean> list;
        private Context mContext;
        public ClassificationAdapter2(Context context, List<FirstLevelBean.DataBean.CateBean> list) {
            super(context,list);
            this.list=list;
            this.mContext=context;
        }

        @Override
        protected int onCreateViewHolderID(int viewType) {
            return R.layout.goods_classfication_item1_item;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            new GlideImageLoader().displayImage(mContext,XianGouApiService.BASEURL+list.get(position).getImage(),holder.getImgeView(R.id.classfication_item1_item_img));
            holder.getTextView(R.id.classfication_item1_item_tv).setText(list.get(position).getName());
        }
    }
}
