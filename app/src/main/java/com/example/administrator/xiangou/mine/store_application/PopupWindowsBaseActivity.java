package com.example.administrator.xiangou.mine.store_application;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.tool.BaseActivity;
import com.example.administrator.xiangou.tool.SelectPicPopupWindow;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2017/3/28.
 */

public abstract class PopupWindowsBaseActivity extends BaseActivity {
    public void setShowatlocation(int showatlocation) {
        this.showatlocation = showatlocation;
    }

    private int showatlocation=R.id.application_parent_rl;
    /**
     * 选择图片的返回码
     */
//    public final static int SELECT_IMAGE_RESULT_CODE = 200;
    /**
     * 当前选择的图片的路径
     */
    public String mImagePath;
    /**
     * 自定义的PopupWindow
     */
    private SelectPicPopupWindow menuWindow;
    public String imgpath;
    /**
     * 拍照或从图库选择图片(Dialog形式)
     */
    public void showPictureDailog( ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(new String[] { "拍摄照片", "选择照片", "取消" },
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0://拍照
//                                takePhoto();
                                break;
                            case 1://相册选择图片
//                                pickPhoto();
                                break;
                            case 2://取消
                                break;
                            default:
                                break;
                        }
                    }
                });
        builder.create().show();
    }

    /**
     * 拍照或从图库选择图片(PopupWindow形式)
     */
    public String  showPicturePopupWindow(final int tag){
        menuWindow = new SelectPicPopupWindow(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 隐藏弹出窗口
                menuWindow.dismiss();
                switch (v.getId()) {
                    case R.id.takePhotoBtn:// 拍照
                        imgpath =  takePhoto(tag);
                        break;
                    case R.id.pickPhotoBtn:// 相册
                        imgpath=pickPhoto(tag);
                        break;
                    case R.id.cancelBtn:// 取消
                        break;
                    default:
                        break;
                }
            }
        });
        menuWindow.showAtLocation(LayoutInflater.from(this).inflate(R.layout.application_store,null,false), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        return imgpath;
    }

    /**
     * 拍照获取图片
     */
    private String takePhoto(int tag) {
        // 执行拍照前，应该先判断SD卡是否存在
        String SDState = Environment.getExternalStorageState();
        if (SDState.equals(Environment.MEDIA_MOUNTED)) {
            /**
             * 通过指定图片存储路径，解决部分机型onActivityResult回调 data返回为null的情况
             */
            //获取与应用相关联的路径
            String imageFilePath = getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
            //根据当前时间生成图片的名称
            String timestamp = "/"+formatter.format(new Date())+".jpg";
            File imageFile = new File(imageFilePath,timestamp);// 通过路径创建保存文件
            mImagePath = imageFile.getAbsolutePath();
            Uri imageFileUri = Uri.fromFile(imageFile);// 获取文件的Uri
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageFileUri);// 告诉相机拍摄完毕输出图片到指定的Uri
//            startActivityForResult(intent, SELECT_IMAGE_RESULT_CODE);
            startActivityForResult(intent, tag);
            showToast(""+tag+" "+mImagePath);
        } else {
            Toast.makeText(this, "内存卡不存在!", Toast.LENGTH_LONG).show();
        }
        return mImagePath;
    }
    /***
     * 从相册中取图片
     */
    private String pickPhoto(int tag) {
        Intent intent = new Intent(Intent.ACTION_PICK);//隐式跳转获取相册中的图片
//      intent.putExtra("position","2");
        intent.setType("image/*");
        startActivityForResult(intent, tag);
        Log.e("flag", "pickPhoto: "+mImagePath );
        return mImagePath;
    }
}
