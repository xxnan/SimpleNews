package com.lauren.simplenews.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lauren.simplenews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxnan on 2016/6/24.
 */
public class TestViewActivity extends AppCompatActivity {
    private MyListView myListView;
    private List<String> list=new ArrayList<String>();
    private MyListView.delListener listener;
    private MyListView.scrollTo scrollTo;
    private  MyAdapter myAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);
        initView();
        initData();
        myAdapter=new MyAdapter();
        myListView.setAdapter(myAdapter);
        myListView.setDelListener(new MyListView.delListener() {
            @Override
            public void del(int pos) {
                list.remove(pos);
                myAdapter.notifyDataSetChanged();
            }
        });
        myListView.setscrollListener(new MyListView.scrollTo() {
            @Override
            public void scroll(int pos,int x,int y) {
                myListView.getChildAt(pos).scrollTo(x,y);
            }
        });
    }
    private void initData()
    {
        for(int i=0;i<10;i++)
            list.add("ItemAAAAAAAAAAAAAAAAAAAA"+i);
    }
    private void initView()
    {
        myListView=(MyListView)findViewById(R.id.my_list);
    }
    class  MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null)
            {
                convertView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.view_list_item,null);
            }
            TextView textView=(TextView) convertView.findViewById(R.id.text_view);
            textView.setText(list.get(position));
            return convertView;
        }
    }
}
