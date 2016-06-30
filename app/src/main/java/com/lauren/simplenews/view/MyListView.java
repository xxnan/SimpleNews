package com.lauren.simplenews.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.lauren.simplenews.R;

/**
 * Created by xxnan on 2016/6/24.
 */
class MyListView extends ListView implements GestureDetector.OnGestureListener,View.OnTouchListener {
    private View buttonView;
    private GestureDetector gestureDetector;
    private boolean isShown;
    private ViewGroup  itemView;
    private LayoutInflater layoutInflater;
    private int selectedItem;
    private delListener listener;
    private scrollTo scroll;
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        layoutInflater=LayoutInflater.from(context);
        gestureDetector=new GestureDetector(getContext(),this);
        setOnTouchListener(this);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        if (!isShown) {
            selectedItem = pointToPosition((int) e.getX(), (int) e.getY());
        }
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (!isShown && Math.abs(velocityX) > Math.abs(velocityY)) {
            buttonView =layoutInflater.inflate(
                    R.layout.view_delbtn, null);
            buttonView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.removeView(buttonView);
                    buttonView = null;
                    isShown = false;
                    listener.del(selectedItem);
                    scroll.scroll(selectedItem,00,0);
                }
            });
            itemView = (ViewGroup) getChildAt(selectedItem
                    - getFirstVisiblePosition());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            scroll.scroll(selectedItem,100,0);
            itemView.addView(buttonView, params);
            isShown = true;

        }
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(isShown) {
            itemView.removeView(buttonView);
            buttonView = null;
            isShown=false;
            return false;
        }else
            return gestureDetector.onTouchEvent(event);
    }
    public void setDelListener(delListener mListener)
    {
        listener=mListener;
    }
    public void setscrollListener(scrollTo mListener)
    {
        scroll=mListener;
    }

  public  interface delListener{
        public void del(int pos);
    }
    public  interface scrollTo{
        public void scroll(int pos,int x,int y);
    }
}
