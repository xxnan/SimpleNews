package com.lauren.simplenews.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lauren.simplenews.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by xxnan on 2016/5/19.
 */
public class OkHttpGetActivity extends AppCompatActivity{
    private EditText edit;
    private TextView show;
    private Button getdata_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttp_get);
        edit=(EditText)findViewById(R.id.url_edit);
        show=(TextView)findViewById(R.id.url_show);
        getdata_btn=(Button)findViewById(R.id.url_button);
        getdata_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //okhttp
                OkHttpClient okHttpClient=new OkHttpClient();
                Request request=new Request.Builder().url(edit.getText().toString()).build();

                Call call=okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(final Response response) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                show.setText(response.body().toString());
                            }
                        });
                    }
                });
//                call.cancel();

            }
        });
    }
}
