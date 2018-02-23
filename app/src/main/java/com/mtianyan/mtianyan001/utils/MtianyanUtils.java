package com.mtianyan.mtianyan001.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.RequiresApi;

import java.io.File;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.utils
 * 文件名：MtianyanUtils
 * 作者：mtianyan
 * 创建时间：2017/6/4 20:46
 * 描述：各种个人自用工具类
 */
public class MtianyanUtils {
    /**
     * 检查是否有网络
     */
    public static boolean isNetworkAvailable(Context context)
    {

        NetworkInfo info = getNetworkInfo(context);
        if (info != null)
        {
            return info.isAvailable();
        }
        return false;
    }

    /**
     * 检查是否是WIFI
     */
    public static boolean isWifi(Context context)
    {

        NetworkInfo info = getNetworkInfo(context);
        if (info != null)
        {
            if (info.getType() == ConnectivityManager.TYPE_WIFI)
                return true;
        }
        return false;
    }

    /**
     * 检查是否是移动网络
     */
    public static boolean isMobile(Context context)
    {

        NetworkInfo info = getNetworkInfo(context);
        if (info != null)
        {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE)
                return true;
        }
        return false;
    }

    private static NetworkInfo getNetworkInfo(Context context)
    {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * 检查SD卡是否存在
     */
    public static boolean checkSdCard()
    {

        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }


    /**
     * 获取手机SD卡总空间
     *
     * @return
     */
    public static long getSDcardTotalSize()
    {

        if (checkSdCard())
        {
            File path = Environment.getExternalStorageDirectory();
            StatFs mStatFs = new StatFs(path.getPath());
            long blockSizeLong = mStatFs.getBlockSizeLong();
            long blockCountLong = mStatFs.getBlockCountLong();

            long totalSize = blockSizeLong * blockCountLong;
            return totalSize;
        } else
        {
            return 0;
        }
    }

    /**
     * 获取SDka可用空间
     *
     * @return
     */
    public static long getSDcardAvailableSize()
    {

        if (checkSdCard())
        {
            File path = Environment.getExternalStorageDirectory();
            StatFs mStatFs = new StatFs(path.getPath());
            long blockSizeLong = mStatFs.getBlockSizeLong();
            long availableBlocksLong = mStatFs.getAvailableBlocksLong();
            long availabSize = blockSizeLong * availableBlocksLong;
            return availabSize;
        } else
        {
            return 0;
        }
    }


    /**
     * 获取手机内部存储总空间
     *
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static long getPhoneTotalSize()
    {

        if (!checkSdCard())
        {
            File path = Environment.getDataDirectory();
            StatFs mStatFs = new StatFs(path.getPath());
            long blockSizeLong = mStatFs.getBlockSizeLong();
            long blockCountLong = mStatFs.getBlockCountLong();
            long totalSize = blockSizeLong * blockCountLong;
            return totalSize;
        } else
        {
            return getSDcardTotalSize();
        }
    }


    /**
     * 获取手机内存存储可用空间
     *
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static long getPhoneAvailableSize()
    {

        if (!checkSdCard())
        {
            File path = Environment.getDataDirectory();
            StatFs mStatFs = new StatFs(path.getPath());
            long blockSizeLong = mStatFs.getBlockSizeLong();
            long availableBlocksLong = mStatFs.getAvailableBlocksLong();
            long availabSize = blockSizeLong * availableBlocksLong;
            return availabSize;
        } else
        {
            return getSDcardAvailableSize();
        }
    }
}
