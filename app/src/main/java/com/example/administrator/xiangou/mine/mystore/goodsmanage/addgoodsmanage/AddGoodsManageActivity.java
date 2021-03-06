package com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage;


import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage.bean.AddGoodsModelBean;
import com.example.administrator.xiangou.mine.mystore.goodsmanage.addgoodsmanage.bean.IntoAddGoodPageBean;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.tool.ImageQualityUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.apptik.widget.multiselectspinner.MultiSelectSpinner;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class AddGoodsManageActivity extends MVPBaseActivity<AddGoodsManageContract.View, AddGoodsManagePresenter> implements AddGoodsManageContract.View {
    private RelativeLayout right_rl;
    private TextView titleTv,good_classification,good_model,good_Labels;
    private ImageView backBtn;
    private EditText good_name,shopprice,marketprice,good_Amount;
    private Button releaseBtn;
    private ImageButton classificationBtn,modelBtn,labelsBtn;
    private CheckBox isNew_no,isNew_yes,isRecommend_no,isRecommend_yes,freeShipping_no,freeShipping_yes;
    private GridView gridView;
    private List<Map<String, Object>> datas;
    private AddGoodsImgesAdpter gridViewAddImgesAdpter;
    private Dialog dialog;
    private final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;
    private final String IMAGE_DIR = Environment.getExternalStorageDirectory() + "/gridview/";
    /* 头像名称 */
    private final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private IntoAddGoodPageBean.DataBean mdata;

//    private Spinner myMultispinner;
    private Spinner myspinner;
    private final static int ForRESULTTag=101;
    private final static int ForRESULTModel=102;
    private static boolean[] selecteds;
    private List<String> strs;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_management_add);
        Log.e("statu", "onCreate: "+getIntent().getIntExtra("statu",0) );
        mPresenter.callIntoAddGoodPage(1,getIntent().getIntExtra("statu",0));
    }
//查找控件
    private void initView() {
        right_rl=findContentView(R.id.store_right_rl,false);
        right_rl.setVisibility(View.GONE);
        titleTv=findContentView(R.id.head_title_tv,false);
        titleTv.setText("添加商品");
        backBtn=findContentView(R.id.store_headback);
        releaseBtn=findContentView(R.id.goods_management_addgoods_releasebtn);

        good_model=findContentView(R.id.goods_management_addgoods_model_text);
        modelBtn=findContentView(R.id.goods_management_addgoods_model_btn);
        good_Labels=findContentView(R.id.goods_management_addgoods_labels_text);
        labelsBtn=findContentView(R.id.goods_management_addgoods_labels_btn);
        good_name=findContentView(R.id.goods_management_addgoods_goodname_edit,false);
        shopprice=findContentView(R.id.goods_management_addgoods_shopprice_edit,false);
        marketprice=findContentView(R.id.goods_management_addgoods_marketprice_edit,false);
        good_Amount=findContentView(R.id.goods_management_addgoods_goodsamount_edit,false);

        isNew_no=findContentView(R.id.goods_management_addgoods_isnewgoodornot_check,false);
        isNew_yes=findContentView(R.id.goods_management_addgoods_isnewgoodornot_isnewgood_check,false);
        initCheckBox(isNew_yes,R.id.goods_management_addgoods_isnewgoodornot_check,
                isNew_no,R.id.goods_management_addgoods_isnewgoodornot_isnewgood_check);
        isRecommend_no=findContentView(R.id.goods_management_addgoods_isnotrecommend_check,false);
        isRecommend_yes=findContentView(R.id.goods_management_addgoods_isrecommend_check,false);
        initCheckBox(isRecommend_yes,R.id.goods_management_addgoods_isrecommend_check,
                isRecommend_no,R.id.goods_management_addgoods_isnotrecommend_check);
        gridView=findContentView(R.id.goods_management_addgoods_updown_grid,false);

        //上传图片相关
        datas = new ArrayList<>();
        gridViewAddImgesAdpter=new AddGoodsImgesAdpter(this,datas);
        //设置grideview的列数以及水平，垂直view之间的间距
        gridView.setNumColumns(3);
//        gridView.setVerticalSpacing(50);//行间距
        gridView.setAdapter((ListAdapter) gridViewAddImgesAdpter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showdialog();
            }
        });
        //给在其他区域点击操作
        strs = new ArrayList<>();
        for (int i = 0; i < mdata.getCate().size(); i++) {
            strs.add(mdata.getCate().get(i).getName());
        }
        myspinner=findContentView(R.id.goods_management_addgoods_selectspinner,false);
        myspinner.setSelection(0);
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, strs);
        myspinner.setAdapter(adapter);
        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("positiojnk", "initView: "+myspinner.getSelectedItemId() );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        myMultispinner.setListAdapter(adapter).setMinSelectedItems(1).setMaxSelectedItems(2);
