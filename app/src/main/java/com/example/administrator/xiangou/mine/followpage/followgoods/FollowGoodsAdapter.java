package com.example.administrator.xiangou.mine.followpage.followgoods;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
public class FollowGoodsAdapter extends BaseAdapter {
    private List<FollowGoodsBean> lists;
    private Context mContext;
    public FollowGoodsAdapter(Context context, List<FollowGoodsBean> lists) {
        this.lists=lists;
        this.mContext=context;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            final LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.followpage_goods_item,null);
            viewHolder=new ViewHolder();
            viewHolder.img=findContextView(convertView,R.id.follow_goods_item_img);
            viewHolder.description=findContextView(convertView,R.id.follow_goods_item_description);
            viewHolder.oldprice=findContextView(convertView,R.id.follow_goods_item_oldprice);
            viewHolder.nowprice=findContextView(convertView,R.id.follow_goods_item_newprice);
            viewHolder.iscoupon=findContextView(convertView,R.id.follow_goods_item_voucher);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        setData(viewHolder,position);
        return convertView;
    }

    private void setData(ViewHolder viewHolder, int position) {
//        viewHolder.img.setImageResource(lists.get(position).getImg());
//        viewHolder.description.setText(lists.get(position).getDescription());
//        viewHolder.nowprice.setText("￥"+ContextUtils.S2places(lists.get(position).getNowprice()));
//        viewHolder.oldprice.setText("￥"+ContextUtils.S2places(lists.get(position).getOldprice()));
        viewHolder.oldprice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
//        if (lists.get(position).iscoupon())
            viewHolder.iscoupon.setVisibility(View.VISIBLE);
    }

    private <T extends View>T findContextView(View convertView, int id) {
        return (T) convertView.findViewById(id);
    }

    class ViewHolder {
        ImageView img;
        TextView description;
        TextView oldprice,nowprice,iscoupon;
    }
}
