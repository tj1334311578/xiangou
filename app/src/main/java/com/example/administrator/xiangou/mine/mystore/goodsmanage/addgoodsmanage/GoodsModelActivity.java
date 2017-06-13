package com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage.bean.AddGoodsModelBean;
import com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage.bean.IntoAddGoodPageBean;
import com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage.bean.StockBean;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.tool.SpinnerEditText;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 作者： tj on 2017/6/6.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */
//Activity需要实现自定义接口
public class GoodsModelActivity extends MVPBaseActivity<AddGoodsManageContract.View, AddGoodsManagePresenter> implements AddGoodsManageContract.View, StockRecycleAdapter.Callback, StockRecycleAdapter.EditCallback {
    private ImageButton backBtn;
    private Spinner model_spinenr;
    private TagFlowLayout sizetag, colortag;
    private List<IntoAddGoodPageBean.DataBean.ModelBean> ModelList;
    private RecyclerView stockRecycle;
    private Map<Integer, Boolean> map = new ArrayMap<Integer, Boolean>();
    private StockRecycleAdapter adapter;
    private List<StockBean> list = new ArrayList<>();
    private static String simplecolor;
    private List<String> sizes;
    private List<String> colors;
    private Map<Integer, Set<Integer>> setMap = new ArrayMap<>();
    private Set<Integer> setSet;
    private static int oldPos = -1;
    private static int modelPosition = -1;
    private TagAdapter<String> sizetagAdapter, colorTagAdapter;
    private boolean haveSizeAttr = true;//判断是否有size属性
    private SpinnerEditText<BaseBean> set_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_model);
        ModelList = (List<IntoAddGoodPageBean.DataBean.ModelBean>) getIntent().getSerializableExtra("modelBean");
        Log.e("ModelList", "onCreate: " + ModelList.toString());
        initView();
    }


    private void initView() {
        backBtn = findContentView(R.id.goods_model_back);
        model_spinenr = findContentView(R.id.goods_model_spinner, false);
        colortag = findContentView(R.id.goods_color_tagflowlayout, false);
        sizetag = findContentView(R.id.goods_model_tagflowlayout, false);
        for (int i = 0; i < sizetag.getChildCount(); i++) {
            map.put(i, false);
        }
        //设置recycle的数据并显示
        stockRecycle = findContentView(R.id.goods_model_tableLayout, false);
        stockRecycle.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        list.add(null);
        stockRecycle.setAdapter(adapter = new StockRecycleAdapter(getContext(), list));
        //点击监听
        adapter.setMcallback(this);
        //edit监听
        adapter.setMeditCallback(this);

        final List<String> models = new ArrayList<>();
        for (int i = 0; i < ModelList.size(); i++) {
            models.add(ModelList.get(i).getName());
        }
//        mPresenter.callIntoAddGoodModel(ModelList.get(1).getCat_id(),0);//默认进页面调用获取尺码显示数据
        model_spinenr.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, models));
        model_spinenr.setSelection(0);
        model_spinenr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("oldposition", "onItemSelected: " + modelPosition);
                if (modelPosition != position) {
                    setMap.clear();
                    list.clear();
                    list.add(null);//添加第一行标题栏
                    adapter.notifyDataSetChanged();
                }
                modelPosition = position;
                Log.e("newposition", "onItemSelected: " + position);
                Log.e("setmap", "onItemSelected: " + setMap.toString());
                mPresenter.callIntoAddGoodModel(ModelList.get(position).getModel_id(), 0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        colortag.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
//              view.setSelected(!view.isSelected());
//                Log.e("size", "onTagClick: "+ sizetag.getSelectedList().toString());
//                Log.e("view", "isSelected: "+view.isSelected() );
                oldPos = -1;
                simplecolor = (String) ((TextView) view).getText();
                Iterator<Integer> colorpositions = colortag.getSelectedList().iterator();
                while (colorpositions.hasNext()) {
                    int colorposition;
                    colorposition = colorpositions.next();
                    if (colorposition == position) {
                        oldPos = position;
                    }
                }
                if (haveSizeAttr) {
                    sizetag.getAdapter().setSelectedList(new HashSet<Integer>());//清除sizetag中的选中集合
//                Log.e("size", "onTagClick: "+ sizetag.getSelectedList().toString());
//                Log.e("color", "onTagClick: "+colortag.getSelectedList().toString());
//                Iterator<Integer> itera=colortag.getSelectedList().iterator();
//                while (itera.hasNext()){
//                        int selected=itera.next();
//                        if (selected==position){
//                            simplecolor= (String) ((TextView)view).getText();
//                            view.setClickable(false);
//                        }
//                }                     }
                    if (oldPos != -1 && haveSizeAttr) {
                        sizetag.getAdapter().setSelectedList(setMap.get(oldPos));
//                    sizetag.getAdapter().setSelectedList(allMap.get(modelPosition).get(oldPos));
                    }
                } else {
                    if (oldPos == position) {
                        list.add(new StockBean(position, simplecolor, "无", "0"));
                        adapter.notifyItemInserted(list.size() - 1);
                    } else {
                        for (int i = 1; i < list.size(); i++) {
                            Log.e("i", "onTagClick: " + i);
                            if (list.get(i).getColor().equals(simplecolor)) {
                                list.remove(i);
                                adapter.notifyItemRemoved(i);
                                break;
                            }
                        }
                    }
                }
//                if (oldPos!=position) {
//                    view.setClickable(false);
//                    if (colortag.getChildAt(oldPos)!=null) {
//                        colortag.getChildAt(oldPos).setClickable(true);
//                    }
//                }
//                for (int i = 1; i < list.size(); i++) {
//                    if (list.get(i).getColor().equals(colors.get(position))){
//                        sizetag.getChildAt(i).setSelected(true);
//                    }
//                }
//                oldPos = position;

                return true;
            }
        });
        sizetag.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {

                showToast("view：" + ((TextView) view).getText() + sizetag.getSelectedList().toString());
                Log.e("count", "onTagClick: " + sizetag.getChildCount());
                //判断当前控件是否被选中
                Iterator<Integer> iterator = sizetag.getSelectedList().iterator();
                boolean isSelect = false;
                while (iterator.hasNext()) {
                    Integer selected = iterator.next();
                    if (selected == position) {
                        isSelect = true;
                        break;
                    }
                }

                showToast("view：" + ((TextView) view).getText() + map.get(position));
                if (haveSizeAttr)//当尺寸数据为空的时候；不做任何操作
                {
                    if (colortag.getSelectedList().size() != 0) {
                        if (isSelect) {
                            boolean hasAlive = false;
                            for (StockBean bean : list) {
                                if (bean != null && bean.getColor().equals(simplecolor) && bean.getSize().equals(sizes.get(position))) {
                                    hasAlive = true;
                                    break;
                                }
                            }
                            if (!hasAlive) {
                                list.add(new StockBean(position, simplecolor, ((TextView) view).getText().toString(), "0"));
                                adapter.notifyItemInserted(list.size() - 1);
                            }
                        } else {
                            for (int i = 1; i < list.size(); i++) {
                                if (list.get(i).getSize().equals(sizes.get(position)) && list.get(i).getColor().equals(simplecolor)) {
                                    list.remove(i);//去除对应的元素
//                            adapter.notifyItemRangeRemoved(0,list.size()+1);//删除后的刷新数据
                                    adapter.notifyItemRemoved(i);
                                }
                            }
                        }
                       setMap.put(oldPos, sizetag.getSelectedList());
//                    allMap.put(modelPosition,setMap);

                    }

//                Log.e("allmap", "onTagClick: "+allMap.toString() );
                    Log.e("list", "onTagClick: " + list.toString());
                    Log.e("map", "onTagClick: " + map.toString());
                    Log.e("map", "onTagClick: " + map.get(position));
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goods_model_back:
                finish();
                break;
        }
    }

    //在此处无用
    @Override
    public void dataToView(IntoAddGoodPageBean.DataBean data) {

    }

    @Override
    public void dataToModelView(AddGoodsModelBean modelBean) {
        haveSizeAttr = true;
        List<AddGoodsModelBean.SpecItemBean> modelsBean = new ArrayList<>();
        List<AddGoodsModelBean.SpecItemBean> colorsBean = new ArrayList<>();
        //获取相关属性集合
        for (int i = 0; i < modelBean.getSpecList().size(); i++) {
            if (modelBean.getSpecList().get(i).getName().equals("颜色")) {
                colorsBean.addAll(modelBean.getSpecList().get(i).getSpec_item());
            } else {
                modelsBean.addAll(modelBean.getSpecList().get(i).getSpec_item());
            }
        }
        sizes = new ArrayList<>();
        colors = new ArrayList<>();
        for (int i = 0; i < modelsBean.size(); i++) {
            sizes.add(modelsBean.get(i).getItem());
        }
        for (int i = 0; i < colorsBean.size(); i++) {
            colors.add(colorsBean.get(i).getItem());
        }

        colortag.setMaxSelectCount(1);//限制颜色属性只能选择一种

        if (sizes.size() == 0) {
            haveSizeAttr = false;
            sizes.add("无");//默认为无
            colortag.setMaxSelectCount(-1);//color可选择的最大数目   -1为无限制
        }
        Log.e("colors", "dataToModelView: " + colors.toString());
//        if (colors!=null)
//        colortag.getAdapter().setSelected(0,true);//默认第一条为选中
        colortag.setAdapter(colorTagAdapter = new TagAdapter<String>(colors) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.goods_model_tag, colortag, false);
                TextView tv = (TextView) view.findViewById(R.id.goods_tags_item_tv);
                tv.setText(s);
                return view;
            }
        });

        sizetag.setAdapter(sizetagAdapter = new TagAdapter<String>(sizes) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.goods_model_tag, sizetag, false);
                TextView tv = (TextView) view.findViewById(R.id.goods_tags_item_tv);
