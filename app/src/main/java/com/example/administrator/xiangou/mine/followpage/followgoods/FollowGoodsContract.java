package com.example.administrator.xiangou.mine.followpage.followgoods;

import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FollowGoodsContract {
    interface View extends BaseView {
        void getGoodsListSuccess(FollowGoodsBean data);
    }

    interface  Presenter extends BasePresenter<View> {
        void getCollectGoodsList(int user_id, int page_no, String goods_name);
    }
}
