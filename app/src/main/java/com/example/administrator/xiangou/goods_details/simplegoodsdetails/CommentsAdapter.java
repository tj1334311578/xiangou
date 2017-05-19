package com.example.administrator.xiangou.goods_details.simplegoodsdetails;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.AutoRVAdapter;
import com.example.administrator.xiangou.net.XianGouApiService;
import com.example.administrator.xiangou.tool.CustomImageView;
import com.example.administrator.xiangou.tool.DrawableTextView;
import com.example.administrator.xiangou.tool.GlideImageLoader;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2017/5/19.
 */
public class CommentsAdapter extends AutoRVAdapter {
    private Context mContext;
    private List<SimpleGoodsDetialBean.DataBean.CommentBean> comment;
    public CommentsAdapter(Context context, List<SimpleGoodsDetialBean.DataBean.CommentBean> comment) {
        super(context,comment);
        this.mContext=context;
        this.comment=comment;
    }

    @Override
    protected int onCreateViewHolderID(int viewType) {
        return R.layout.simple_goodsdetails_comment_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.setTextView(R.id.simple_goodsdetails_comment_item_msg,comment.get(position).getContent());
        holder.setTextView(R.id.simple_goodsdetails_attribute,comment.get(position).getAdd_time());

        //设置评论用户的头像和昵称
        new GlideImageLoader().displayImage(mContext,XianGouApiService.BASEURL+comment.get(position).getHead_pic(),holder.getCustomImageView(R.id.simple_goodsdetails_comment_item_userlogo));
        holder.setTextView(R.id.simple_goodsdetails_comment_item_usernickname,comment.get(position).getUsername());
        RecyclerView recycle=holder.getRecycleView(R.id.simple_goodsdetails_recycle);
        //设置是否有数据，有显示，无gone掉
        if (comment.get(position).getImg().size()>0) {
            Log.e("commentimg", "onBindViewHolder: "+comment.get(position).getImg()+"\nsize:"+comment.get(position).getImg().size() );
            recycle.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
            recycle.setAdapter(new CommentsItemAdapter(mContext, comment.get(position).getImg()));
            recycle.addItemDecoration(new ItemIntervalDecoration(1,0,0,0));
        }else{
            recycle.setVisibility(View.GONE);
        }
    }
}
