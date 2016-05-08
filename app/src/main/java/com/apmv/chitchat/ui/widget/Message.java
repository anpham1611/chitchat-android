package com.apmv.chitchat.ui.widget;

public class Message {

    public static final int TYPE_MESSAGE = 0;
    public static final int TYPE_MESSAGE_ME = 1;
    public static final int TYPE_LOG = 2;
    public static final int TYPE_ACTION = 3;

    private int mType;
    private String mMessage;
    private String mUsername;
    private String mTime;

    private Message() {}

    public int getType() {
        return mType;
    };

    public String getMessage() {
        return mMessage;
    };

    public String getUsername() {
        return mUsername;
    };

    public String getTime() {
        return mTime;
    }

    public static class Builder {
        private final int mType;
        private String mUsername;
        private String mMessage;
        private String mTime;

        public Builder(int type) {
            mType = type;
        }

        public Builder username(String username) {
            mUsername = username;
            return this;
        }

        public Builder message(String message) {
            mMessage = message;
            return this;
        }

        public Builder time(String time) {
            mTime = time;
            return this;
        }

        public Message build() {
            Message message = new Message();
            message.mType = mType;
            message.mUsername = mUsername;
            message.mMessage = mMessage;
            message.mTime = mTime;
            return message;
        }
    }
}
