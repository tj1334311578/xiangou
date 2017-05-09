package com.example.administrator.xiangou.mine.followpage.followstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.tool.SelectImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
public class FollowStoreAdapter extends BaseAdapter {
    private  List<FollowStoreBean> lists;
    private Context mContext;
    public FollowStoreAdapter(Context context, List<FollowStoreBean> lists) {
        this.lists=lists;
        mContext=context;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.followpage_store_item,null);
            viewHolder=new ViewHolder();
            viewHolder.cb=findContextView(convertView,R.id.follow_store_item_checkbox);
            viewHolder.description=findContextView(convertView,R.id.follow_store_item_likemessage);
            viewHolder.follows=findContextView(convertView,R.id.follow_store_item_follows);
            viewHolder.imgbtn=findContextView(convertView,R.id.follow_store_item_imgbtn);
            viewHolder.selectImg=findContextView(convertView,R.id.follow_store_item_img);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        setData(viewHolder,position);
        return convertView;
    }

    private void setData(ViewHolder viewHolder, int position) {
        viewHolder.selectImg.setImageResource(lists.get(position).getImg());
        viewHolder.description.setText(lists.get(position).getDescription());
        viewHolder.follows.setText(lists.get(position).getFollows()+"人关注");
    }

    private <T extends View>T findContextView(View convertView, int id) {
        return (T) convertView.findViewById(id);
    }

    class ViewHolder{
        CheckBox cb;
        SelectImageView selectImg;
        TextView description,follows;
        ImageButton imgbtn;
    }
}
