package com.example.administrator.xiangou.goods_details.storehome.storehome.homestore;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.xiangou.goods_details.storehome.HomePageBean;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class HomeStorePresenter extends BasePresenterImpl<HomeStoreContract.View> implements HomeStoreContract.Presenter{

    @Override
    public void dealHomeStoreCall(HomePageBean dataBean) {
        if (dataBean!=null)
        mView.sendDataBeanToView(dataBean);
        else{
            Toast.makeText(mView.getContext(), "数据为空", Toast.LENGTH_SHORT).show();
        }
    }
}
