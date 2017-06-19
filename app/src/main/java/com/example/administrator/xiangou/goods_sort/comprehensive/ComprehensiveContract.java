package com.example.administrator.xiangou.goods_sort.comprehensive;

import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ComprehensiveContract {
    interface View extends BaseView {
        void sortDataToView(SortBean sortBean);
    }

    interface  Presenter extends BasePresenter<View> {
        void callClassificationSort(int cat_id,int page_no,int is_new,String name,String map_x,String map_y,String sort,String sort_asc);
    }
}