//                tv.setTextColor(getBaseContext().getResources().getColor(R.color.gray_6a747e));//在xml中设置了选择器则无需再代码中设置该属性
                tv.setText(s);
                return view;
            }
        });

        initSpinner();
        Log.e("dataview", "haveSizeAttr: " + haveSizeAttr);
    }

    private void initSpinner() {
        set_name = (SpinnerEditText<BaseBean>) findViewById(R.id.goods_model_spinner1);

        set_name.setRightCompoundDrawable(R.drawable.vector_drawable_arrowdown);
        set_name.setOnItemClickListener(new SpinnerEditText.OnItemClickListener<BaseBean>() {
            @Override
            public void onItemClick(BaseBean baseBean, SpinnerEditText<BaseBean> var1, View var2, int position, long var4, String selectContent) {
                showToast("名称:" + baseBean.Name + " Id:" + baseBean.Id);
            }
        });

        List<BaseBean> baseBeanList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            BaseBean baseBean = new BaseBean();
            baseBean.Name = "学生:" + i;
            baseBean.Id = i;
            baseBeanList.add(baseBean);
        }
        set_name.setNeedShowSpinner(true);
        set_name.setView(set_name);//设置window显示位置
        set_name.setList(baseBeanList);
        set_name.setSelection(0);
    }


    public static class BaseBean {
        public String Name;
        public int Id;

        @Override
        public String toString() {
            return Name;
        }
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void click(View view, int position) {
        Log.e("del", "click: position:" + position);
        if (position > 0 && haveSizeAttr) {//判断是否有尺寸属性
            for (int i = 0; i < colors.size(); i++) {
                Set<Integer> location;
                if (setMap.get(i)!=null) {//判断集合中是否有key为i的集合，否则进入下一循环
                     location = setMap.get(i);
                }else{
                    continue;
                }
                Iterator<Integer> iterator = location.iterator();
                int k=0;
                boolean locaboolean=false;
                while(iterator.hasNext()){
                    k=iterator.next();
                   if (list.get(position).getColor().equals(colors.get(i))&&list.get(position).getPosition()==k){
                        setMap.get(i).remove(k);
                       locaboolean=true;
                       break;//跳出while循环
                    }
                }
                if (locaboolean){
                    break;//跳出for循环
                }
            }
            for (int j = 0; j < colors.size(); j++) {
                if (colors.get(j).equals(simplecolor))
                    sizetagAdapter.setSelectedList(setMap.get(j));
            }
            list.remove(position);
            adapter.notifyItemRangeRemoved(position,list.size());
            sizes.add("");
            sizetagAdapter.notifyDataChanged();//刷新sizetag标签
            sizes.remove(sizes.size() - 1);
            sizetagAdapter.notifyDataChanged();//刷新sizetag标签

            Log.e("list", "click: " + list.toString() + "\nsetmap" + setMap.toString() + "selected:" + sizetag.getSelectedList().toString());
        } else {//无sizes的情况
            setSet = colortag.getSelectedList();
            Log.e("setSet", "click: "+ setSet.toString());
                position=list.size()>position?position:(list.size()-1);
                for (int j = 0; j < colors.size(); j++) {
                    if (colors.get(j).equals(list.get(position).getColor())) {
                        setSet.remove(j);//删除后颜色状态
                        colorTagAdapter.setSelectedList(setSet);
                        Log.e("setSet11111111", "click: "+ setSet.toString()+"\nlist.get(position).getPosition()"+list.get(position).getPosition()
                                +"list.get(position).getcolor():"+list.get(position).getColor());
                        list.remove(position);
                        adapter.notifyItemRangeRemoved(position,list.size());
                        Log.e("jjj", "click: "+j+"position:");
                        break;
                    }
                }

            colors.add("");
            colorTagAdapter.notifyDataChanged();//刷新colortag标签
            colors.remove(colors.size() - 1);
            colorTagAdapter.notifyDataChanged();//刷新colortag标

        }
    }

    @Override
    public void editCallback(View v, CharSequence s, int position) {
        //关键；否则会报下标越界 注：在回调时；由于获取的位置是之前的位置，来不及重新设置position，在刷新之后再次调用时是之前的位置，所以会报下标越界
        //故重新给越界的位置初始化为数据最后一条数据的位置。
        position = list.size() > position ? position : list.size() - 1;
        if (adapter.getDatas() != null && adapter.getDatas().size() > 1) {
            adapter.getDatas().get(position).setStock(s.toString());
            Log.e("sssjsijso", "View: " + v + "s:" + s + "position" + position + "\ndata:" + adapter.getDatas().get(position).getStock());
//            Toast.makeText(this, "listview的内部的按钮被点击了！，位置是-->" + (Integer) v.getTag() + ",内容是-->" + adapter.getDatas().get((Integer) v.getTag()).getStock() + "s-->" + s,
//                    Toast.LENGTH_SHORT).show();
        }
    }


}
