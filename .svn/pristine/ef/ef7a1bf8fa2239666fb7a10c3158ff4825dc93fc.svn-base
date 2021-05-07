package com.xyl.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 47500 on 2017/5/24.
 */
public class ImageBitmapUtil {

    public static Bitmap loadingImageBitmap(String imagePath, int width, int height) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(imagePath, options);
            /**
             * 计算手机宽高与显示大图的宽高，然后确定缩放有比例
             */
            int widthRaio = (int) Math.ceil(options.outWidth / (float) width);
            int heightRaio = (int) Math.ceil(options.outHeight / (float) height);
            if (widthRaio > 1 && heightRaio > 1) {
                if (widthRaio > heightRaio) {
                    options.inSampleSize = widthRaio;
                } else {
                    options.inSampleSize = heightRaio;
                }
            }
            /**
             * 设置加载缩放后的图片
             */
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(imagePath, options);
            bitmap = CommonUtil.zoomImg(bitmap, width * 3 / 4, height * 3 / 4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }


    public static String  getFilePath(){
        String mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/systemCemer";
        //存储文件夹操作
        File outFilePath = new File(mFilePath);
        if (!outFilePath.exists()) {
            outFilePath.mkdirs();
        }
        //设置自定义照片的名字
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        mFilePath = mFilePath + "/" + fileName + ".jpg";
        return mFilePath;
    }


}
