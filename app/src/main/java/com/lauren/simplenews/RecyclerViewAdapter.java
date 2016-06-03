package com.lauren.simplenews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by xxnan on 2016/5/19.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<MyHolder>
{
    private String [] mTitle;
    private Context mContext;
    private RecycleOnItemClick recycleOnItemClick;
    public interface RecycleOnItemClick
    {
        void onItemClick(int position);
    }
    public void setListener(RecycleOnItemClick mRecycleOnItemClick)
    {
        recycleOnItemClick=mRecycleOnItemClick;
    }
    public RecyclerViewAdapter(Context context,String[] title)
    {
        mTitle=title;
        mContext=context;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.main_list_item,null);

        final MyHolder myHolder=new MyHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recycleOnItemClick.onItemClick(myHolder.getAdapterPosition());
            }
        });
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.tv.setText(mTitle[position]);
    }

    @Override
    public int getItemCount() {
        return mTitle.length;
    }
}
 class MyHolder extends RecyclerView.ViewHolder
{
    TextView tv;
    public MyHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.item_view_main_list);
    }


}

