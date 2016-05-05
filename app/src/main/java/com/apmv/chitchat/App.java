package com.apmv.chitchat;

import android.app.Application;

import com.apmv.chitchat.helper.Constants;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by an.pham1611 on 2/23/16.
 */
public class App extends Application {

    private static App app;
    private Socket mSocket;

    @Override
    public void onCreate() {
        super.onCreate();
        this.app = this;
        FlowManager.init(this);

        try {
            mSocket = IO.socket(Constants.CHAT_SERVER_URL);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static App getInstance() {
        return app;
    }

    public Socket getSocket() {
        return mSocket;
    }
}
