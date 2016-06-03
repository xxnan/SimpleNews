package com.lauren.simplenews.async;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lauren.simplenews.R;

/**
 * Created by xxnan on 2016/6/3.
 */
public class TestAsyncTaskActivity extends AppCompatActivity {
    private Button begin;
    private ProgressDialog progressDialog;
    private MyAsyncTask myAsyncTask;
    String url="http://f.hiphotos.baidu.com/image/h%3D300/sign=b4b02e92ce177f3e0f34fa0d40cf3bb9/4afbfbedab64034f1d887e22a8c379310a551d48.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async);
        begin=(Button)findViewById(R.id.begindown);

        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAsyncTask = new MyAsyncTask(TestAsyncTaskActivity.this, progressDialog);
                myAsyncTask.execute(url);
            }
        });

    }
}
