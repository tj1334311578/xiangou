package com.example.administrator.xiangou.classification.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.AutoRVAdapter;
import com.example.administrator.xiangou.base.BaseAdapter;
import com.example.administrator.xiangou.nearby.nearbypreferential.model.PreferBean3;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 * 推荐商品页
 */
public class ClassificationAdapter1 extends AutoRVAdapter implements BaseAdapter.OnMineItemClickListener{
    private static Context context;
    private  List<String> list;
    public ClassificationAdapter1(Context context, List<String> list) {
        super(context,list);
        this.context=context;
        this.list=list;
    }

    @Override
    public int getItemCount() {
        return list.size();
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
        List alist=new ArrayList<PreferBean3>();
        alist.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        alist.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        alist.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        alist.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        Log.e("alist", "bindViewHolder1: "+alist.size() );
        RecyclerView recy=holder.getRecycleView(R.id.goods_classfication_item2_recycle);
        recy.setLayoutManager(new GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false));
        recy.setAdapter(new Classificationitem2Adapter(context,alist));
    }

    private void bindViewHolder1(ViewHolder holder) {
        List list=new ArrayList<PreferBean3>();
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        RecyclerView recy=holder.getRecycleView(R.id.goods_classfication_recycle);
        recy.addItemDecoration(new ItemIntervalDecoration(10,5,10,30));
        recy.setLayoutManager(new GridLayoutManager(context,3, LinearLayoutManager.VERTICAL,false));
        recy.setAdapter(new ClassificationitemAdapter(context,list));
    }

    @Override
    public void onMineItemClick(View view, int position) {

    }

    @Override
    public void onMineItemLongClick(View view, int position) {

    }

    private class Classificationitem2Adapter extends AutoRVAdapter {
        private  List<PreferBean3> list;
        public Classificationitem2Adapter(Context context,List list) {
            super(context,list);
            this.list=list;
        }

        @Override
        protected int onCreateViewHolderID(int viewType) {
            return R.layout.goods_classfication_item2_item;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.getImgeView(R.id.classfication_item2_item_img).setImageResource(list.get(position).getMore_img());
            holder.getTextView(R.id.classfication_item2_item_tv).setText(list.get(position).getMore_description());
        }
    }

    private class ClassificationitemAdapter extends AutoRVAdapter {
        private  List<PreferBean3> list;
        public ClassificationitemAdapter(Context context, List list) {
            super(context,list);
            this.list=list;
        }

        @Override
        protected int onCreateViewHolderID(int viewType) {
            return R.layout.goods_classfication_item1_item;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.getImgeView(R.id.classfication_item1_item_img).setImageResource(list.get(position).getMore_img());
            holder.getTextView(R.id.classfication_item1_item_tv).setText(list.get(position).getMore_description());
        }
    }
}
