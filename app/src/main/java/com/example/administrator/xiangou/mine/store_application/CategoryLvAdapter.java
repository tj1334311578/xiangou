package com.example.administrator.xiangou.mine.store_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mine.store_application.model.CategoryListDataBean;

import java.util.List;

/**
 * @author zhouzongyao
 * @email 18482195579@163.com
 * @Date 2017-05-13 15:19
 */
public class CategoryLvAdapter extends BaseAdapter {
    private Context mContext;
    private List<CategoryListDataBean.DataBean> mDataBeanList;
    LayoutInflater mLayoutInflater;

    public interface CategoryCheckedListener{
        void dealAdapterItemChecked(CompoundButton buttonView, boolean isChecked, int position);
    }
    CategoryCheckedListener mCategoryCheckedListener;
    public void setCategoryCheckedListener(CategoryCheckedListener mCategoryCheckedListener){
        this.mCategoryCheckedListener = mCategoryCheckedListener;
    }

    public CategoryLvAdapter(Context context, List<CategoryListDataBean.DataBean> dataBeanList) {
        mContext = context;
        mDataBeanList = dataBeanList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mDataBeanList!=null ? mDataBeanList.size():0;
    }

    @Override
    public Object getItem(int position) {
        return mDataBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder = null;
        if (convertView==null){
            viewHolder = new MyViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.select_category_item,null,false);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.select_category_item_tv);
            viewHolder.mCheckBox = (CheckBox) convertView.findViewById(R.id.select_category_item_selectCb);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }
        viewHolder.mTextView.setText(mDataBeanList.get(position).getName());
        viewHolder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCategoryCheckedListener.dealAdapterItemChecked(buttonView,isChecked,position);
            }
        });
        return convertView;
    }

    class MyViewHolder{
        private TextView mTextView;
        private CheckBox mCheckBox;
    }
}
