package com.example.administrator.xiangou.goodsdetails.simplegoodsdetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;

import java.util.List;

/**
 * 作者： tj on 2017/5/24.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */
public class ColorandSizeAdapter extends RVBaseAdapter<String> {

    public ColorandSizeAdapter(Context context, List<String> data) {
        super(context,data);
        if (data.get(0).indexOf("色")!=-1){
            setLayoutResId(R.layout.simple_goodsdetails_colorandsize_coloritem);
        }else{
            setLayoutResId(R.layout.simple_goodsdetails_colorandsize_sizeitem);
        }
    }

    @Override
    protected void bindData(RVBaseViewHolder holder, String s, int position) {
        if (mDatas.get(position).indexOf("色")!=-1){
            holder.getTextView(R.id.simple_goodsdetails_colorandsize_colortext).setText(mDatas.get(position));
        }else{
            holder.getTextView(R.id.simple_goodsdetails_colorandsize_sizetext).setText(mDatas.get(position));
        }
    }


}
