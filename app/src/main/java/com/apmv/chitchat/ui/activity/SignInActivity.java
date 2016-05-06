package com.apmv.chitchat.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.apmv.chitchat.App;
import com.apmv.chitchat.R;
import com.apmv.chitchat.ui.uicontroller.SignInActivityUiController;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SignInActivity extends SecondaryActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private SignInActivityUiController uiController;
    private Context mContext;
    private String mUsername;
    private Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uiController = new SignInActivityUiController(this);
        mContext = this;

        App app = (App) getApplication();
        mSocket = app.getSocket();
        mSocket.on("login", onLogin);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSocket.off("login", onLogin);
    }

    /**
     * Attempts to sign in the account specified by the login form.
     * If there are form errors (invalid username, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin(TextView v) {
        // Reset errors.
        v.setError(null);

        // Store values at the time of the login attempt.
        String username = v.getText().toString().trim();

        // Check for a valid username.
        if (TextUtils.isEmpty(username)) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            v.setError(getString(R.string.error_field_required));
            v.requestFocus();
            return;
        }

        mUsername = username;

        // perform the user login attempt.
        mSocket.emit("add user", username);
    }

    private Emitter.Listener onLogin = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject data = (JSONObject) args[0];

            int numUsers;
            try {
                numUsers = data.getInt("numUsers");
            } catch (JSONException e) {
                return;
            }

            Intent intent = new Intent();
            intent.putExtra("username", mUsername);
            intent.putExtra("numUsers", numUsers);
            setResult(RESULT_OK, intent);
            finish();
        }
    };

}
