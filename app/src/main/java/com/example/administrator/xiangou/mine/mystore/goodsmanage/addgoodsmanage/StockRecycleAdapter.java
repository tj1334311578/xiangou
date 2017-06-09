package com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage.bean.StockBean;

import java.util.List;

/**
 * 作者： tj on 2017/6/8.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */
public class StockRecycleAdapter extends RVBaseAdapter<StockBean> {
    private TextView colorTv,sizeTv,delTv;
    private EditText stockEdit;

    public void setStockEditListener(StockEditListener stockEditListener) {
        this.stockEditListener = stockEditListener;
    }

    private StockEditListener stockEditListener;

    private  interface StockEditListener {
        void stockChangeListener();
    }

    public StockRecycleAdapter(Context context, List<StockBean> mDatas) {
        super(context, R.layout.goods_model_recyclel_item, mDatas);
        Log.e("datasize", "StockRecycleAdapter: "+mDatas.size() );
    }


    @Override
    public int getItemCount() {
        return mDatas==null?0:mDatas.size();
    }

    @Override
    protected void bindData(RVBaseViewHolder holder, StockBean stockBean, final int position) {
        colorTv=holder.getTextView(R.id.goods_model_recycle_color);
        sizeTv=holder.getTextView(R.id.goods_model_recycle_size);
        delTv=holder.getTextView(R.id.goods_model_recycle_delete);
        stockEdit=holder.getEditText(R.id.goods_model_recycle_stock);
        if (position==0){
            colorTv.setText("颜色");
            sizeTv.setText("尺码");
            stockEdit.setText("库存/件");
            stockEdit.setFocusable(false);
            Log.e("position==0", "bindData: "+mDatas.toString() );
            stockEdit.setBackgroundColor(mContext.getResources().getColor(R.color.white_edf2f6));
            delTv.setText("");
        }else if (position!=0){
            colorTv.setText(mDatas.get(position).getColor());
            sizeTv.setText(mDatas.get(position).getSize());
            stockEdit.setText(mDatas.get(position).getStock());
            stockEdit.setBackgroundColor(mContext.getResources().getColor(R.color.blue_c2def8));
            delTv.setText("删除");
            stockEdit.setFocusable(true);
            delTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
            Log.e("position!=0", "bindData: "+mDatas.toString() );

        }
        Log.e("position", "bindData: "+position+"mdata:"+mDatas.toString()+"focusable:"+stockEdit.isFocusable()+"position:"+position);
    }
}
