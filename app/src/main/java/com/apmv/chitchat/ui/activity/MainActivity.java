package com.apmv.chitchat.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.apmv.chitchat.R;
import com.apmv.chitchat.ui.uicontroller.MainActivityUiController;

public class MainActivity extends PrimaryActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private MainActivityUiController uiController;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        uiController = new MainActivityUiController(this);

        init();
    }

    private void init() {
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
