package com.example.administrator.xiangou.mine.mystore.datamanager;

import android.content.Context;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;

import java.util.List;

/**
 * 作者： tj on 2017/5/25.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */
public class DataManagerAdapter extends RVBaseAdapter<String> {
    public DataManagerAdapter(Context context, List<String> record) {
        super(context,record);
        setLayoutResId(R.layout.seller_center_datamanager_item);
    }
    @Override
    protected void bindData(RVBaseViewHolder holder, String recordBean, int position) {
//        String [] conditions=new String[]{"月份","交易量/件","交易金额/元","访客量/人"};
        TextView Tv=holder.getTextView(R.id.seller_center_datamanager_item_Tv);
        if (position<4){//设置第一行数据
            Tv.setTextColor(mContext.getResources().getColor(R.color.cart_textb316color));
            Tv.setText(recordBean);
        }else if (position%4==0){
            Tv.setTextColor(mContext.getResources().getColor(R.color.textcolor_gray));
            //将01数字格式的月份转化为大写格式
            Tv.setText(IntToParseCapital(recordBean));
        }else{//设置字体颜色
            Tv.setTextColor(mContext.getResources().getColor(R.color.textcolor_mine_black));
            switch (position%4){
                case 1:
                    Tv.setText(recordBean);
                    break;
                case 2:
                    Tv.setText(recordBean);
                    break;
                case 3:
                    Tv.setText(recordBean);
                    break;
            }
        }
    }

    private String IntToParseCapital(String month) {
        switch (month){
            case "01":return "一月";
            case "02":return "二月";
            case "03":return "三月";
            case "04":return "四月";
            case "05":return "五月";
            case "06":return "六月";
            case "07":return "七月";
            case "08":return "八月";
            case "09":return "九月";
            case "10":return "十月";
            case "11":return "十一月";
            case "12":return "十二月";
            default: return null;
        }
    }
}
