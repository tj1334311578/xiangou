package com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage.bean.IntoAddGoodPageBean;
import com.example.administrator.xiangou.tool.BaseActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 作者： tj on 2017/6/5.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public class GoodsTagActivity extends BaseActivity {
    private TextView saveBtn;
    private TagFlowLayout tagFlowLayout;
    private List<IntoAddGoodPageBean.DataBean.SignBeanX> signs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_tags);
        signs = (List<IntoAddGoodPageBean.DataBean.SignBeanX>) getIntent().getSerializableExtra("tags");
        Log.e("signs", "onCreate: " + signs.toString());
        initView();
    }

    private void initView() {
        findContentView(R.id.goods_tags_back);
        saveBtn = findContentView(R.id.goods_tags_saved);
        tagFlowLayout = findContentView(R.id.goods_tags_tagflowlayout, false);
        //初始化数据
        final List<String> tags = new ArrayList<>();
//        List<Integer>
        for (int i = 0; i < signs.size(); i++) {
            tags.add(signs.get(i).getName());
        }
//        tagFlowLayout.setMaxSelectCount(5);//设置允许选择的最高数量,可以在xml中设置max_select属性
        //设置数据源
        tagFlowLayout.setAdapter(new TagAdapter<String>(tags) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {

                View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.goods_tags_item, tagFlowLayout, false);
                TextView tv = (TextView) view.findViewById(R.id.goods_tags_item_tv);
//                tv.setTextColor(getBaseContext().getResources().getColor(R.color.gray_6a747e));//在xml中设置了选择器则无需再代码中设置该属性
                tv.setText(s);
                return view;
            }
        });


        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                showToast(tags.get(position));
                view.setSelected(!view.isSelected());//设置是否被选中
                Log.e("view", "getView: "+view.isSelected() );
                return true;

            }
        });
        tagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                Log.e("selectposSet:", "onSelected: " + selectPosSet.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goods_tags_back://返回
                finish();
                break;
            case R.id.goods_tags_saved://保存
                Set<Integer> selectes=tagFlowLayout.getSelectedList();
                List<IntoAddGoodPageBean.DataBean.SignBeanX> signbeans=new ArrayList<>();
                for (Integer selected:selectes) {
                    signbeans.add(signs.get(selected));
                }

                Intent intent=new Intent();
                //不转换会报类型转换错误
                Object[] strs = {signbeans};
                for (int i = 0; i < strs.length; i++) {
                    Serializable s = (Serializable) strs[i];
                    intent.putExtra("tag",s);
                }
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
}
