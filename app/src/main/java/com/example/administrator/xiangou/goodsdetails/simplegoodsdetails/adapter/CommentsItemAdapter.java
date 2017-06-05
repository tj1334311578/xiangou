package com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.adapter;

import android.content.Context;
import android.util.Log;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.AutoRVAdapter;
import com.example.administrator.xiangou.net.XianGouApiService;
import com.example.administrator.xiangou.tool.GlideImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
public class CommentsItemAdapter extends AutoRVAdapter {
    private List<String> imgs;
    private Context mContext;
    public CommentsItemAdapter(Context mContext, List<String> img) {
        super(mContext,img);
        this.imgs=img;
        this.mContext=mContext;
    }

    @Override
    public int getItemCount() {
        //限制图片张数
        if (imgs.size()<5)
            return imgs.size();
        else
            return 4;
    }

    @Override
    protected int onCreateViewHolderID(int viewType) {
        return R.layout.imageviewtype;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e("imgurl", "onBindViewHolder: "+position+" --------"+imgs.get(position) );
        new GlideImageLoader().displayImage(mContext, XianGouApiService.IMGBASEURL +imgs.get(position),holder.getImgeView(R.id.imageview_type_img));
    }
}
