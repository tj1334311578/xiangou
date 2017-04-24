package com.example.administrator.xiangou.cart;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

public class CartContract {
    interface View extends BaseView {
//        void dealAllCheckBox();
        void toUpdataView(int startpos, int count, String text);
    }

    interface  Presenter extends BasePresenter<View> {
        void dealAllCheckBox(CompoundButton buttonView, boolean isChecked );//全选Cb
        void dealStoreCheckBox(boolean isChecked, int position, CheckBox mAllGoodsCb);//店铺Cb
        void dealGoodsCheckBox(boolean isItemChecked, final int parentposition, final int chaildposition);//商品Cb

    }
}
