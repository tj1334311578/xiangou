package com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsdetailscomment.goodsdetailscommentitem;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.adapter.CommentsAdapter;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsbean.SimpleGoodsDetialBean;
import com.example.administrator.xiangou.mvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class GoodsDetailsCommentItemFragment extends MVPBaseFragment<GoodsDetailsCommentItemContract.View, GoodsDetailsCommentItemPresenter> implements GoodsDetailsCommentItemContract.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContextView(inflater,container, R.layout.recycleview_style);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void initView() {
        RecyclerView recycle=findContentView(R.id.recycleview_style_recycle,false);
        recycle.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        /*************************模拟数据**************************/
        List<String> imgs=new ArrayList<>();
        imgs.add("/public/upload/goods/2016/04-21/57187bc13f7f6.jpg");
        imgs.add("/public/upload/goods/2016/04-21/57187bd601eaf.jpg");
        imgs.add("/public/upload/goods/2016/04-21/57187bd5dd859.jpg");
        SimpleGoodsDetialBean.DataBean.CommentBean comment=new SimpleGoodsDetialBean.DataBean.CommentBean(12,"买来送给我老公的, 嘻嘻....","貌美*****","2016-04-21",1,"/public/upload/head_pic/2017/05-15/UqSUKfvARCakYFYC8wwkTaTpa.png","",imgs);
        List<SimpleGoodsDetialBean.DataBean.CommentBean> comments=new ArrayList<>();
        comments.add(comment);
        comments.add(comment);
        comments.add(comment);
        comments.add(comment);
        comments.add(comment);
        comments.add(comment);
        comments.add(comment);
        /*************************模拟数据**************************/
        recycle.setAdapter(new CommentsAdapter(getContext(),comments));
    }
}
