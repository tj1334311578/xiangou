package com.example.administrator.xiangou.cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.AutoRVAdapter;
import com.example.administrator.xiangou.tool.ItemIntervalDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */

public class PopWindow_EditCart extends PopupWindow {
    private View mMenuView;
    private Button Cancel,beSure;
    private RecyclerView colorRecycle,sizeRecycle;
    public PopWindow_EditCart(Context context, View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.cart_popupwindow, null);

        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.PopupAnimation);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x80000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.cart_popwindow_Rl).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

        initView();
    }

    private void initView() {
        Cancel = (Button) mMenuView.findViewById(R.id.cart_cancel);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        beSure= (Button) mMenuView.findViewById(R.id.cart_button_sure);
        colorRecycle= (RecyclerView) mMenuView.findViewById(R.id.cart_goods_colorrecycle);
        sizeRecycle= (RecyclerView) mMenuView.findViewById(R.id.cart_goods_sizerecycle);
        colorRecycle.setLayoutManager(new GridLayoutManager(mMenuView.getContext(),3));
        List<String> colorlist=new ArrayList<>();
        colorlist.add("白色 + 黑色");
        colorlist.add("白色");
        colorlist.add("灰色");
        colorlist.add("蓝色");
        colorRecycle.setAdapter(new popAdapter(getContentView().getContext(),colorlist,0));
        colorRecycle.addItemDecoration(new ItemIntervalDecoration(0,10,0,0));
        List<String> sizelist=new ArrayList<>();
        sizeRecycle.setLayoutManager(new GridLayoutManager(mMenuView.getContext(),5));
        sizeRecycle.addItemDecoration(new ItemIntervalDecoration(0,10,0,0));
        sizelist.add("S");
        sizelist.add("M");
        sizelist.add("L");
        sizelist.add("XL");
        sizelist.add("XXL");
        sizeRecycle.setAdapter(new popAdapter(getContentView().getContext(),sizelist,1));

    }

    class popAdapter extends AutoRVAdapter{
        private List<String> list=new ArrayList<>();
        private int style;

        public popAdapter(Context context,List<String> list,int style) {
            super(context, list);
            this.list=list;
            this.style=style;
        }

        @Override
        protected int onCreateViewHolderID(int viewType) {
            switch (style){
                case 0:
                    return R.layout.cart_popwindow_coloritem;
                case 1:
                    return R.layout.cart_popwindow_sizeitem;
                default:
                    return 0;
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            switch (style){
                //添加颜色数据
                case 0:
                    addcolorData(holder,position);
                    break;
                //添加尺寸数据
                case 1:
                    addsizeData(holder,position);
                    break;
                default:
            }
        }

        private void addsizeData(ViewHolder holder, int position) {
            holder.setTextButton(R.id.cart_popwindow_sizeitem,list.get(position));
        }

        private void addcolorData(ViewHolder holder, int position) {
            holder.setTextButton(R.id.cart_popwindow_coloritem,list.get(position));
        }
    }
}
