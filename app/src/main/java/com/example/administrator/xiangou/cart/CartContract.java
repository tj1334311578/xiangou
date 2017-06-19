package com.example.administrator.xiangou.cart;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.cart.model.CartListDataBean;
import com.example.administrator.xiangou.cart.model.CartMergeBean;
import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

import java.util.List;

public class CartContract {
    interface View extends BaseView {
//        void dealAllCheckBox();
        void toUpdataView(int startpos, int count, String text);
    }

    interface  Presenter extends BasePresenter<View> {
        void getCartListDataCall(int user_id, String map_x, String map_y);

        List<CartMergeBean> initAdapterData();
        List<CartListDataBean.DataBean.GoodsListBean> initRecommendationData();

        void dealAllCheckBox(CompoundButton buttonView, boolean isChecked );//全选Cb
        void dealStoreCheckBox(boolean isChecked, int position, CheckBox mAllGoodsCb);//店铺Cb
        void dealGoodsCheckBox(boolean isItemChecked, final int parentposition, final int chaildposition);//商品Cb

        void setOnEditStoreGoods(TextView v, final int position);//购物车店铺商品编辑
        void setOnDeleteGoodsClick(TextView tv, final int parentposition, final int chaildposition);//删除商品
        void setOnDecreaseGoodsClick(final ImageView iv, final int parentposition, final int chaildposition, TextView goodsCountTv);//减少商品数量
        void setOnAddGoodsClick(ImageView iv, int parentposition, int chaildposition, TextView goodsCountTv);//增加商品数量
        void setOnEditGoodsClick(ImageView view, int parentposition, int chaildposition);//商品属性编辑
    }
}
