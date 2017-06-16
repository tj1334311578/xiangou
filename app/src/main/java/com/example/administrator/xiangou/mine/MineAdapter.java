package com.example.administrator.xiangou.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;

import java.util.List;

/**
 * @author zhouzongyao
 * @Description 我页下面板块listview的适配器
 * @email 18482195579@163.com
 * @Date 2017-06-12 12:01
 */
public class MineAdapter extends BaseAdapter{
    private Context mContext;
    private List<ItemImage> Datas;

    public MineAdapter(Context context, List<ItemImage> datas) {
        this.mContext = context;
        this.Datas = datas;
    }

    @Override
    public int getCount() {
        return Datas.size();
    }

    @Override
    public Object getItem(int position) {
        return Datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MineAdapter.ViewHolder viewHolder=new MineAdapter.ViewHolder();
        if (convertView==null) {
            convertView= LayoutInflater.from(mContext).inflate(R.layout.mine_item,null,false);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.mine_item_img);
//            Log.e("MineFg", "getView: "+viewHolder.imageView);
            viewHolder.textView= (TextView) convertView.findViewById(R.id.mine_item_text);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (MineAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setImageResource(Datas.get(position).getSrc());
        viewHolder.textView.setText(Datas.get(position).getStr());
        return convertView;
    }
    public class ViewHolder{
        public ImageView imageView;
        public TextView textView;
    }
}
