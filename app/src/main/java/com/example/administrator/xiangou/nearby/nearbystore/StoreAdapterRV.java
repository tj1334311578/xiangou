package com.example.administrator.xiangou.nearby.nearbystore;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;

import java.util.List;


/**
 * Created by zhouzongyao on 2017/3/14.
 */

public class StoreAdapterRV extends RVBaseAdapter<StoreBean> {
    public StoreAdapterRV(Context context, List<StoreBean> mDatas) {
        super(context, R.layout.child_store_nearby, mDatas);
    }

    @Override
    protected void bindData(RVBaseViewHolder holder, StoreBean storeBean, int position) {
        holder.getCustomView(R.id.icon_title_store).setImageResource(storeBean.getIconId());
        holder.getTextView(R.id.text_title_store).setText(storeBean.getNameStore());
        holder.getTextView(R.id.distance_title_store).setText("< "+storeBean.getDistance()+"m");

        holder.getImageView(R.id.img1_child_store_nearby).setImageResource(storeBean.getImg1Url());
        holder.getImageView(R.id.img2_child_store_nearby).setImageResource(storeBean.getImg2Url());
        holder.getImageView(R.id.img3_child_store_nearby).setImageResource(storeBean.getImg3Url());
        holder.getTextView(R.id.discribe_child_store_nearby).setText(storeBean.getDiscribe());
        holder.getTextView(R.id.open_child_store_nearby).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "皮皮虾，我们走，去找一个蓝盆友，吃炸鸡喝啤酒，还能一起拉拉手。 \n 皮皮虾拉拉手，以后的路一起走，不想再做单身狗，人家也要捶胸口", Toast.LENGTH_LONG).show();
            }
        });
        holder.getTextView(R.id.look_store_nearby_tv).setText(storeBean.getLookCount()+" 人看过");
        holder.getTextView(R.id.like_child_store_nearby).setText(storeBean.getLikeCount()+"");
        holder.getTextView(R.id.attention_child_store_nearby).setText(storeBean.getAttentionCount()+"");
    }
}
