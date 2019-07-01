package com.yhbc.base.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import com.yhbc.base.BaseApplication;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alan on 2015/12/22.
 */
public class FileUtil {

    public static String fileDirPath = Environment.getExternalStorageDirectory().getPath();

    /**
     * 获取程序APP根目录
     *
     * @return
     */
    public static String getRootCache() {
        File externalCacheDir = BaseApplication.baseApp.getExternalCacheDir();
        if (externalCacheDir == null && !externalCacheDir.exists()) {
            externalCacheDir.mkdirs();
        }
        return externalCacheDir.getAbsolutePath();
    }

    /**
     * 获取图片的保存路径
     *
     * @return
     */
    public static File getImageCache() {
        String imageCache = getRootCache() + File.separator + AppConfig.APP_IMAGE_CACHE;
        File file = new File(imageCache);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    /**
     * 获取双屏自定义图片的保存路径
     *
     * @return
     */
    public static File getPictureuserdefined() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) { // 文件可用
            String fileName = "winboxcash/pictureuserdefined";
            String imageCache = Environment.getExternalStorageDirectory().toString() + File.separator
                    + fileName;
            File file = new File(imageCache);
            if (!file.exists()) {
                file.mkdir();
            }
            return file;
        } else {
            return null;
        }
    }

    /**
     * 获取双屏自定义图片的保存路径
     *
     * @return
     */
    public static File getPictureGoods() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) { // 文件可用
            String fileName = "winboxcash/picturegoods";
            String imageCache = Environment.getExternalStorageDirectory().toString() + File.separator
                    + fileName;
            File file = new File(imageCache);
            if (!file.exists()) {
                file.mkdir();
            }
            return file;
        } else {
            return null;
        }
    }

    public static void writeFileDetail(String path, String detail) {

        File file = new File(path);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        try {

            FileOutputStream os = new FileOutputStream(file);

            byte[] len = detail.getBytes("utf-8");

            os.write(len);

            os.flush();

            os.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public synchronized static String readFileDetail(String path) {

        File file = new File(path);

        if (file.exists()) {
            FileInputStream in = null;
            try {
                StringBuffer sb = new StringBuffer();
                in = new FileInputStream(file);
                byte[] tempbytes = new byte[1024];
                int byteread = 0;
                in = new FileInputStream(path);
                // 读入多个字节到字节数组中，byteread为一次读入的字节数
                while ((byteread = in.read(tempbytes)) != -1) {
                    String str = new String(tempbytes, 0, byteread);
                    sb.append(str);
                }
                return sb.toString();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /**
     * 从SD卡获取图片资源
     */
    private static List<String> imagePathList = new ArrayList<>();

    public static List<String> getImagePathFromSD(String filePath, boolean clearFlag) {
        if (clearFlag) {
            imagePathList.clear();
        }
        // 得到该路径文件夹下所有的文件
        File fileAll = new File(filePath);
        if (null != fileAll && fileAll.exists()) {
            File[] files = fileAll.listFiles();
            if (null != files && files.length > 0) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        getImagePathFromSD(file.getPath(), false);
                    } else {
                        if (checkIsImageFile(file.getPath())) {
                            imagePathList.add(file.getPath());
                        }
                    }
                }
                // 返回得到的图片列表
            }
        }
        return imagePathList;
//        if (!fileAll.exists()){
//            return null;
//        }
//        File[] files = fileAll.listFiles();
//        if (files==null) {
//            return null;
//        }
        // 将所有的文件存入ArrayList中,并过滤所有图片格式的文件

    }

    /**
     * 获取APP目录下的缓存的自定义图片
     */
    public static List<String> getImagePathFromAppRoot() {
        // 图片列表
        List<String> imagePathList = new ArrayList<>();
        // 得到sd卡内image文件夹的路径   File.separator(/)
        String filePath = getPictureuserdefined().getAbsolutePath();
        // 得到该路径文件夹下所有的文件
        File fileAll = new File(filePath);
        File[] files = fileAll.listFiles();
        // 将所有的文件存入ArrayList中,并过滤所有图片格式的文件
        if (null != files) {
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (checkIsImageFile(file.getPath())) {
                    imagePathList.add(file.getPath());
                }
            }
        }
        // 返回得到的图片列表
        return imagePathList;
    }

    /**
     * 检查是否是图片资源
     */
    private static boolean checkIsImageFile(String fName) {
        boolean isImageFile = false;
        // 获取扩展名
        String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
                fName.length()).toLowerCase();
        if (FileEnd.equals("jpg") || FileEnd.equals("png") || FileEnd.equals("gif")
                || FileEnd.equals("jpeg") || FileEnd.equals("bmp")) {
            isImageFile = true;
        } else {
            isImageFile = false;
        }
        return isImageFile;
    }

    /**
     * 通过uri获取bitamap对象
     */
    public static Bitmap getBitmapFormUri(Context context, String goodsImageUri, Bitmap.CompressFormat format, int heightPixels, int widthPixels) {
        try {
            Uri uri = Uri.parse(goodsImageUri);
            InputStream input = null;

            input = context.getContentResolver().openInputStream(uri);

            BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
            onlyBoundsOptions.inJustDecodeBounds = true;
            onlyBoundsOptions.inDither = true;//optional
            onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
            BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
            input.close();
            //图片分辨率以480x800为标准
            float hh = 1024f;//这里设置高度为800f
            float ww = 1024f;//这里设置宽度为480f

            //比例压缩
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            bitmapOptions.inSampleSize = calculateInSampleSize(onlyBoundsOptions, hh, ww);//设置缩放比例
            bitmapOptions.inDither = true;//optional
            onlyBoundsOptions.inJustDecodeBounds = false;
            bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
            input = context.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
            input.close();
            return compressImage(bitmap, format);//再进行质量压缩
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, float reqHeight, float reqWidth) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        //计算图片的宽高跟屏幕的宽高的比例
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {  //只要超过屏幕尺寸，才做缩放
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);  //ronnd四舍五入
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
            for (int i = 1; i < 5; i++) { //2^5=32，安卓中的位图是32位的，占4个字节，所以加载进来的图片不能大于这个位数
                if (inSampleSize < Math.pow(2, i)) {
                    inSampleSize = (int) Math.pow(2, i);//4，取到小于该采样率的最大数
                    break;
                }
            }
        }
        return inSampleSize;
    }


    /**
     * 图片质量压缩
     */
    private static Bitmap compressImage(Bitmap image, Bitmap.CompressFormat format) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(format, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (options >= 10 && baos.toByteArray().length / 1024 > 1024) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10, //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
            image.compress(format, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * 轮播图片缓存
     *
     * @param bitmap
     * @param name
     */
    public static String savePNG_After(Bitmap bitmap, String name) {
        File file = new File(name, TimeUtils.getNowDate3() + ".png");
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            return file.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 删除指定文件夹
     */

    public static void delete(String path) {
        File f = new File(path);
        if (f.isDirectory()) {//如果是目录，先递归删除
            String[] list = f.list();
            for (int i = 0; i < list.length; i++) {
//                delete(path + "//" + list[i]);//先删除目录下的文件
                delete(path + File.separator + list[i]);//先删除目录下的文件
            }
        }
        f.delete();
    }

    public static void copyFolder(String oldPath, String newPath) {
        try {
            (new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" +
                            (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {//如果是子文件夹
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public static File getDCIMFile(String filePath, String imageName) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) { // 文件可用
            File dirs = new File(Environment.getExternalStorageDirectory(),
                    filePath);
            if (!dirs.exists())
                dirs.mkdirs();

            File file = new File(dirs,
                    imageName);
            if (!file.exists()) {
                try {
                    //在指定的文件夹中创建文件
                    file.createNewFile();
                } catch (Exception e) {
                }
            }
            return file;
        } else {
            return null;
        }
    }

    public static String saveRes(Context context, int res, String name) {
        try {
            InputStream is = context.getResources().openRawResource(res);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            String defaultPath = context.getFilesDir()
                    .getAbsolutePath() + "/" + name;
            File file = new File(defaultPath);
            if (!file.exists()) {
                file.mkdirs();
            } else {
                String defaultImgPath = defaultPath + TimeUtils.getNowDate3() + ".png";
                file = new File(defaultImgPath);
                file.createNewFile();
                FileOutputStream fOut = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 20, fOut);
                is.close();
                fOut.flush();
                fOut.close();
            }
            return file.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}