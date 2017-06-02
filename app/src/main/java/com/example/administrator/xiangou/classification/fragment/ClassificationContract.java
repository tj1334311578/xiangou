package com.example.administrator.xiangou.classification.fragment;


import com.example.administrator.xiangou.classification.bean.FirstLevelBean;
import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

public class ClassificationContract {
    interface View extends BaseView {
        void datatoView(FirstLevelBean data);
    }

    interface  Presenter extends BasePresenter<View> {
        void callclassificationarray(int cat_id);
    }
}
