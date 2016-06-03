package com.lauren.simplenews.okhttp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lauren.simplenews.R;
import com.lauren.simplenews.RecyclerViewAdapter;

/**
 * Created by xxnan on 2016/5/19.
 */
public class OkhttpTestActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private Class<Activity>[] classList=new Class[]{OkHttpGetActivity.class,SnakeBarTest.class};
    private String [] title=new String[]{"Http get请求","SnakeBar测试"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_main);
        recyclerViewAdapter=new RecyclerViewAdapter(getApplicationContext(),title);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerViewAdapter.setListener(new RecyclerViewAdapter.RecycleOnItemClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                intent.setClass(OkhttpTestActivity.this, classList[position]);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
