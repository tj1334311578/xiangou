package com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsdetailscomment;

import android.content.Context;

import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsbean.CommentBean;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsbean.commenttempBean;
import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;
import com.example.administrator.xiangou.nearby.apimodel.CommentDataBean;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class GoodsDetailsCommentContract {
    interface View extends BaseView {
        void sendDataBeanToView(commenttempBean commentBean);
    }

    interface  Presenter extends BasePresenter<View> {
      void dealCallComment(int goods_id,int page_no,String condition);
    }
}
