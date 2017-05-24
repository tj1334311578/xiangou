package com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.SimpleGoodsDetialBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22.
 */
public class SimpleGoodsParameterAdapter extends BaseAdapter {
    private Context mContext;
    private List<SimpleGoodsDetialBean.DataBean.GoodsAttrBean> goods_attr;
    public SimpleGoodsParameterAdapter(Context context, List<SimpleGoodsDetialBean.DataBean.GoodsAttrBean> goods_attr) {
        this.mContext=context;
        this.goods_attr=goods_attr;
    }

    @Override
    public int getCount() {
        return goods_attr.size();
    }

    @Override
    public Object getItem(int position) {
        return goods_attr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh=null;
        if (convertView==null){
            vh=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.simple_goodsdetails_productparameter_item,null);
            vh.parameterTv= (TextView) convertView.findViewById(R.id.simplegoodsdetails_productparameter_parameter);
            vh.valueTv= (TextView) convertView.findViewById(R.id.simplegoodsdetails_productparameter_value);
            convertView.setTag(vh);
        }else{
            vh= (ViewHolder) convertView.getTag();
        }
            vh.parameterTv.setText(goods_attr.get(position).getAttr_name());
            vh.valueTv.setText(goods_attr.get(position).getAttr_value());
        return convertView;
    }

    class ViewHolder{
        TextView parameterTv,valueTv;
    }
}
