package com.example.administrator.xiangou.nearby.nearbystore;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;
import com.example.administrator.xiangou.nearby.apimodel.NearbyStoreApiDataBean;

import java.util.List;


/**
 * Created by zhouzongyao on 2017/3/14.
 */

public class StoreAdapterRV extends RVBaseAdapter<NearbyStoreApiDataBean.DataBean> {

    private ImageView[] goodsImgs;

    public StoreAdapterRV(Context context, List<NearbyStoreApiDataBean.DataBean> dataList) {
        super(context, R.layout.child_store_nearby, dataList);
        goodsImgs = new ImageView[3];
    }

    @Override
    protected void bindData(RVBaseViewHolder holder, NearbyStoreApiDataBean.DataBean storeBean, final int position) {
//        (CustomImageView) holder.getView(R.id.title_nearby_store_child).findViewById(R.id.icon_title_store)
        Log.e("storebean", "bindData: " +storeBean.toString() );
        loadImg(storeBean.getLogo(), holder.getCustomView(R.id.icon_title_store));
        holder.getTextView(R.id.text_title_store).setText(storeBean.getName());
        holder.getTextView(R.id.distance_title_store).setText("< "+storeBean.getDistance()+"m");
        //.getView(R.id.title_nearby_store_child).findViewById
        goodsImgs[0] = holder.getImageView(R.id.img1_child_store_nearby);
        goodsImgs[1] = holder.getImageView(R.id.img2_child_store_nearby);
        goodsImgs[2] = holder.getImageView(R.id.img3_child_store_nearby);
        for (int i = 0; i < goodsImgs.length; i++) {
            if (storeBean.getGoods_list().size()>0 && storeBean.getGoods_list().get(i).getOriginal_img()!=null) {
                loadImg(storeBean.getGoods_list().get(i).getOriginal_img(), goodsImgs[i]);
            }
        }
        holder.getTextView(R.id.discribe_child_store_nearby).setText(storeBean.getSynopsis());
        holder.getTextView(R.id.open_child_store_nearby).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,position+ ": 皮皮虾，我们走，去找一个蓝盆友，吃炸鸡喝啤酒，还能一起拉拉手。 \n 皮皮虾拉拉手，以后的路一起走，不想再做单身狗，人家也要捶胸口", Toast.LENGTH_LONG).show();
            }
        });

        holder.getTextView(R.id.attention_child_store_nearby).setText(storeBean.getFollow()+"");
        holder.getTextView(R.id.open_child_store_nearby).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,position+ "--皮皮虾", Toast.LENGTH_LONG).show();
            }
        });
    }

}
