package com.example.administrator.xiangou.mine.store_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mine.store_application.model.CategoryListDataBean;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;
import com.example.administrator.xiangou.tool.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouzongyao
 * @Description 申请店铺的主营类别页
 * @email 18482195579@163.com
 * @Date 2017-05-12 9:40
 */
public class CategoryTypeActivity extends BaseActivity {
    private ImageView mBackIv;
    private Button mSaveBtn;
    private ListView mCategoryLv;
    private List<Integer> mCategoryList;
    private List<String> mCategoryNameList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_category);
        mCategoryList = new ArrayList<>();
        mCategoryNameList = new ArrayList<>();
        initView();
    }

    private void initView() {
        mBackIv = findContentView(R.id.select_category_return);
        mSaveBtn = findContentView(R.id.select_category_btn);
        mCategoryLv = findContentView(R.id.select_category_listView,false);
        showProgressDialog();
        addSubscription(mApiService.getCategoryList(), new BaseSubscriber<CategoryListDataBean>(this) {
            @Override
            public void onNext(CategoryListDataBean categoryListDataBean) {
                if (categoryListDataBean.getState().getCode() == 200){
                    bindDataToView(categoryListDataBean.getData());
                }
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                showToast(e.getMessage());
            }

        });
    }

    private void bindDataToView(final List<CategoryListDataBean.DataBean> data) {
        CategoryLvAdapter mAdapter = new CategoryLvAdapter(this,data);
        mAdapter.setCategoryCheckedListener(new CategoryLvAdapter.CategoryCheckedListener() {
            @Override
            public void dealAdapterItemChecked(CompoundButton buttonView, boolean isChecked, int position) {
                if (isChecked){
                    if (mCategoryList.size()<3) {
                        mCategoryList.add(data.get(position).getCat_id());
                        mCategoryNameList.add(data.get(position).getName());
                    }else {
                        buttonView.setChecked(false);
                        showToast("最多只能选择3个类型！");
                    }
                }else {
                    for (int i = 0; i < mCategoryList.size(); i++) {
                        if (data.get(position).getCat_id() == mCategoryList.get(i)) {
                            mCategoryList.remove(i);
                            mCategoryNameList.remove(i);
                        }
                    }

                }

            }
        });
        mCategoryLv.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_category_return:
                finish();
                break;
            case R.id.select_category_btn:
                Intent intent = new Intent();
                int[] categoryList = new int[mCategoryList.size()];
                String[] categoryNameList = new String[mCategoryList.size()];
                for (int i = 0; i < mCategoryList.size(); i++) {
                    categoryList[i] = mCategoryList.get(i);
                    categoryNameList[i] = mCategoryNameList.get(i);
                }
                Log.e("categoryList", "onClick: " + mCategoryList.toString());
                intent.putExtra("category_list", categoryList);
                intent.putExtra("category_namelist", categoryNameList);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
}
