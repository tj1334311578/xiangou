package com.example.administrator.xiangou.mine.followpage.followgoods;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.net.XianGouApiService;
import com.example.administrator.xiangou.tool.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
public class FollowGoodsAdapter extends BaseAdapter {
    private List<FollowGoodsBean.DataBean> lists;
    private Context mContext;
    private List<Integer> goodsIdList;
    private GlideImageLoader mImageLoader;
    private boolean toEdit;

    public boolean isToEdit() {
        return toEdit;
    }

    public void setToEdit(boolean toEdit) {
        this.toEdit = toEdit;
    }

    public FollowGoodsAdapter(Context context, List<FollowGoodsBean.DataBean> lists) {
        this.lists=lists;
        this.mContext=context;
        goodsIdList = new ArrayList<>();
        mImageLoader = new GlideImageLoader();
    }


    public interface DealGoodsFollowChanged{
        void dealBackGoodsChaged(List<Integer> goodsIds);
    }
    public DealGoodsFollowChanged mDealGoodsFollowChanged;
    public void setDealGoodsFollowChanged(DealGoodsFollowChanged mDealGoodsFollowChanged){
        this.mDealGoodsFollowChanged = mDealGoodsFollowChanged;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            final LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.followpage_goods_item,null);
            viewHolder=new ViewHolder();
            viewHolder.cb = findContextView(convertView,R.id.follow_goods_item_checkbox);
            viewHolder.img=findContextView(convertView,R.id.follow_goods_item_img);
            viewHolder.description=findContextView(convertView,R.id.follow_goods_item_description);
            viewHolder.oldprice=findContextView(convertView,R.id.follow_goods_item_oldprice);
            viewHolder.nowprice=findContextView(convertView,R.id.follow_goods_item_newprice);
            viewHolder.iscoupon=findContextView(convertView,R.id.follow_goods_item_voucher);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        setData(viewHolder,position);
        return convertView;
    }

    private void setData(ViewHolder viewHolder, final int position) {
        viewHolder.description.setText(lists.get(position).getGoods_name());
        viewHolder.nowprice.setText("￥"+ lists.get(position).getShop_price());
        viewHolder.oldprice.setText("￥"+ lists.get(position).getMarket_price());
        mImageLoader.displayImage(mContext, XianGouApiService.IMGBASEURL+lists.get(position).getOriginal_img(),viewHolder.img);
        if (isToEdit()){
            viewHolder.cb.setVisibility(View.VISIBLE);
            viewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        goodsIdList.add(position);
                    }else {
                        int index = goodsIdList.indexOf(position);
                        goodsIdList.remove(index);
                    }
                    mDealGoodsFollowChanged.dealBackGoodsChaged(goodsIdList);
                    notifyDataSetChanged();
                }
            });
        }else {
            if (viewHolder.cb.getVisibility()!=View.GONE) viewHolder.cb.setVisibility(View.GONE);
        }
        viewHolder.oldprice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        if (lists.get(position).getIs_coupon()==1) {
            viewHolder.iscoupon.setVisibility(View.VISIBLE);
        }
    }

    private <T extends View>T findContextView(View convertView, int id) {
        return (T) convertView.findViewById(id);
    }

    class ViewHolder {
        CheckBox cb;
        ImageView img;
        TextView description;
        TextView oldprice,nowprice,iscoupon;
    }
}
