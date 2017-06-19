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
import com.example.administrator.xiangou.net.XianGouApiService;
import com.example.administrator.xiangou.tool.GlideImageLoader;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ComprehensiveFragment extends MVPBaseFragment<ComprehensiveContract.View, ComprehensivePresenter> implements ComprehensiveContract.View {
    private RecyclerView mComprehensiveRecycle;
    private int tag;
    public ComprehensiveFragment (){
        this.tag=getArguments().getInt("tag",0);
    }
    public static int page_no=0;
    public static int is_new=0;
    public static String name=null;
    public static String sort=null;
    public static String sort_asc=null;
    public static boolean isasc=false;//是否升序

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
        int cat_id=getActivity().getIntent().getIntExtra("cat_id",0);
        switch (tag){
            case 0:
                page_no=0;
                is_new=0;
                name=null;
                sort=null;
                sort_asc=null;
                mPresenter.callClassificationSort(cat_id,page_no,is_new,name,null,null,sort,sort_asc);
                break;
            case 1:
                sort="sales_sum";
                mPresenter.callClassificationSort(cat_id,page_no,is_new,name,null,null,sort,sort_asc);
                break;
            case 2:
                is_new=1;
                mPresenter.callClassificationSort(cat_id,page_no,is_new,name,null,null,sort,sort_asc);
                break;
            case 3:
//                isasc=!isasc;//写在排序按钮中
//                if (isasc){
//                    sort_asc="asc";
//                }else{
//                    sort_asc=null;
//                }
                sort="shop_price";
                mPresenter.callClassificationSort(cat_id,page_no,is_new,name,null,null,sort,sort_asc);
                break;
        }
    }

    @Override
    public void sortDataToView(SortBean sortBean) {
        if (sortBean.getData()!=null){
            mComprehensiveRecycle.setAdapter(new ComprehensiveAdapter(getContext(),sortBean.getData()));
        }else{
            List<SortBean.DataBean> lists=new ArrayList<>();
            List<SortBean.ItemBean> items=new ArrayList<>();
            items.add(new SortBean.ItemBean("新品"));
            items.add(new SortBean.ItemBean("爆款"));
            items.add(new SortBean.ItemBean("时尚"));
            lists.add(new SortBean.DataBean("百搭抽绳连帽中长款外套", "/public/upload/goods/2017/04-12/R6hjhyxUK2dd8HtPX7VTr2Ugf.jpg","210.0",500,items));
            mComprehensiveRecycle.setAdapter(new ComprehensiveAdapter(getContext(),lists));
        }
    }

    private class ComprehensiveAdapter extends AutoRVAdapter {
        List<SortBean.DataBean> lists;
        Context mContext;
        public ComprehensiveAdapter(Context context, List<SortBean.DataBean> lists) {
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
            new GlideImageLoader().displayImage(mContext,XianGouApiService.IMGBASEURL+lists.get(position).getOriginal_img(),holder.getCustomImageView(R.id.goods_ranking_item_img));
            holder.getTextView(R.id.goods_ranking_item_description).setText(lists.get(position).getGoods_name());
            Log.e("lists", "onBindViewHolder: "+(lists.get(position).getShop_price())+lists.get(position).getDistance());
            holder.getTextView(R.id.goods_ranking_item_price).setText("￥"+(lists.get(position).getShop_price()));
            holder.getTextView(R.id.goods_ranking_item_distances).setText("宝贝位置：<"+lists.get(position).getDistance()+"m");
            if (lists.get(position).getSign()!=null) {//判断是否有sign标签
                for (int i = 0; i < lists.get(position).getSign().size(); i++) {//处理标签sign
                    list.add(lists.get(position).getSign().get(i).getItem());
                }
                holder.getRecycleView(R.id.goods_ranking_item_recycle).setLayoutManager(new GridLayoutManager(mContext, 1, GridLayoutManager.HORIZONTAL, false));
                holder.getRecycleView(R.id.goods_ranking_item_recycle).setAdapter(new AttributeAdapter(mContext, list));
            }else{
                holder.getRecycleView(R.id.goods_ranking_item_recycle).setVisibility(View.GONE);
            }
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
                    textView.setTextColor(getResources().getColor(R.color.whiteBgColor));
                    textView.setBackground(getResources().getDrawable(R.drawable.ranking_item_listitem_bg));
                }else{
                    textView.setTextColor(Color.parseColor("#6a747e"));
                    textView.setBackgroundColor(Color.WHITE);
                }
            }
        }
    }
}
