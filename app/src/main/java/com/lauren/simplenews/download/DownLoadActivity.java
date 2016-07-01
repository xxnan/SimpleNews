package com.lauren.simplenews.download;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lauren.simplenews.R;

/**
 * Created by xxnan on 2016/7/1.
 */
public class DownLoadActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String url="http://hot.m.shouji.360tpcdn.com/160310/4bb497f1ea5b1c627d9d61ea0c36b0b5/com.qihoo.appstore_300030556.apk";
    private TextView progress_tv;
    private Button begin;
    private EditText edit_url;
    ServiceConnection serviceConnection;
    DownLoadService downLoadService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.down_load);
        begin=(Button)findViewById(R.id.begin_down);
        begin.setOnClickListener(this);
        edit_url=(EditText)findViewById(R.id.edit_url);
        edit_url.setText(url);
        progress_tv=(TextView)findViewById(R.id.progress_tv);
        serviceConnection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                downLoadService=((DownLoadService.MyBinder)service).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                downLoadService=null;
            }
        };
        Intent intent=new Intent();
        intent.setClass(DownLoadActivity.this,DownLoadService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
        registerReceiver(receiver,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
                downLoadService.checkDownloadStatus(new IDownLoad(){
                    @Override
                    public void DownLoad(String result) {
                        progress_tv.setText(result);
                    }
                    @Override
                    public void finishDownLoad(boolean res) {
                        unbindservice();
                    }
                });//检查下载状态
        }
    };

    public void unbindservice()
    {
        Intent intent=new Intent();
        intent.setClass(DownLoadActivity.this,DownLoadService.class);
        unbindService(serviceConnection);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.begin_down:
                downLoadService.startDownLoad(url);
        }
    }

    @Override
    protected void onDestroy() {
        unbindservice();
        super.onDestroy();
    }
}
