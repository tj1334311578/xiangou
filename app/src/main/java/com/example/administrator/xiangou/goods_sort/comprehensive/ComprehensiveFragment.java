package com.example.administrator.xiangou.goods_sort.comprehensive;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.AutoRVAdapter;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ComprehensiveFragment extends MVPBaseFragment<ComprehensiveContract.View, ComprehensivePresenter> implements ComprehensiveContract.View {
    private RecyclerView mComprehensiveRecycle;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        getArguments().putInt("data",3);
        return setContextView(inflater,container, R.layout.recycleview_style);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void initView() {
        mComprehensiveRecycle= (RecyclerView) mContextView.findViewById(R.id.recycleview_style_recycle);
        mComprehensiveRecycle.setLayoutManager(new GridLayoutManager(getContext(),2));
        mComprehensiveRecycle.addItemDecoration(new ItemIntervalDecoration(0,4,0,10));
        List<ComprehensiveBean> lists=new ArrayList<>();
        List<String> items=new ArrayList<>();
        items.add("新品");
        items.add("爆款");
        items.add("时尚");
        lists.add(new ComprehensiveBean(R.mipmap.girl_h,"百搭抽绳连帽中长款外套",210.0,500,items));
        lists.add(new ComprehensiveBean(R.mipmap.girl_h,"百搭抽绳连帽中长款外套",210.0,500,items));
        lists.add(new ComprehensiveBean(R.mipmap.girl_h,"百搭抽绳连帽中长款外套",210.0,500,items));
        lists.add(new ComprehensiveBean(R.mipmap.girl_h,"百搭抽绳连帽中长款外套",210.0,500,items));
        lists.add(new ComprehensiveBean(R.mipmap.girl_h,"百搭抽绳连帽中长款外套",210.0,500,items));
        lists.add(new ComprehensiveBean(R.mipmap.girl_h,"百搭抽绳连帽中长款外套",210.0,500,items));
        mComprehensiveRecycle.setAdapter(new ComprehensiveAdapter(getContext(),lists));
    }

    private class ComprehensiveAdapter extends AutoRVAdapter {
        List<ComprehensiveBean> lists;
        Context mContext;
        public ComprehensiveAdapter(Context context, List<ComprehensiveBean> lists) {
            super(context,lists);
            mContext=context;
            this.lists=lists;
        }
        @Override
        protected int onCreateViewHolderID(int viewType) {
            return R.layout.goods_ranking_item;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            List<String> list=new ArrayList<>();
            holder.getCustomImageView(R.id.goods_ranking_item_img).setImageResource(lists.get(position).getImg());
            holder.getTextView(R.id.goods_ranking_item_description).setText(lists.get(position).getDescription());
            Log.e("lists", "onBindViewHolder: "+(lists.get(position).getPrice())+lists.get(position).getLocation());
            holder.getTextView(R.id.goods_ranking_item_price).setText("￥"+(lists.get(position).getPrice()));
            holder.getTextView(R.id.goods_ranking_item_distances).setText("宝贝位置：<"+lists.get(position).getLocation()+"m");
            for (int i = 0; i <lists.get(position).getItems().size() ; i++) {
                list.add(lists.get(position).getItems().get(i));
            }
            holder.getRecycleView(R.id.goods_ranking_item_recycle).setLayoutManager(new GridLayoutManager(mContext, 1, GridLayoutManager.HORIZONTAL,false));
            holder.getRecycleView(R.id.goods_ranking_item_recycle).setAdapter(new AttributeAdapter(mContext,list));
        }

        private class AttributeAdapter extends AutoRVAdapter {
            List<String> list;
            public AttributeAdapter(Context mContext, List<String> list) {
                super(mContext,list);
                this.list=list;
            }

            @Override
            protected int onCreateViewHolderID(int viewType) {
                return R.layout.ranking_item_listitem;
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                TextView textView=holder.getTextView(R.id.ranking_item_listitem);
                textView.setText(list.get(position));
                //特殊样式修改
                if (list.get(position).equals("新品")){
                    textView.setTextColor(getResources().getColor(R.color.white));
                    textView.setBackground(getResources().getDrawable(R.drawable.ranking_item_listitem_bg));
                }else{
                    textView.setTextColor(Color.parseColor("#6a747e"));
                    textView.setBackgroundColor(Color.WHITE);
                }
            }
        }
    }
}
