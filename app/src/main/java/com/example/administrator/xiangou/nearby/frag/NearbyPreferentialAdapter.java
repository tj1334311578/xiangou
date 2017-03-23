package com.example.administrator.xiangou.nearby.frag;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.AutoRVAdapter;
import com.example.administrator.xiangou.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/15.
 */

public class NearbyPreferentialAdapter extends AutoRVAdapter implements BaseAdapter.OnMineItemClickListener{
    private static Context context;
    private  List<String> list;
    RecyclerView dayRecycer,hourRecycler,moreRecycler;
//    //无参构造用于设置监听
//    public NearbyPreferentialAdapter(){}
//
    public NearbyPreferentialAdapter(Context context, List<String> list) {
        super(context, list);
        this.context=context;
        this.list=list;
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    /**
     *   设置item的类型
     * @param position
     * @return
     */
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

    /**
     * 不同类型item的布局
     * @param viewType
     * @return
     */
    @Override
    protected int onCreateViewHolderID(int viewType) {
        switch (viewType){
            case TYPE_1:
                return R.layout.nearby_item_day;
            case TYPE_2:
                return R.layout.nearby_item_hour;
            case TYPE_3:
                return R.layout.nearby_item_more;
            default:
                return R.layout.nearby_item_day;
        }
    }

    /**
     * 第一层recycle的item设置数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch(position){
            case 0:bindHolder1(holder);
                break;
            case 1:bindHolder2(holder);
                break;
            case 2:bindHolder3(holder);
                break;
            default:bindHolder1(holder);
                break;
        }
    }

    private void bindHolder3(ViewHolder holder) {
        List<PreferBean3> list=new ArrayList<>();
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        list.add(new PreferBean3(R.mipmap.girl_h,"欧兰雅护肤品套装"));
        (moreRecycler=holder.getRecycleView(R.id.item_more_recycleView)).setLayoutManager(new GridLayoutManager(context, 3,GridLayoutManager.VERTICAL,false));
        holder.getRecycleView(R.id.item_more_recycleView).setAdapter(new PrefertialAdapter3(context,list));
    }

    private void bindHolder2(ViewHolder holder) {
        //配置第一级recycle item数据
        holder.getTextView(R.id.item_hour_hour).setText("48");
        holder.getTextView(R.id.item_hour_min).setText("00");
        holder.getTextView(R.id.item_hour_sec).setText("00");
        //配置第二级recycle item数据
        List<PreferBean2> list=new ArrayList<PreferBean2>();
        list.add(new PreferBean2(48,48,00,R.mipmap.girl_h,"下一场开抢时间：9:00","海量顶级包包等你入手","及访问jfiejfijifjijijfiejifjeijf饿哦发 描述详细",289.10,362.10));
        list.add(new PreferBean2(48,48,00,R.mipmap.girl_h,"下一场开抢时间：9:00","海量顶级包包等你入手","及访问jfiejfijifjijijfiejifjeijf饿哦发 描述详细",289.10,362.10));
        (hourRecycler=holder.getRecycleView(R.id.item_hour_recycleView)).setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        holder.getRecycleView(R.id.item_hour_recycleView).setAdapter(new PrefertialAdapter2(context,list));
    }

    private void bindHolder1(ViewHolder holder) {
        List list=new ArrayList<PreferBean1>();
        list.add(new PreferBean1(R.mipmap.girl_h,"jfeifjeijfiei",151.32,165.30));
        list.add(new PreferBean1(R.mipmap.girl_h,"jfeifjeijfiei",151.32,165.30));
        list.add(new PreferBean1(R.mipmap.girl_h,"jfeifjeijfiei",151.32,165.30));
        list.add(new PreferBean1(R.mipmap.girl_h,"jfeifjeijfiei",151.32,165.30));
//        Log.e("tag", "bindHolder1: "+holder.getRecycleView(R.id.item_day_recycle).getId());
        (dayRecycer=holder.getRecycleView(R.id.item_day_recycle)).setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.getRecycleView(R.id.item_day_recycle).setAdapter(new PrefertialAdapter1(context,list));


    }

    @Override
    public void onMineItemClick(View view, int position) {
        if (view.getParent() == dayRecycer) {
            Toast.makeText(context, "mClothingRv "+position+" 瞧一瞧，看一看了啊", Toast.LENGTH_SHORT).show();
        }else if (view.getParent() == hourRecycler) {
            Toast.makeText(context, "mMakeupRv "+position+" 绝对是假的", Toast.LENGTH_SHORT).show();
        }else if (view.getParent() == moreRecycler) {
            Toast.makeText(context, "mHouseholdsRv "+position+" 巴适得板", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMineItemLongClick(View view, int position) {


    }

//
//    /**
//     * 附近优惠设置点击事件
//     * @param v
//     */
//    @Override
//    public void onClick(View v) {
//        switch(v.getId()){
//            //一级hourItem recycleView
//            case R.id.item_hour_hour:
//                ((TextView)v).setText("27");
//                break;
//            case R.id.item_hour_min:
//                break;
//            case R.id.item_hour_sec:
//                break;
//            case R.id.item_hour:
//                break;
//
//            //二级hourItem recycleViewItem
//            case R.id.item_hour_item_img:
//                break;
//            case R.id.item_hour_item_description1:
//                break;
//            case R.id.item_hour_item_description2:
//                break;
//            case R.id.item_hour_item_originalprice:
//                break;
//            case R.id.item_hour_item_presentprice:
//                break;
//            case R.id.item_hour_item_show:
//                break;
//
//            //二级dayItem recycleViewItem
//            case R.id.item_day_item_img:
//                Toast.makeText(context, "item_day_item_img点击了", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.item_day_item_description:
//                Toast.makeText(context, "item_day_item_description", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.item_day_item_presentprice:
//                Toast.makeText(context, "item_day_item_presentprice", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.item_day_item_originalprice:
//                Toast.makeText(context, "item_day_item_originalprice", Toast.LENGTH_SHORT).show();
//                break;
//
//            //二级更多moreItem recycleViewItem
//            case R.id.item_more_item_img:
//                break;
//            case R.id.item_more_item_text:
//                break;
//        }
//    }



    /**
     * 第二层的recycleView设置adapter所需要的适配器
     */
        public static class PrefertialAdapter1 extends AutoRVAdapter{

        //自定义RecycleViewItemClickListener接口
//        public interface OnItemClickListener {
//            void onItemClick(View view,int position);
//            void onItemLongClick(View view,int position);
//        }
//        private OnItemClickListener mOnItemClickListener;
//
//        public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
//            this.mOnItemClickListener=mOnItemClickListener;
//        }
//

        List<PreferBean1> list;
        public PrefertialAdapter1(Context context, List<PreferBean1> list) {
            super(context,list);
            this.list=list;
        }

        @Override
        protected int onCreateViewHolderID(int viewType) {
            return R.layout.nearby_item_day_item;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.getImgeView(R.id.item_day_item_img).setImageResource(list.get(position).getDay_img());
            holder.getTextView(R.id.item_day_item_description).setText(list.get(position).getDay_description());
            //原价效果设置
            holder.getTextView(R.id.item_day_item_originalprice).setText("￥"+list.get(position).getDay_price1());
            //中间加横线
            holder.getTextView(R.id.item_day_item_originalprice).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            //现价
            holder.getTextView(R.id.item_day_item_presentprice).setText("￥"+list.get(position).getDay_price2());
            //控件设置监听
//            holder.getTextView(R.id.item_day_item_description).setOnClickListener(this);
//            holder.getImgeView(R.id.item_day_item_img).setOnClickListener(this);
//            holder.getTextView(R.id.item_day_item_originalprice).setOnClickListener(this);
//            holder.getTextView(R.id.item_day_item_presentprice).setOnClickListener(this);
//                if (mOnItemClickListener!=null){
////                 holder.getViewHolder(R.layout.nearby_item_day_item)
//                }

        }

//        @Override
//        public void onClick(View v) {
//            switch (v.getId()){
//                //二级dayItem recycleViewItem
//                case R.id.item_day_item_img:
//                    Toast.makeText(context, "item_day_item_img点击了", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.item_day_item_description:
//                    Toast.makeText(context, "item_day_item_description", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.item_day_item_presentprice:
//                    Toast.makeText(context, "item_day_item_presentprice", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.item_day_item_originalprice:
//                    Toast.makeText(context, "item_day_item_originalprice", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.item_day_recycle:
//                    break;
//            }
//
//        }
    }

    private class PrefertialAdapter2 extends AutoRVAdapter {
        List<PreferBean2> list;
        public PrefertialAdapter2(Context context, List<PreferBean2> list) {
            super(context,list);
            this.list = list;
        }

        @Override
        protected int onCreateViewHolderID(int viewType) {
            return R.layout.nearby_item_hour_item;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.getImgeView(R.id.item_hour_item_img).setImageResource(list.get(position).getHour_img());
            holder.getTextView(R.id.item_hour_item_description1).setText(list.get(position).getHour_description1());
            holder.getTextView(R.id.item_hour_item_description2).setText(list.get(position).getGetHour_description2());
            //原价
            holder.getTextView(R.id.item_hour_item_originalprice).setText("￥ "+list.get(position).getHour_price1());
            holder.getTextView(R.id.item_hour_item_originalprice).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.getTextView(R.id.item_hour_item_presentprice).setText("￥ "+list.get(position).getHour_price2());
        }
    }

    private class PrefertialAdapter3 extends AutoRVAdapter {
        private  List<PreferBean3> list;

        public PrefertialAdapter3(Context context, List<PreferBean3> list) {
            super(context, list);
            this.list = list;
        }

        @Override
        protected int onCreateViewHolderID(int viewType) {
            return R.layout.nearby_item_more_item;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.getImgeView(R.id.item_more_item_img).setImageResource(list.get(position).getMore_img());
            holder.getTextView(R.id.item_more_item_text).setText(list.get(position).getMore_description());
        }
    }

}
