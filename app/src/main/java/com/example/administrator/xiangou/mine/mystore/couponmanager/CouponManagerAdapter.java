package com.example.administrator.xiangou.mine.mystore.couponmanager;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.base.RVBaseAdapter;
import com.example.administrator.xiangou.base.RVBaseViewHolder;


import java.util.List;


/**
 * 作者： tj on 2017/5/27.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */
public class CouponManagerAdapter extends RVBaseAdapter<CouponManageBean> {
    protected  interface  OnLongClickListener{
        void setOnLongClickListener(View view, int position);
    }
    public void setOnLongClickListener(OnLongClickListener onLongClickListener){
        this.onLongClickListener=onLongClickListener;
    }
    protected OnLongClickListener onLongClickListener;

    private int tag;
    private TextView startTime,endTime,remainingquantity,preferentialvalue,condition,condition1;
    private Button del;
    private RelativeLayout bg;

    public CouponManagerAdapter(Context context, List<CouponManageBean> mDatas,int tag) {
        super(context,R.layout.seller_center_coupon_item, mDatas);
        this.tag=tag;
    }

//    @Override
//    public RVBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View mItemView = LayoutInflater.from(mContext).inflate(mLayoutResId, parent,false);
//
//        onLongClickListener.setOnLongClickListener(mItemView,mItemView.getVerticalScrollbarPosition());
//        RVBaseViewHolder holder = new RVBaseViewHolder(mItemView, mOnItemViewClickListener);
//        return holder;
//    }

    private void findView(RVBaseViewHolder holder) {
        startTime= holder.getTextView(R.id.seller_center_coupon_item_starttime);
        endTime= holder.getTextView(R.id.seller_center_coupon_item_endtime);
        remainingquantity= holder.getTextView(R.id.seller_center_coupon_item_remainingquantity);
        preferentialvalue=  holder.getTextView(R.id.seller_center_coupon_item_preferentialvalue);
        condition= holder.getTextView(R.id.seller_center_coupon_item_condition);
        condition1=  holder.getTextView(R.id.seller_center_coupon_item_condition1);
        bg= (RelativeLayout) holder.getView(R.id.seller_center_coupon_style_rl);
        del=holder.getButton(R.id.seller_center_coupon_del);
    }

    @Override
    protected void bindData(RVBaseViewHolder holder, CouponManageBean couponManageBean, final int position) {
        findView(holder);

        switch (tag){
            case 1:
                style1();
                break;
            case 2:
                style1();
                break;
            case 3:
                style2();
                break;
        }
        View contextView = mItemView;
        if (contextView.getTag()==null) {
            contextView.setTag(mDatas.get(position));
        }
        contextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e("item position", "onLongClick: "+position );
                if (v.getTag()==mDatas.get(position)) {
//                    onLongClickListener.setOnLongClickListener(v, position);
                    longView(v,position);
                    return true;
                }else return false;
            }
        });
        if (couponManageBean!=null)
        dataToView(couponManageBean);
    }

    private void longView(View v, final int position) {
        RelativeLayout.LayoutParams layoutparams = (RelativeLayout.LayoutParams) v.findViewById(R.id.seller_center_coupon_style_rl).getLayoutParams();
        layoutparams.setMarginStart(mContext.getResources().getDimensionPixelOffset(R.dimen.item_margin));//设置marginstart属性
        layoutparams.removeRule(RelativeLayout.CENTER_HORIZONTAL);//取消垂直居中
        layoutparams.addRule(Button.TEXT_ALIGNMENT_GRAVITY);//字体居中 注：没用呢？
        v.findViewById(R.id.seller_center_coupon_del).setVisibility(View.VISIBLE);
        v.findViewById(R.id.seller_center_coupon_style_rl).setLayoutParams(layoutparams);
        v.findViewById(R.id.seller_center_coupon_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout.LayoutParams layoutparams = (RelativeLayout.LayoutParams) ((View)v.getParent()).findViewById(R.id.seller_center_coupon_style_rl).getLayoutParams();
                mDatas.remove(position);//删除该项数据
                layoutparams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                v.setVisibility(View.GONE);
                layoutparams.setMarginStart(0);
                ((View)v.getParent()).findViewById(R.id.seller_center_coupon_style_rl).setLayoutParams(layoutparams);
                Log.e("onClick:",mDatas.size()+ " onClick: "+mDatas.toString());
//                notifyItemRangeChanged(0,mDatas.size());
//                upData();
            }
        });
    }

    private void dataToView(CouponManageBean couponManageBean) {
        condition.setText(couponManageBean.getCondition());
        startTime.setText(couponManageBean.getStartTime());
        endTime.setText(couponManageBean.getEndTime());
        remainingquantity.setText(couponManageBean.getRemainingquantity());
        preferentialvalue.setText(couponManageBean.getPreferentialvalue());
        condition1.setText(couponManageBean.getCondition1());
    }

    //设置样式2
    private void style2() {
        condition.setTextColor(mContext.getResources().getColor(R.color.white));
        startTime.setTextColor(mContext.getResources().getColor(R.color.white_b3));
        endTime.setTextColor(mContext.getResources().getColor(R.color.white_b3));
        remainingquantity.setTextColor(mContext.getResources().getColor(R.color.white_b3));
        preferentialvalue.setTextColor(mContext.getResources().getColor(R.color.white));
        condition1.setTextColor(mContext.getResources().getColor(R.color.white_b3));
        bg.setBackgroundResource(R.mipmap.privilege_management_lose_efficacy_background_color);
    }
    //设置样式1
    private void style1() {
        condition.setTextColor(mContext.getResources().getColor(R.color.color_pink_fb4a89));
        startTime.setTextColor(mContext.getResources().getColor(R.color.cart_textb316color));
        endTime.setTextColor(mContext.getResources().getColor(R.color.cart_textb316color));
        remainingquantity.setTextColor(mContext.getResources().getColor(R.color.color_pink_fb4a89));
        preferentialvalue.setTextColor(mContext.getResources().getColor(R.color.color_pink_fb4a89));
        condition1.setTextColor(mContext.getResources().getColor(R.color.color_pink_b3fb4a89));
        bg.setBackgroundResource(R.mipmap.privilege_management_use_background_color);
    }
}
