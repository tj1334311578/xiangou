package com.example.administrator.xiangou.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.tool.CustomImageView;
import com.example.administrator.xiangou.tool.DrawableTextView;

import java.util.List;


/**
 * Created by Administrator on 2017/3/9.
 * RecyclerView基类
 */

public abstract class AutoRVAdapter extends RecyclerView.Adapter<AutoRVAdapter.RVHolder> {
    public List<?> list;
    private Context context;
    //无参构造
    public AutoRVAdapter(){}
    //有参构造
    public AutoRVAdapter(Context context, List<?> list) {
        this.list = list;
        this.context = context;
    }
    //type
    public static final int TYPE_1=0xff01;
    public static final int TYPE_2=0xff02;
    public static final int TYPE_3=0xff03;
    public static final int TYPE_4=0xff04;
    public static final int TYPE_MAIN=0xffff;

    @Override
    public RVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(onCreateViewHolderID(viewType), parent,false);
        return new RVHolder(view);
    }

    protected abstract int onCreateViewHolderID(int viewType);

    @Override
    public void onViewRecycled(final RVHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public void onBindViewHolder(final RVHolder holder, final int position) {
        onBindViewHolder(holder.getViewHolder(), position);
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(null, v, holder.getPosition(), holder.getItemId());
                }
            });
        }
    }
    public abstract void onBindViewHolder(ViewHolder holder, int position);

//    public abstract void setItemData(List<?> list);

    public int getItemCount() {
        return list.size();
    }

    private AdapterView.OnItemClickListener onItemClickListener;

    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * RVHolder类
     * 用于缓存设置控件
     */

    public class RVHolder extends RecyclerView.ViewHolder {
        private ViewHolder viewHolder;

        public RVHolder(View view) {
            super(view);
            viewHolder= ViewHolder.getViewHolder(view);
        }

        public ViewHolder getViewHolder(){
            return viewHolder;
        }
    }


    /**
     * ViewHolder类
     * 缓存视图
     * 查找布局
     */

    public static class ViewHolder {
        private SparseArray<View> viewHolder;
        private View view;

        public static ViewHolder getViewHolder(View view) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            if (viewHolder == null) {
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            }
            return viewHolder;
        }

        private ViewHolder(View view) {
            this.view = view;
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }

        public <T extends View> T get(int id) {
            View childView = viewHolder.get(id);
            if (childView == null) {
                childView = view.findViewById(id);
                viewHolder.put(id, childView);
            }
            return (T) childView;
        }

        public View getConverView() {
            return view;
        }

        public TextView getTextView(int id) {
            return get(id);
        }

        public Button getButton(int id) {
            return get(id);
        }

        public ImageView getImgeView(int id) {
            return get(id);
        }

        public void setTextView(int id, CharSequence charSequence) {
            getTextView(id).setText(charSequence);
        }

        public DrawableTextView getDrawableTextView(int id){
            return get(id);
        }
        public RecyclerView getRecycleView(int id){
            return get(id);
        }
        public CustomImageView getCustomImageView(int id){return get(id);}

    }
}
