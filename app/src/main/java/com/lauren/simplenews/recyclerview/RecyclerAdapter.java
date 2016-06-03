package com.lauren.simplenews.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lauren.simplenews.R;

import java.util.List;

/**
 * Created by xxnan on 2016/5/18.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {
    private Context mContext;
    private List<String> mList;
    public RecyclerAdapter(Context context,List list)
    {
        mContext=context;
        mList=list;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder myHolder= new MyHolder(LayoutInflater.from(mContext).inflate(R.layout.recycler_item,parent,false));
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.tv.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    class  MyHolder extends RecyclerView.ViewHolder
    {
        public TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_num);
        }
    }
}
