package com.lauren.simplenews.SwipeRefreshLayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lauren.simplenews.R;

import java.util.List;

/**
 * Created by xxnan on 2016/8/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    private Context mContext;
    private List<String>mList;
    public MyAdapter(Context context, List<String> list)
    {
        mList=list;
        mContext=context;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ecycler_item,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.item.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

class  MyHolder extends  RecyclerView.ViewHolder
{
    public TextView item;
    public MyHolder(View itemView) {
        super(itemView);
        item= (TextView) itemView.findViewById(R.id.rec_item);
    }
}
