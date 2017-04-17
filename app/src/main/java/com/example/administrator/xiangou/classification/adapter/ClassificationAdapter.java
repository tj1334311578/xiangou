package com.example.administrator.xiangou.classification.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.AutoRVAdapter;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.nearby.nearbypreferential.model.PreferBean3;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 * 推荐商品页
 */
public class ClassificationAdapter extends AutoRVAdapter implements RVBaseAdapter.OnItemViewClickListener{
    private static Context context;
    private  List<String> list;
    public ClassificationAdapter(Context context, List<String> list) {
        super(context,list);
        this.context=context;
        this.list=list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    protected int onCreateViewHolderID(int viewType) {
        return R.layout.goods_classfication_item1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        List<PreferBean3> list=new ArrayList<>();
        holder.getImgeView(R.id.goods_item1_img).setImageResource(R.mipmap.girl_v);
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        RecyclerView recy=holder.getRecycleView(R.id.goods_classfication_item1_recycle);
        recy.addItemDecoration(new ItemIntervalDecoration(20,10,20,20));
        recy.setLayoutManager(new GridLayoutManager(context,3, LinearLayoutManager.VERTICAL,false));
        recy.setAdapter(new ClassificationAdapter2(context,list));
    }


    @Override
    public void setOnItemViewClick(View view, int position) {

    }

    private class ClassificationAdapter2 extends AutoRVAdapter {
        private  List<PreferBean3> list;
        public ClassificationAdapter2(Context context, List<PreferBean3> list) {
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
