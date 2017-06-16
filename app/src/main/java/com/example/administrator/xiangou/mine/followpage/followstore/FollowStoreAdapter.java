package com.example.administrator.xiangou.mine.followpage.followstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.net.XianGouApiService;
import com.example.administrator.xiangou.tool.GlideImageLoader;
import com.example.administrator.xiangou.tool.SelectImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
public class FollowStoreAdapter extends BaseAdapter {
    private  List<FollowStoreBean.DataBean> lists;
    private Context mContext;
    private GlideImageLoader mImageLoader;
    private boolean toEdit;
    private List<Integer> storesIdList;

    public boolean isToEdit() {
        return toEdit;
    }

    public void setToEdit(boolean toEdit) {
        this.toEdit = toEdit;
    }

    public interface DealSotresFollowChanged{
        void dealBackStoresChaged(List<Integer> storesIds);
        void dealClickEnterStore(int did);
    }
    public DealSotresFollowChanged mDealSotresFollowChanged;
    public void setDealSotresFollowChanged(DealSotresFollowChanged mDealSotresFollowChanged){
        this.mDealSotresFollowChanged = mDealSotresFollowChanged;
    }

    public FollowStoreAdapter(Context context, List<FollowStoreBean.DataBean> lists) {
        this.lists=lists;
        mContext=context;
        storesIdList = new ArrayList<>();
        mImageLoader = new GlideImageLoader();
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
            convertView= LayoutInflater.from(mContext).inflate(R.layout.followpage_store_item,null);
            viewHolder=new ViewHolder();
            viewHolder.cb=findContextView(convertView,R.id.follow_store_item_checkbox);
            viewHolder.description=findContextView(convertView,R.id.follow_store_item_likemessage);
            viewHolder.follows=findContextView(convertView,R.id.follow_store_item_follows);
            viewHolder.imgbtn=findContextView(convertView,R.id.follow_store_item_imgbtn);
            viewHolder.selectImg=findContextView(convertView,R.id.follow_store_item_img);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        setData(viewHolder,position);
        return convertView;
    }

    private void setData(ViewHolder viewHolder, final int position) {
        mImageLoader.displayImage(mContext, XianGouApiService.IMGBASEURL+lists.get(position).getLogo(),viewHolder.selectImg);

        if (isToEdit()){
            viewHolder.cb.setVisibility(View.VISIBLE);
            viewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
//                        storesIdList.add(lists.get(position).getDid());
                        storesIdList.add(position);
                    }else {
//                        int index = storesIdList.indexOf(lists.get(position).getDid());
                        int index = storesIdList.indexOf(position);
                        storesIdList.remove(index);
                    }
                    mDealSotresFollowChanged.dealBackStoresChaged(storesIdList);
                    notifyDataSetChanged();
                }
            });
        }else {
            if (viewHolder.cb.getVisibility()!=View.GONE) viewHolder.cb.setVisibility(View.GONE);
        }
        viewHolder.description.setText(lists.get(position).getName());
        viewHolder.follows.setText(lists.get(position).getCount()+"人关注");
        viewHolder.imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDealSotresFollowChanged.dealClickEnterStore(lists.get(position).getDid());
            }
        });
    }

    private <T extends View>T findContextView(View convertView, int id) {
        return (T) convertView.findViewById(id);
    }

    class ViewHolder{
        CheckBox cb;
        SelectImageView selectImg;
        TextView description,follows;
        ImageButton imgbtn;
    }
}
