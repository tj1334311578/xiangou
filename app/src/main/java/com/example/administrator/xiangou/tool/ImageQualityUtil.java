package com.example.administrator.xiangou.tool;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/5/12.
 */

public class ImageQualityUtil {
    private static int DEFAULT_QUALITY = 95;
    private static int outWidth = 720;
    private static int outHeight = 1080;
//    /**
//     * 上传图片
//     *
//     * @param path
//     */
//    private void uploadImage(final String path) {
//        new Thread() {
//            @Override
//            public void run() {
//                if (new File(path).exists()) {
//                    Log.d("images", "源文件存在" + path);
//                } else {
//                    Log.d("images", "源文件不存在" + path);
//                }
//
//                File dir = new File(IMAGE_DIR);
//                if (!dir.exists()) {
//                    dir.mkdir();
//                }
//                final File file = new File(dir + "/temp_photo" + System.currentTimeMillis() + ".jpg");
//                //进行图片压缩与处理（质量压缩）
////                NativeUtil.compressBitmap(path, file.getAbsolutePath(), 50);
//
//                compressBitmap(path,file.getAbsolutePath(),50);
//
//                if (file.exists()) {
//                    Log.d("images", "压缩后的文件存在" + file.getAbsolutePath());
//                } else {
//                    Log.d("images", "压缩后的不存在" + file.getAbsolutePath());
//                }
////                Message message = new Message();
////                message.what = 0xAAAAAAAA;
////                message.obj = file.getAbsolutePath();
////                handler.sendMessage(message);
//            }
//        }.start();
//    }

    public static void compressBitmap(String srcPath, String outPath, int quality) {
        DEFAULT_QUALITY=quality;
        BitmapFactory.Options newOpts=new BitmapFactory.Options();
        newOpts.inJustDecodeBounds=true;
        BitmapFactory.decodeFile(srcPath,newOpts);//此时返回bm为空
        int scanSize=caculateInSampleSize(newOpts, outWidth, outHeight);
        newOpts.inSampleSize=scanSize;
        newOpts.inJustDecodeBounds = false;
        Bitmap outbitmap =BitmapFactory.decodeFile(srcPath,newOpts);
        outbitmap=rotateBitmap(outbitmap,getDegress(srcPath));
        savBitmap(outPath,outbitmap,DEFAULT_QUALITY);
//        saveBitmap(outbitmap,quality,outPath,true);
        if(outbitmap != null && !outbitmap.isRecycled()) {
            outbitmap.recycle();
            System.gc();
        }
        //Donotcompress
//        newOpts.inSampleSize=1;
//        newOpts.inPreferredConfig= Bitmap.Config.RGB_565;
//        return BitmapFactory.decodeFile(outPath,newOpts);
    }

    private static void savBitmap(String outPath, Bitmap bm, int quality) {
        File f=new File(outPath);
        if (f.exists()){
            f.delete();
        }
        try{
            FileOutputStream out=new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG,quality,out);
            out.flush();
            out.close();
            Log.e("TGA", "savBitmap:已保存 " );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static int caculateInSampleSize(BitmapFactory.Options newOpts, int outWidth, int outHeight) {
        int height = newOpts.outHeight;
        int width = newOpts.outWidth;
        int inSampleSize = 1;
        if(outWidth != 0 && outHeight != 0) {
            if(height > outHeight || width > outWidth) {
                int heightRatio = Math.round((float)height / (float)outHeight);
                int widthRatio = Math.round((float)width / (float)outWidth);
                inSampleSize = heightRatio < widthRatio?heightRatio:widthRatio;
            }

            return inSampleSize;
        } else {
            return 1;
        }
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int degress) {
        if(bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate((float)degress);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
            return bitmap;
        } else {
            return bitmap;
        }
    }

    @TargetApi(5)
    public static final int getDegress(String path) {
        short degree = 0;

        try {
            ExifInterface e = new ExifInterface(path);
            int orientation = e.getAttributeInt("Orientation", 1);
            switch(orientation) {
                case 3:
                    degree = 180;
                    break;
                case 6:
                    degree = 90;
                    break;
                case 8:
                    degree = 270;
            }
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return degree;
    }
}
