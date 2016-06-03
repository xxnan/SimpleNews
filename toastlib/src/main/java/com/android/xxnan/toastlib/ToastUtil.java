package com.android.xxnan.toastlib;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xxnan on 2016/4/21.
 */
public class ToastUtil {
    private static Toast toast ;
    private static ToastUtil toastUtil =new ToastUtil();
    public synchronized static ToastUtil getInstance()
    {
        return toastUtil;
    }

    public void showToast(Context context,String text)
    {
        if(toast==null)
        {
            synchronized (this) {
                toast =  Toast.makeText(context, text, Toast.LENGTH_SHORT);
            }
        }else
        {
            toast.setText(text);
            toast.setDuration( Toast.LENGTH_SHORT);
        }
        toast.show();
    }
    public void showToast(Context context,String text,int ms)
    {
        if(toast==null)
        {
            synchronized (this) {
                toast =  Toast.makeText(context, text, ms);
            }
        }else
        {
            toast.setText(text);
            toast.setDuration(ms);
        }
        toast.show();
    }
    public void cancelToast()
    {
        if(toast!=null)
            toast.cancel();
    }
}
