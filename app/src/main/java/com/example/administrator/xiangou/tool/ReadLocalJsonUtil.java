package com.example.administrator.xiangou.tool;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/4/11.
 */

public class ReadLocalJsonUtil {

    /**
     * 从asset目录下读取城市json文件转化为String类型
     *
     * @Title: InitData
     * @param
     * @return void
     * @throws @date
     *             [2017年4月12日 上午10:22:00]
     */
    public static String InitData(Context context) {
        StringBuffer sb = new StringBuffer();
        AssetManager mAssetManager = context.getAssets();
        try {
            InputStream is = mAssetManager.open("city.json");
            byte[] data = new byte[is.available()];
            int len = -1;
            while ((len = is.read(data)) != -1) {
                sb.append(new String(data, 0, len, "UTF-8"));
            }
            is.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