//        final   List<Integer> item_ids=new ArrayList<>();
//        myMultispinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                selecteds = myMultispinner.getSelected();
//                item_ids.clear();
//                //初始化为false
//                for (int i = 0; i < selecteds.length; i++) {
//                    if (selecteds[i]==true){
//                        myMultispinner.setSelection(i,false);
//                    }
//                }
//                myMultispinner.setSelection(position,true);
//                strs.add("");
//                adapter.notifyDataSetChanged();
//                strs.remove(strs.size()-1);
//                adapter.notifyDataSetChanged();
//            }
//        });
//        myMultispinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Log.e("position1", "onItemClick: "+Arrays.toString(myMultispinner.getSelected()));
//                Log.e("position2", "onItemClick: "+Arrays.toString(selecteds));
////                for (int i = 0; i < selecteds.length; i++) {
////                    //逻辑设置为单选
////                    if (selecteds[i]&&i==position){
////                        item_ids.add(mdata.getCate().get(i).getCat_id());
////                    }else{
////                        myMultispinner.setSelection(position,false);
////                    }
////                }
//                Log.e("selecteds", "initView: "+item_ids.toString());
//                Log.e("str", "initView: "+myMultispinner.getSpinnerText().replace(" ",""));
//
//            }

//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
////                Log.e("position2", "onItemClick: "+ Arrays.toString(myMultispinner.getSelected()));
//            }
//        });



    }


    /**
     * 选择图片对话框
     */
    private void showdialog() {
        final View localView= LayoutInflater.from(this).inflate(R.layout.layout_popupwindow_pic,null);
        Button takephoto,pickphoto,cancel;
        takephoto= (Button) localView.findViewById(R.id.takePhotoBtn);
        pickphoto= (Button) localView.findViewById(R.id.pickPhotoBtn);
        cancel= (Button) localView.findViewById(R.id.cancelBtn);
        dialog = new Dialog(this, R.style.custom_dialog);
        dialog.setContentView(localView);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        // 设置全屏
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = display.getWidth(); // 设置宽度
        dialog.getWindow().setAttributes(lp);

        // localView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        localView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = localView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dialog.dismiss();
                    }
                }
                return true;
            }
        });
        dialog.show();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                // 拍照
                camera();
            }
        });
        pickphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                // 从系统相册选取照片
                gallery();
            }
        });
    }
    /**
     * 从相册获取2
     */
    private void gallery() {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }
    /**
     * 拍照
     */
    private void camera() {
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()){
            File dir = new File(IMAGE_DIR);
            if (!dir.exists()) {
                dir.mkdir();
            }
            tempFile = new File(dir,
                    System.currentTimeMillis() + "_" + PHOTO_FILE_NAME);
            //从文件中创建uri
            Uri uri = Uri.fromFile(tempFile);
            Intent intent = new Intent();
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.addCategory(intent.CATEGORY_DEFAULT);
            // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
            startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
        }else{
            Toast.makeText(this, "未找到存储卡，无法拍照！", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 判断sdcard是否被挂载
     */
    private boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    private void initCheckBox(CheckBox y, int yesid, CheckBox n, int noid){
        y = findContentView(yesid,false);
        n = findContentView(noid,false);
        final CheckBox yes = y;
        final CheckBox no = n;
        yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && no.isChecked()==isChecked){
//                    yes.setFocusable(false);
                    buttonView.setFocusable(false);
                    no.setChecked(!isChecked);
                    no.setFocusable(true);
                }
            }
        });
        no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && yes.isChecked()==isChecked){
//                    no.setFocusable(false);
                    buttonView.setFocusable(false);
                    yes.setChecked(!isChecked);
                    yes.setFocusable(true);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.store_headback://返回上一级
                finish();
                break;
            case R.id.goods_management_addgoods_releasebtn://发布新商品
                showToast("发布新商品");
                Log.e("ceshi", "isNew_no:"+isNew_no.isChecked()+"\nisNew_yes:" +isNew_yes.isChecked()+"\nisRecommend_no:"+isRecommend_no.isChecked()+"\nisRecommend_yes:"+isRecommend_yes.isChecked());
                savadata();
                break;
//            case R.id.goods_management_addgoods_classification_btn://进入商品分类扩展页
//                showToast("进入商品分类扩展页");
//                break;
            case R.id.goods_management_addgoods_model_btn://进入模型扩展页
                showToast("进入商品模型扩展页");
                //添加商品进入模型页
                startNewUIForResult(GoodsModelActivity.class,ForRESULTModel,"modelBean",mdata.getModel());
                break;
            case R.id.goods_management_addgoods_labels_btn://进入标签扩展页
                showToast("进入商品标签扩展页");
                startNewUIForResult(GoodsTagActivity.class,ForRESULTTag,"tags",mdata.getSign());
                break;
        }
    }
    //保存需要上传的数据并请求上传
    private void savadata() {

    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case PHOTO_REQUEST_GALLERY:
                    // 从相册返回的数据
                    if (data != null) {
                        // 得到图片的全路径
                        Uri uri = data.getData();
                        String[] proj = {MediaStore.Images.Media.DATA};
                        //好像是android多媒体数据库的封装接口，具体的看Android文档
                        Cursor cursor = managedQuery(uri, proj, null, null, null);
                        //按我个人理解 这个是获得用户选择的图片的索引值
                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        //将光标移至开头 ，这个很重要，不小心很容易引起越界
                        cursor.moveToFirst();
                        //最后根据索引值获取图片路径
                        String path = cursor.getString(column_index);
                        uploadImage(path);
                    }
                    break;
                case PHOTO_REQUEST_CAREMA:
                    // 从相机返回的数据
                    if (hasSdcard()) {
                        if (tempFile != null) {
                            uploadImage(tempFile.getPath());
                        } else {
                            Toast.makeText(this, "相机异常请稍后再试！", Toast.LENGTH_SHORT).show();
                        }

                        Log.i("images", "拿到照片path=" + tempFile.getPath());
                    } else {
                        Toast.makeText(this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case ForRESULTTag://获取tag页面返回的数据
                    String tag_id="";
                    String tags="";
                        if (data!=null){
                            //获取返回的标签id集合字符
                            Bundle bundle=data.getExtras();
                            List<IntoAddGoodPageBean.DataBean.SignBeanX> tagsbean= (List<IntoAddGoodPageBean.DataBean.SignBeanX>) bundle.getSerializable("tag");
                            for (int i = 0; i < tagsbean.size(); i++) {
                                if (tags.equals("")){
                                    tags+=tagsbean.get(i).getName();
                                }else{
                                    tags+=","+tagsbean.get(i).getName();
                                }
                                if (tag_id.equals("")){
                                    tag_id+=tagsbean.get(i).getSign_id();
                                }else{
                                    tag_id+=","+tagsbean.get(i).getSign_id();
                                }
                            }
                        }
                    good_Labels.setText(tags);
                    Log.e("data", "tags: "+tags+"tag_id"+tag_id);
                    break;
                case ForRESULTModel://获取model页面返回的数据

                    break;

            }

//            if (requestCode == PHOTO_REQUEST_GALLERY) {
//                // 从相册返回的数据
//                if (data != null) {
//                    // 得到图片的全路径
//                    Uri uri = data.getData();
//                    String[] proj = {MediaStore.Images.Media.DATA};
//                    //好像是android多媒体数据库的封装接口，具体的看Android文档
//                    Cursor cursor = managedQuery(uri, proj, null, null, null);
//                    //按我个人理解 这个是获得用户选择的图片的索引值
//                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                    //将光标移至开头 ，这个很重要，不小心很容易引起越界
//                    cursor.moveToFirst();
//                    //最后根据索引值获取图片路径
//                    String path = cursor.getString(column_index);
//                    uploadImage(path);
//                }
//
//            } else if (requestCode == PHOTO_REQUEST_CAREMA) {
//                if (resultCode != RESULT_CANCELED) {
//                    // 从相机返回的数据
//                    if (hasSdcard()) {
//                        if (tempFile != null) {
//                            uploadImage(tempFile.getPath());
//                        } else {
//                            Toast.makeText(this, "相机异常请稍后再试！", Toast.LENGTH_SHORT).show();
//                        }
//
//                        Log.i("images", "拿到照片path=" + tempFile.getPath());
//                    } else {
//                        Toast.makeText(this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//
        }
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0xAAAAAAAA) {
                photoPath(msg.obj.toString());
            }
        }
    };

        public void photoPath(String path) {
            Map<String,Object> map=new HashMap<>();
            map.put("path",path);
            datas.add(map);
            gridViewAddImgesAdpter.notifyDataSetChanged();
    }


    /**
     * 上传图片
     *
     * @param path
     */
    private void uploadImage(final String path) {
        new Thread() {
            @Override
            public void run() {
                if (new File(path).exists()) {
                    Log.d("images", "源文件存在" + path);
                } else {
                    Log.d("images", "源文件不存在" + path);
                }

                File dir = new File(IMAGE_DIR);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                final File file = new File(dir + "/temp_photo" + System.currentTimeMillis() + ".jpg");
                //进行图片压缩与处理（质量压缩）
//                NativeUtil.compressBitmap(path, file.getAbsolutePath(), 50);

                ImageQualityUtil.compressBitmap(path,file.getAbsolutePath(),50);

                if (file.exists()) {
                    Log.d("images", "压缩后的文件存在" + file.getAbsolutePath());
                } else {
                    Log.d("images", "压缩后的不存在" + file.getAbsolutePath());
                }
                Message message = new Message();
                message.what = 0xAAAAAAAA;
                message.obj = file.getAbsolutePath();
                handler.sendMessage(message);
            }
        }.start();
    }

    @Override
    public void dataToView(IntoAddGoodPageBean.DataBean data) {
        this.mdata=data;
        Log.e("mdata", "dataToView: "+mdata.toString() );
        initView();
    }

    @Override
    public void dataToModelView(AddGoodsModelBean modelBean) {

    }
}
