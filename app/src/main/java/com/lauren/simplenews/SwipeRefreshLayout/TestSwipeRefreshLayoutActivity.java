package com.lauren.simplenews.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lauren.simplenews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxnan on 2016/8/16.
 */
public class TestSwipeRefreshLayoutActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<String> list =new ArrayList<String>();
    private MyHandler myHandler;
    private static final int REFRESH_COMPLETE = 0X110;
    private class MyHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    list.add("jsp");
                    list.add("android");
                    myAdapter.notifyDataSetChanged();
                    if(mSwipeRefreshLayout.isRefreshing())
                        mSwipeRefreshLayout.setRefreshing(false);
                    break;
            }
            super.handleMessage(msg);
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh_layout);
        myHandler=new MyHandler();
        mSwipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerview);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(TestSwipeRefreshLayoutActivity.this));
        list.add("java");
        list.add("c");
        list.add("c++");
        list.add("python");
        list.add("object C");

        myAdapter=new MyAdapter(TestSwipeRefreshLayoutActivity.this,list);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onRefresh() {
        myHandler.sendMessage(myHandler.obtainMessage(REFRESH_COMPLETE));
    }
}
