package com.apmv.chitchat.ui.uicontroller;

import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.apmv.chitchat.R;
import com.apmv.chitchat.ui.activity.SignInActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by an.pham1611 on 2/23/16.
 */
public class SignInActivityUiController implements View.OnClickListener, TextView.OnEditorActionListener {
    SignInActivity activity;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.username_input)
    EditText usernameView;
    @Bind(R.id.sign_in_button)
    Button signInButton;

    public SignInActivityUiController(SignInActivity activity) {
        this.activity = activity;
        ButterKnife.bind(this, activity);
        init();
    }

    private void init() {
        initToolbar();
        usernameView.setOnEditorActionListener(this);
        signInButton.setOnClickListener(this);
    }

    private void initToolbar() {
        toolbar.setTitle(activity.getStringResource(R.string.title_activity_login));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                activity.attemptLogin(usernameView);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == R.id.login || actionId == EditorInfo.IME_NULL) {
            activity.attemptLogin(v);
            return true;
        }
        return false;
    }
}
