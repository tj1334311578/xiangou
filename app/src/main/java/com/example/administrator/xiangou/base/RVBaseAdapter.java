package com.example.administrator.xiangou.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.xiangou.net.XianGouApiService;
import com.example.administrator.xiangou.tool.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by zhouzongyao on 2017/3/6.
 */

public abstract class RVBaseAdapter<T> extends RecyclerView.Adapter<RVBaseViewHolder> {
    public Context mContext;
    protected List<T> mDatas;
    protected int mLayoutResId;
    protected static View mItemView;
    private  GlideImageLoader mImageLoader;

    /**
     * 提供itemview里控件的点击监听
     */
    protected OnItemViewClickListener mOnItemViewClickListener;
    public interface OnItemViewClickListener {
        void setOnItemViewClick(View view, int pos);
    }
    public void setOnItemViewClickListener(OnItemViewClickListener itemViewClickListener) {
        this.mOnItemViewClickListener = itemViewClickListener;
    }

    /**
     * 提供控件的点击监听，需要holder时可调用
     */
//    protected OnItemViewHolderListener mOnItemViewHolderListener;
//    public interface OnItemViewHolderListener {
//        void bindItemViewHolder(View view,RVBaseViewHolder holder, int pos);
//    }
//    public void setOnItemViewHolderListener(OnItemViewHolderListener itemViewHolderListener) {
//        this.mOnItemViewHolderListener = itemViewHolderListener;
//    }

    /**
     * 两参构造器，需自己调用 setLayoutResId()方法加载Item布局
     * @param context
     * @param mDatas 数据源
     */
    public RVBaseAdapter(Context context, List<T> mDatas) {
        this(context,0,mDatas);
    }

    /**
     * 三参构造器
     * @param context
     * @param mLayoutResId item布局的XML ID
     * @param mDatas 数据源
     */
    public RVBaseAdapter(Context context, int mLayoutResId, List<T> mDatas) {
        mContext = context;
        this.mDatas = mDatas;
        setLayoutResId(mLayoutResId);
        mImageLoader = new GlideImageLoader();
    }

    @Override
    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mItemView = LayoutInflater.from(mContext).inflate(mLayoutResId, parent,false);
        RVBaseViewHolder holder = new RVBaseViewHolder(mItemView, mOnItemViewClickListener);
        return holder;
    }

    /**
     * 封装onBindViewHolder方法，节省通过position get data的步骤
     * @param holder
     * @param t  list里第position的data
     * @param position
     */
    protected abstract void bindData(RVBaseViewHolder holder, T t, int position);
    @Override
    public void onBindViewHolder(RVBaseViewHolder holder, final int position) {
        T t = getItem(position);
        bindData(holder,t,position);
    }

    @Override
    public int getItemCount() {
        return mDatas!=null ? mDatas.size():0;
    }

    /**
     * 设置item的布局
     * @param layoutResId item的布局XML的ID
     */
    public void setLayoutResId(int layoutResId) {
        this.mLayoutResId = layoutResId;
    }

    //list里第position的data
    public T getItem(int position){
        return mDatas.get(position);
    }
    public List<T> getDatas(){
        return mDatas;
    }
    //清空数据源list
    public void clearData(){
        mDatas.clear();
        notifyItemRangeChanged(0,mDatas.size());
    }

    /**
     * 在position处添加同类型的数据源list
     * @param position
     * @param datas
     */
    public void addData(int position,List<T> datas){
        if (datas != null && datas.size()>0) {
            mDatas.addAll(position,datas);
            notifyItemRangeChanged(position,mDatas.size());
        }
    }
    public void addData(List<T> datas){
        addData(0,datas);
    }
    public void upData(){
//        if (mDatas!=null)
//        mDatas.clear();
//        Log.e("Data size", "onClick: "+mDatas.size() );
        notifyItemRangeChanged(0,mDatas.size());
    }


    /**
     * 提供图片加载方法
     * @param imgUrl 图片的网址（不需要加baseURL）
     * @param imageView 显示图片的imageview控件
     */
    public void loadImg(String imgUrl, ImageView imageView) {
        mImageLoader.displayImage(mContext, XianGouApiService.BASEURL+imgUrl,imageView);
    }
}
