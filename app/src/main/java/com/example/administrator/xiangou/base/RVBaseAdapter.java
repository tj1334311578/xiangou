package com.example.administrator.xiangou.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/6.
 */

public abstract class RVBaseAdapter<T> extends RecyclerView.Adapter<RVBaseViewHolder> {
    public Context mContext;
    protected List<T> mDatas;
    protected  int mLayoutResId;

    /**
     * 提供item监听
     */
    protected OnMineItemClickListener mOnItemClickListener;

    public interface OnMineItemClickListener {
        void onMineItemClick(View view, int position);
//        void onMineItemLongClick(View view, int position);
    }
    public void setOnMineItemClickListener(OnMineItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public RVBaseAdapter(Context context, List<T> mDatas) {
        mContext = context;
        this.mDatas = mDatas;
        setLayoutResId(mLayoutResId);
    }
    public RVBaseAdapter(Context context, int mLayoutResId, List<T> mDatas) {
        mContext = context;
        this.mDatas = mDatas;
        setLayoutResId(mLayoutResId);
    }

    protected abstract void bindData(RVBaseViewHolder holder, T t, int position);

    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RVBaseViewHolder holder = RVBaseViewHolder.createViewHolder(mContext,parent,mLayoutResId);
        return holder;
    }

    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, int position) {
        T t = getItem(position);
        bindData(holder,t,position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setLayoutResId(int layoutResId) {
        this.mLayoutResId = layoutResId;
    }

    public T getItem(int position){
        return mDatas.get(position);
    }

    public List<T> getDatas(){
        return mDatas;
    }
    public void clearData(){
        mDatas.clear();
        notifyItemRangeChanged(0,mDatas.size());
    }
    public void addData(List<T> datas){
        addData(0,datas);
    }
    public void addData(int position,List<T> datas){
        if (datas != null && datas.size()>0) {
            mDatas.addAll(datas);
            notifyItemRangeChanged(position,mDatas.size());
        }
    }

}
