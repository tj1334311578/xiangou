package com.example.administrator.xiangou.mine.myfootprint;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.administrator.xiangou.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */
public class MyFootPrintAdapter extends BaseAdapter {
    private Context mContext;
    private  List<FootPrintBean> lists;
    public MyFootPrintAdapter(Context context, List<FootPrintBean> lists) {
        this.mContext=context;
        this.lists=lists;
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
        ViewHolde viewHolder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.footprintpage_item,null);
            viewHolder=new ViewHolde(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolde) convertView.getTag();
        }
        //给listview设置数据
        viewHolder.img.setImageResource(lists.get(position).getImg());
        viewHolder.description.setText(lists.get(position).getDescription());
        viewHolder.nowprice.setText("￥"+lists.get(position).getNowprice());
        viewHolder.oldprice.setText("￥"+lists.get(position).getOldprice());
        return convertView;
    }
    class ViewHolde{
        ImageView img;
        TextView description,oldprice,nowprice;
        ViewHolde(View v){
            img= (ImageView) v.findViewById(R.id.footprint_item_img);
            description= (TextView) v.findViewById(R.id.footprint_item_description);
            oldprice= (TextView) v.findViewById(R.id.footprint_item_oldprice);
            nowprice= (TextView) v.findViewById(R.id.footprint_item_newprice);
            oldprice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        }
    }
}
