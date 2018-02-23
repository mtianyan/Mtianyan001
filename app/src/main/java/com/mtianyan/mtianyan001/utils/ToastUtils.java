package com.mtianyan.mtianyan001.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.mtianyan.mtianyan001.application.BaseApplication;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.utils
 * 文件名：ToastUtils
 * 作者：mtianyan
 * 创建时间：2017/6/4 20:59
 * 描述：
 */
public class ToastUtils {

    public static void showShort(Context context, String text)
    {

        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(Context context, int resId)
    {

        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String text)
    {

        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void showLong(Context context, int resId)
    {

        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

    public static void LongToast(final String text)
    {

        new Handler(Looper.getMainLooper()).post(new Runnable()
        {

            @Override
            public void run()
            {

                Toast.makeText(BaseApplication.getInstance(), text, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void LongToast(final int stringId)
    {

        new Handler(Looper.getMainLooper()).post(new Runnable()
        {

            @Override
            public void run()
            {

                Toast.makeText(BaseApplication.getInstance(), stringId, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void ShortToast(final String text)
    {

        new Handler(Looper.getMainLooper()).post(new Runnable()
        {

            @Override
            public void run()
            {

                Toast.makeText(BaseApplication.getInstance(), text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void ShortToast(final int stringId)
    {

        new Handler(Looper.getMainLooper()).post(new Runnable()
        {

            @Override
            public void run()
            {

                Toast.makeText(BaseApplication.getInstance(), stringId, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
