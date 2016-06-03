package com.lauren.simplenews.DrawerLayout;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.lauren.simplenews.R;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by xxnan on 2016/5/17.
 */
public class DrawerLayoutTest extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private LinearLayout leftLayout;
    private LinearLayout rightLayout;
    private List<ContentModel> list;
    private ContentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        leftLayout=(LinearLayout) findViewById(R.id.left);
        rightLayout=(LinearLayout) findViewById(R.id.right);
        ListView listView=(ListView)leftLayout.findViewById(R.id.left_listview);
        initData();
        adapter=new ContentAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),list.get(position).getText(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initData() {
        list=new ArrayList<ContentModel>();
        list.add(new ContentModel(R.drawable.doctoradvice2, "新闻"));
        list.add(new ContentModel(R.drawable.infusion_selected, "订阅"));
        list.add(new ContentModel(R.drawable.mypatient_selected, "图片"));
        list.add(new ContentModel(R.drawable.mywork_selected, "视频"));
        list.add(new ContentModel(R.drawable.nursingcareplan2, "跟帖"));
        list.add(new ContentModel(R.drawable.personal_selected, "投票"));
    }
}
