package com.lauren.simplenews.okhttp;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lauren.simplenews.R;

/**
 * Created by xxnan on 2016/5/26.
 */
public class SnakeBarTest extends AppCompatActivity {
    private CoordinatorLayout layout;
    private FloatingActionButton floatingActionButton;
    Snackbar snackbar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snackbar_test);
        layout=(CoordinatorLayout)findViewById(R.id.layout);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.btnFloatingAction);
        snackbar=Snackbar.make(layout, "snackebar  test", Snackbar.LENGTH_LONG).setAction("clickbutton", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                layout.setText("aleady click snackbar");
            }
        });
        snackbar.getView().setBackgroundColor(0xfff44336);
        snackbar.show();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(layout, "xxnan", Snackbar.LENGTH_LONG).setAction("aaaaa", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                layout.setText("aleady click snackbar");
                    }
                }).show();
            }
        });
    }
}
