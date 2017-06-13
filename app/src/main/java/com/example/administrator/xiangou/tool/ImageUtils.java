package com.example.administrator.xiangou.tool;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 图片简单处理工具类
 */
public class ImageUtils {

	private static int imgSize = 100;//图片最大值，单位 kb
	
	/**
	 * 屏幕宽
	 * @param context
	 * @return
	 */
	public static int getWidth(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.widthPixels;
	}
	/**
	 * 屏幕高
	 * @param context
	 * @return
	 */
	public static int getHeight(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.heightPixels;
	}

	public static void loadLocationImg(Context context, Uri imgUri, ImageView imageView){
		if (imgUri!=null){
			File imgFile = getFileFromMediaUri(context,imgUri);
			try {
				Bitmap bitmap = getBitmapFormUri(context,imgUri);
				int degree = getBitmapDegree(imgFile.getAbsolutePath());
				if (degree%360!=0){//如果选择照片后返回来的图片居然转了角度
					Bitmap newBitmap = rotateBitmapByDegree(bitmap,degree);
					imageView.setImageBitmap(newBitmap);
				}else {
					imageView.setImageBitmap(bitmap);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * 根据文件Uri获取路径
	 * @param context
	 * @param uri
	 * @return
	 */
	public static String getFilePathByFileUri(Context context, Uri uri) {
		String filePath = null;
		Cursor cursor = context.getContentResolver().query(uri, null, null,
				null, null);
		if (cursor.moveToFirst()) {
			filePath = cursor.getString(cursor
					.getColumnIndex(MediaStore.Images.Media.DATA));
		}
		cursor.close();
		return filePath;
	}

	/**
	 * 通过Uri获取文件
	 * @param ac
	 * @param uri
	 * @return
	 */
	public static File getFileFromMediaUri(Context ac, Uri uri) {
		if(uri.getScheme().toString().compareTo("content") == 0){
			ContentResolver cr = ac.getContentResolver();
			Cursor cursor = cr.query(uri, null, null, null, null);// 根据Uri从数据库中找
			if (cursor != null) {
				cursor.moveToFirst();
				String filePath = cursor.getString(cursor.getColumnIndex("_data"));// 获取图片路径
				cursor.close();
				if (filePath != null) {
					return new File(filePath);
				}
			}
		}else if(uri.getScheme().toString().compareTo("file") == 0){
			return new File(uri.toString().replace("file://",""));
		}
		return null;
	}

	/**
	 * 根据图片原始路径获取图片缩略图
	 * 
	 * @param imagePath 图片原始路径
	 * @param width		缩略图宽度
	 * @param height	缩略图高度
	 * @return
	 */
	public static Bitmap getImageThumbnail(String imagePath, int width,
										   int height) {
		Bitmap bitmap = null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;//不加载直接获取Bitmap宽高
		// 获取这个图片的宽和高，注意此处的bitmap为null
		bitmap = BitmapFactory.decodeFile(imagePath, options);
		if(bitmap == null){
		// 计算缩放比
		int h = options.outHeight;
		int w = options.outWidth;
		int beWidth = w / width;
		int beHeight = h / height;
		int rate = 1;
		if (beWidth < beHeight) {
			rate = beWidth;
		} else {
			rate = beHeight;
		}
		if (rate <= 0) {//图片实际大小小于缩略图,不缩放
			rate = 1;
		}
		options.inSampleSize = rate;
		options.inJustDecodeBounds = false; 
		// 重新读入图片，读取缩放后的bitmap，注意这次要把options.inJustDecodeBounds 设为 false
		bitmap = BitmapFactory.decodeFile(imagePath, options);
		// 利用ThumbnailUtils来创建缩略图，这里要指定要缩放哪个Bitmap对象
		bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
				ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		}
		return bitmap;
	}

	/**
	 * 通过uri获取图片并进行压缩
	 * @param uri
	 */
	public static Bitmap getBitmapFormUri(Context ac, Uri uri) throws FileNotFoundException, IOException {
		InputStream input = ac.getContentResolver().openInputStream(uri);
		BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
		onlyBoundsOptions.inJustDecodeBounds = true;
		onlyBoundsOptions.inDither = true;//optional
		onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
		BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
		input.close();
		int originalWidth = onlyBoundsOptions.outWidth;
		int originalHeight = onlyBoundsOptions.outHeight;
		if ((originalWidth == -1) || (originalHeight == -1))
			return null;
		//图片分辨率以480x800为标准
		float hh = 800f;//这里设置高度为800f
		float ww = 480f;//这里设置宽度为480f
		//缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;//be=1表示不缩放
		if (originalWidth > originalHeight && originalWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
			be = (int) (originalWidth / ww);
		} else if (originalWidth < originalHeight && originalHeight > hh) {//如果高度高的话根据宽度固定大小缩放
			be = (int) (originalHeight / hh);
		}
		if (be <= 0)
			be = 1;
		//比例压缩
		BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
		bitmapOptions.inSampleSize = be;//设置缩放比例
		bitmapOptions.inDither = true;//optional
		bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
		input = ac.getContentResolver().openInputStream(uri);
		Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
		input.close();

		return compressImage(bitmap);//再进行质量压缩
	}
	/**
	 * 质量压缩方法
	 * @param image
	 * @return
	 */
	public static Bitmap compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while (baos.toByteArray().length / 1024 > imgSize) {  //循环判断如果压缩后图片是否大于imgSize kb,大于继续压缩
			baos.reset();//重置baos即清空baos
			//第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;//每次都减少10
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
		return bitmap;
	}

	/**
	 * 读取图片的旋转的角度
	 * @param path 图片绝对路径
	 * @return 图片的旋转角度
	 */
	public static int getBitmapDegree(String path) {
		int degree = 0;
		try {
			// 从指定路径下读取图片，并获取其EXIF信息
			ExifInterface exifInterface = new ExifInterface(path);
			// 获取图片的旋转信息
			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree = 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					degree = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					degree = 270;
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

	/**
	 * 将图片按照某个角度进行旋转
	 * @param bm     需要旋转的图片
	 * @param degree 旋转角度
	 * @return 旋转后的图片
	 */
	public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
		Bitmap returnBm = null;
		// 根据旋转角度，生成旋转矩阵
		Matrix matrix = new Matrix();
		matrix.postRotate(degree);
		try {
			// 将原始图片按照旋转矩阵进行旋转，并得到新的图片
			returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
		} catch (OutOfMemoryError e) {
		}
		if (returnBm == null) {
			returnBm = bm;
		}
		if (bm != returnBm) {
			bm.recycle();
		}
		return returnBm;
	}
}
