package com.lauren.simplenews.download;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.File;

/**
 * Created by xxnan on 2016/7/1.
 */
public class DownLoadService extends Service {
    private DownloadManager mDownloadManager;
    long taskId;
    String apkName;
    public class MyBinder extends Binder {
        //此方法是为了可以在Acitity中获得服务的实例
        public DownLoadService getService() {
            return DownLoadService.this;
        }

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mDownloadManager=(DownloadManager) getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);

    }

    public void startDownLoad(String url)
    {
        apkName= url.substring(url.lastIndexOf("/")+1,url.length());
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(url));
        request.setDestinationInExternalPublicDir("/download/",apkName);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        //只有wifi下能下载
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        taskId= mDownloadManager.enqueue(request);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        startDownLoad(DownLoadActivity.url);
        return super.onStartCommand(intent, flags, startId);
    }
    protected void installAPK(File file) {
        if (!file.exists()) return;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("file://" + file.toString());
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        //在服务中开启activity必须设置flag,后面解释
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }
    public void checkDownloadStatus(IDownLoad iDownLoad) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(taskId);//筛选下载任务，传入任务ID，可变参数
        Cursor c = mDownloadManager.query(query);
        if (c.moveToFirst()) {
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            switch (status) {
                case DownloadManager.STATUS_PAUSED:
                    iDownLoad.DownLoad("下载暂停");
                case DownloadManager.STATUS_PENDING:
                    iDownLoad.DownLoad("下载延迟");
                case DownloadManager.STATUS_RUNNING:
                    iDownLoad.DownLoad("正在下载");
                    break;
                case DownloadManager.STATUS_SUCCESSFUL:
                    iDownLoad.DownLoad("下载完成");
                    iDownLoad.finishDownLoad(true);

                    //下载完成安装APK
                    String downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + apkName;
                    installAPK(new File(downloadPath));
                    break;
                case DownloadManager.STATUS_FAILED:
                    iDownLoad.DownLoad("下载失败");
                    break;
            }

        }

    }
}
