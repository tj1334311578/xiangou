package com.example.administrator.xiangou.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.tool.CustomImageView;
import com.youth.banner.Banner;

/**
 * Created by zhouzongyao on 2017/3/6.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private Context mContext;
    private SparseArray<View> mViews;
    private View mItemView;
    protected BaseAdapter.OnMineItemClickListener mOnItemClickListener;

    public BaseViewHolder( View itemView , BaseAdapter.OnMineItemClickListener mOnItemClickListener) {
        super(itemView);
        mItemView = itemView;
        mViews = new SparseArray<>();
        this.mOnItemClickListener = mOnItemClickListener;
        itemView.setOnClickListener(this);
    }

    public static BaseViewHolder createViewHolder(Context context, ViewGroup parent, int layoutId, BaseAdapter.OnMineItemClickListener mOnItemClickListener)
    {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        BaseViewHolder holder = new BaseViewHolder(itemView,mOnItemClickListener);
        return holder;
    }

    /**
     * 通过ID获取控件
     * @param id
     * @return
     */
    public View getView(int id) {
        return findView(id);
    }
    public TextView getTextView(int id){
        return findView(id);
    }
    public ImageView getImageView(int id){
        return findView(id);
    }
    public CustomImageView getCustomView(int id){
        return findView(id);
    }
    public Button getButton(int id){
        return findView(id);
    }
    public RecyclerView getRecyclerView(int id){
        return findView(id);
    }

    public Banner getBanner(int id){
        return findView(id);
    }

    private <T extends View> T findView(int id) {
        View view = mViews.get(id);
        if (view == null) {
            view = mItemView.findViewById(id);
            mViews.put(id,view);
        }
        return (T) view;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onMineItemClick(v,getLayoutPosition());
        }
    }
}
