package com.example.administrator.xiangou.mine.setting.feedback;


import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Paint;
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
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;
import com.example.administrator.xiangou.tool.ImageQualityUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class FeedBackActivity extends MVPBaseActivity<FeedBackContract.View, FeedBackPresenter> implements FeedBackContract.View {
    private ImageView back;
    private TextView feedbackTv,submitTv;
    private GridView gridview;

    private static int DEFAULT_QUALITY = 95;
    private static int outWidth = 720;
    private static int outHeight = 1080;

    private List<Map<String, Object>> datas;
    private GridViewAddImgesAdpter gridViewAddImgesAdpter;
    private Dialog dialog;
    private final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;
    private final String IMAGE_DIR = Environment.getExternalStorageDirectory() + "/gridview/";
    /* 头像名称 */
    private final String PHOTO_FILE_NAME = "temp_photo.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        back=findContentView(R.id.setting_head_back);
        feedbackTv=findContentView(R.id.setting_head_center,false);
        submitTv=findContentView(R.id.setting_head_right);
        gridview=findContentView(R.id.feedback_gridview,false);
        initView();

    }
    //复用头布局更改布局内容
    private void initView() {
        feedbackTv.setText("意见反馈");
        submitTv.setText("提交");
        submitTv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        //上传图片相关
        datas = new ArrayList<>();
        gridViewAddImgesAdpter=new GridViewAddImgesAdpter(this,datas);
        //设置grideview的列数以及水平，垂直view之间的间距
        gridview.setNumColumns(4);
        gridview.setVerticalSpacing(10);
        gridview.setAdapter((ListAdapter) gridViewAddImgesAdpter);
        //给在其他区域点击操作
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showdialog();
            }
        });
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

    @Override
    public void sendFialRequest(String message) {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_head_back:
                finish();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PHOTO_REQUEST_GALLERY) {
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

            } else if (requestCode == PHOTO_REQUEST_CAREMA) {
                if (resultCode != RESULT_CANCELED) {
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
                }
            }

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

//    private void compressBitmap(String srcPath, String outPath, int quality) {
//        DEFAULT_QUALITY=quality;
//        BitmapFactory.Options newOpts=new BitmapFactory.Options();
//        newOpts.inJustDecodeBounds=true;
//        BitmapFactory.decodeFile(srcPath,newOpts);//此时返回bm为空
//        int scanSize=caculateInSampleSize(newOpts, outWidth, outHeight);
//        newOpts.inSampleSize=scanSize;
//        newOpts.inJustDecodeBounds = false;
//        Bitmap outbitmap =BitmapFactory.decodeFile(srcPath,newOpts);
//        outbitmap=rotateBitmap(outbitmap,getDegress(srcPath));
//        savBitmap(outPath,outbitmap,DEFAULT_QUALITY);
////        saveBitmap(outbitmap,quality,outPath,true);
//        if(outbitmap != null && !outbitmap.isRecycled()) {
//            outbitmap.recycle();
//            System.gc();
//        }
//       //Donotcompress
////        newOpts.inSampleSize=1;
////        newOpts.inPreferredConfig= Bitmap.Config.RGB_565;
////        return BitmapFactory.decodeFile(outPath,newOpts);
//    }

//    private void savBitmap(String outPath, Bitmap bm, int quality) {
//        File f=new File(outPath);
//        if (f.exists()){
//            f.delete();
//        }
//        try{
//            FileOutputStream out=new FileOutputStream(f);
//            bm.compress(Bitmap.CompressFormat.PNG,quality,out);
//            out.flush();
//            out.close();
//            Log.e("TGA", "savBitmap:已保存 " );
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


//    private int caculateInSampleSize(BitmapFactory.Options newOpts, int outWidth, int outHeight) {
//        int height = newOpts.outHeight;
//        int width = newOpts.outWidth;
//        int inSampleSize = 1;
//        if(outWidth != 0 && outHeight != 0) {
//            if(height > outHeight || width > outWidth) {
//                int heightRatio = Math.round((float)height / (float)outHeight);
//                int widthRatio = Math.round((float)width / (float)outWidth);
//                inSampleSize = heightRatio < widthRatio?heightRatio:widthRatio;
//            }
//
//            return inSampleSize;
//        } else {
//            return 1;
//        }
//    }


    public void photoPath(String path) {
        Map<String,Object> map=new HashMap<>();
        map.put("path",path);
        datas.add(map);
        gridViewAddImgesAdpter.notifyDataSetChanged();
    }
//    public static Bitmap rotateBitmap(Bitmap bitmap, int degress) {
//        if(bitmap != null) {
//            Matrix m = new Matrix();
//            m.postRotate((float)degress);
//            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
//            return bitmap;
//        } else {
//            return bitmap;
//        }
//    }
//
//    @TargetApi(5)
//    public static final int getDegress(String path) {
//        short degree = 0;
//
//        try {
//            ExifInterface e = new ExifInterface(path);
//            int orientation = e.getAttributeInt("Orientation", 1);
//            switch(orientation) {
//                case 3:
//                    degree = 180;
//                    break;
//                case 6:
//                    degree = 90;
//                    break;
//                case 8:
//                    degree = 270;
//            }
//        } catch (IOException var4) {
//            var4.printStackTrace();
//        }
//
//        return degree;
//    }
   }
