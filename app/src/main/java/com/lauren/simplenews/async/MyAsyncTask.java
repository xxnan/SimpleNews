package com.lauren.simplenews.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.android.xxnan.toastlib.ToastUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by xxnan on 2016/6/3.
 */
public class MyAsyncTask extends AsyncTask<String,Integer,Boolean> {
    private ProgressDialog progressDialog;
    private Context mContext;
    public MyAsyncTask(Context context,ProgressDialog progressDialog)
    {
        this.progressDialog=progressDialog;
        mContext=context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=new ProgressDialog(mContext);
        progressDialog.setTitle("下载进度");
        progressDialog.setProgress(0);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected Boolean doInBackground(String... params) {
        HttpURLConnection httpURLConnection=null;
        try{
            URL url=new URL(params[0]);
            httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setRequestMethod("GET");

            httpURLConnection.setReadTimeout(3000);
            int filelegth=0;
            if(httpURLConnection.getResponseCode()==200)
            {
                filelegth=httpURLConnection.getContentLength();
                File file=new File("/mnt/sdcard/async/");
                if(!file.exists())
                    file.mkdir();
                File f=new File(file.getAbsolutePath(),"qiaoen.jpg");
                InputStream inputStream=null;
                FileOutputStream fileOutputStream=new FileOutputStream(f);
                inputStream=httpURLConnection.getInputStream();
                int len=0;
                long totalsize=0;
                byte[] bytes=new byte[1024];
                while((len=inputStream.read(bytes))!=-1)
                {
                    fileOutputStream.write(bytes,0,len);
                    totalsize+=len*100;
                    int progress=(int)(totalsize/filelegth);
                    publishProgress(progress);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }finally {
            httpURLConnection=null;
        }

        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        progressDialog.dismiss();
        if (aBoolean)
        {
            ToastUtil.getInstance().showToast(mContext, "下载成功!");
        }else
            ToastUtil.getInstance().showToast(mContext, "下载失败!");
    }
}
