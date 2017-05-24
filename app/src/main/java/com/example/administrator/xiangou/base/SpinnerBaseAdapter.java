package com.example.administrator.xiangou.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.xiangou.R;

import java.util.List;

/**
 * @author zhouzongyao
 * @Description Spinner的适配器
 * @email 18482195579@163.com
 * @Date 2017-05-11 11:11
 */
public abstract class SpinnerBaseAdapter<T> extends BaseAdapter{
    private List<T> mListData;
    private Context mContext;
    private int mLayoutId;

    public SpinnerBaseAdapter(Context context, int layoutId, List<T> listData) {
        mContext = context;
        mLayoutId = layoutId;
        mListData = listData;
    }

    @Override
    public int getCount() {
        return mListData!=null ? mListData.size():0;
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mLayoutId,null);
        if (convertView !=null){
            TextView textView = (TextView) convertView.findViewById(R.id.itemtv_spinner_applystore);
            bindDataToView(textView,position);
        }
        return convertView;
    }

    public abstract void bindDataToView(TextView textView, int position);
}
