package com.lauren.simplenews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lauren.simplenews.DrawerLayout.DrawerLayoutTest;
import com.lauren.simplenews.main.widget.MainActivity;
import com.lauren.simplenews.recyclerview.RecycleViewDivider;
import com.lauren.simplenews.recyclerview.RecyclerViewTest;

/**
 * Created by xxnan on 2016/5/19.
 */
public class MainRecycleViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private Class<Activity>[] classList=new Class[]{MainActivity.class,DrawerLayoutTest.class,RecyclerViewTest.class};
    private String [] title=new String[]{"新闻主界面","DrawerLayout策滑","RecyclerView测试"};
    RecyclerViewAdapter.RecycleOnItemClick recycleOnItemClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_main);
        recyclerViewAdapter=new RecyclerViewAdapter(getApplicationContext(),title);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new RecycleViewDivider(getApplicationContext(), LinearLayoutManager.HORIZONTAL),10);
        recyclerViewAdapter.setListener(new RecyclerViewAdapter.RecycleOnItemClick() {
            @Override
            public void onItemClick(int position) {
                    Intent intent=new Intent();
                    intent.setClass(MainRecycleViewActivity.this,classList[position]);
                    startActivity(intent);
            }
        });
        recyclerView.setAdapter(recyclerViewAdapter);
    }


}
