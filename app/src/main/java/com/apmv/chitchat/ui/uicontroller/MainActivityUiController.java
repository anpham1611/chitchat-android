package com.apmv.chitchat.ui.uicontroller;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.apmv.chitchat.R;
import com.apmv.chitchat.ui.activity.MainActivity;
import com.apmv.chitchat.ui.widget.MessageFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by an.pham1611 on 2/23/16.
 */
public class MainActivityUiController implements View.OnClickListener {
    private String TAG = MainActivityUiController.class.getSimpleName();
    private MainActivity activity;
    MessageFragment fgMessageContent;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    public MainActivityUiController(MainActivity activity) {
        this.activity = activity;
        ButterKnife.bind(this, activity);
        init();
    }

    private void init() {
        initToolbar();
        fgMessageContent = (MessageFragment) activity.getSupportFragmentManager().findFragmentById(R.id.fgMessageContent);
    }

    private void initToolbar() {
        toolbar.setTitle(activity.getStringResource(R.string.message_welcome));
        toolbar.setNavigationIcon(R.drawable.ic_white_back);
        toolbar.setNavigationOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                fgMessageContent.leave();
                break;
        }
    }
}
