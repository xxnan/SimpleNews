package com.lauren.simplenews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.uct.UCTConfig;
import com.lauren.simplenews.DrawerLayout.DrawerLayoutTest;
import com.lauren.simplenews.SwipeRefreshLayout.TestSwipeRefreshLayoutActivity;
import com.lauren.simplenews.async.TestAsyncTaskActivity;
import com.lauren.simplenews.download.DownLoadActivity;
import com.lauren.simplenews.main.widget.MainActivity;
import com.lauren.simplenews.okhttp.OkhttpTestActivity;
import com.lauren.simplenews.recyclerview.RecyclerViewTest;
import com.lauren.simplenews.view.TestViewActivity;

/**
 * Created by xxnan on 2016/5/19.
 */
public class MainListActivity extends AppCompatActivity {
    private ListView listView;
    private ListAdapter adapter;
    private Class<Activity>[] classList=new Class[]{MainActivity.class,DrawerLayoutTest.class,
            RecyclerViewTest.class,MainRecycleViewActivity.class, OkhttpTestActivity.class,TestAsyncTaskActivity.class,TestViewActivity.class,DownLoadActivity.class,TestSwipeRefreshLayoutActivity.class};
    private String [] title=new String[]{"新闻主界面","DrawerLayout策滑",
            "RecyclerView测试","RecyclerView主界面测试","OkHttp测试","AsyncTask测试","自定义view","DownLoadManager测试","SwipeRefreshLayout测试"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        UCTConfig cfg = new UCTConfig();
        cfg.uct_iSysRunType = 1;
        cfg.is_Awp_lib = 1;
        cfg.uct_use_double_thread_for_audio = 0;
        cfg.uct_echo = 1;
        cfg.uct_iSysNatTime = 1;
    }
    private void initView()
    {
        listView=(ListView)findViewById(R.id.list_main);
        adapter=new ListAdapter(getApplicationContext(),title);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(MainListActivity.this,classList[position]);
                startActivity(intent);
            }
        });
    }
    class ListAdapter extends BaseAdapter
    {
        private String [] mTitle;
        private Context mContext;
        public ListAdapter(Context context,String[] title)
        {
            mTitle=title;
            mContext=context;
        }
        @Override
        public int getCount() {
            return mTitle.length;
        }

        @Override
        public Object getItem(int position) {
            return mTitle[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null)
                convertView= LayoutInflater.from(mContext).inflate(R.layout.main_list_item,null);
            TextView tv=(TextView)convertView.findViewById(R.id.item_view_main_list);
            tv.setText(mTitle[position]);
            return convertView;
        }
    }
}
