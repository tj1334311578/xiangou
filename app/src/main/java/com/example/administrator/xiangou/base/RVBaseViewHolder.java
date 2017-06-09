package com.example.administrator.xiangou.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.tool.CustomImageView;
import com.example.administrator.xiangou.tool.DrawableTextView;
import com.youth.banner.Banner;

/**
 * Created by zhouzongyao on 2017/3/6.
 */

public class RVBaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private SparseArray<View> mViews;
//    private View mItemView;
    private RVBaseAdapter.OnItemViewClickListener mOnItemViewClickListener;

    public RVBaseViewHolder(View itemView , final RVBaseAdapter.OnItemViewClickListener onItemViewClickListener ) {
        super(itemView);
//         = itemView;
        mViews = new SparseArray<>();
        mOnItemViewClickListener = onItemViewClickListener;
        itemView.setOnClickListener(this);
    }

    public RVBaseViewHolder(View itemView ) {
        this(itemView,null);
    }

    /**
     * 通过ID获取控件
     * @param id
     * @return
     */
    public EditText getEditText(int id){
        return findView(id);
    }
    public DrawableTextView getDrawableTextView(int id){return findView(id);}
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
    public CheckBox getCheckBox(int id) {
        CheckBox cbView = findView(id);
        return cbView;
    }

    public Banner getBanner(int id){
        return findView(id);
    }

    private <T extends View> T findView(int id) {
        View view = mViews.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            mViews.put(id,view);
        }
        return (T) view;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemViewClickListener!=null)
            mOnItemViewClickListener.setOnItemViewClick(v,getLayoutPosition());
    }
}
