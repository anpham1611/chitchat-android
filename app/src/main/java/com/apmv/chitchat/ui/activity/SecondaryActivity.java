package com.apmv.chitchat.ui.activity;

import android.view.WindowManager;

import com.apmv.chitchat.R;

public abstract class SecondaryActivity extends BaseActivity {
    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
        );
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.sub_activity_open, R.anim.sub_activity_close);
    }
}
