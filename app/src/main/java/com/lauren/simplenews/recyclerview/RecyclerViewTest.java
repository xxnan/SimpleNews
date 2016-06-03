package com.lauren.simplenews.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.lauren.simplenews.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xxnan on 2016/5/18.
 */
public class RecyclerViewTest extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerAdapter;
    private Context context;
    private List<String> list;
    private ItemTouchHelper mItemTouchHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.recycler);
        mRecyclerView=(RecyclerView)findViewById(R.id.recycler_test);
        initData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerAdapter=new RecyclerAdapter(context,list);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mItemTouchHelper=new ItemTouchHelper(new MyCallback());
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void initData()
    {
        if(list==null)
            list=new ArrayList<String>();
        for(int i='A';i<='z';i++)
            list.add(""+(char)i);
    }
    class MyCallback extends ItemTouchHelper.Callback
    {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFlags,swipeFlags;
            if(recyclerView.getLayoutManager() instanceof GridLayoutManager)
            {
                dragFlags=ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
                swipeFlags=0;
            }else
            {
                dragFlags=ItemTouchHelper.UP|ItemTouchHelper.DOWN;
                swipeFlags=0;
            }
            return makeMovementFlags(dragFlags,swipeFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int fromPosition=viewHolder.getAdapterPosition();
            int toPosition=target.getAdapterPosition();
            if(fromPosition<toPosition)
            for(int i=fromPosition;i<toPosition;i++)
                Collections.swap(list,i,i+1);
            else
                for(int i=fromPosition;i>toPosition;i--)
                    Collections.swap(list,i,i-1);
            mRecyclerAdapter.notifyItemMoved(fromPosition,toPosition);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }
    }
}
