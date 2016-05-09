package com.apmv.chitchat.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apmv.chitchat.R;
import com.apmv.chitchat.helper.Constants;
import com.apmv.chitchat.helper.SharedPreferenceUtils;
import com.apmv.chitchat.helper.Utils;
import com.apmv.chitchat.ui.widget.Message;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<Message> mMessages;
    private int[] mUsernameColors;
    private Message mLastMessage;

    public MessageAdapter(Context context, List<Message> messages) {
        mMessages = messages;
        mUsernameColors = context.getResources().getIntArray(R.array.username_colors);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = -1;
        switch (viewType) {
        case Message.TYPE_MESSAGE:
            layout = R.layout.item_message;
            break;
        case Message.TYPE_MESSAGE_ME:
            layout = R.layout.item_message_me;
            break;
        case Message.TYPE_LOG:
            layout = R.layout.item_log;
            break;
        case Message.TYPE_ACTION:
            layout = R.layout.item_action;
            break;
        }
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Message message = mMessages.get(position);

        // Username
        int type = message.getType();
        if (Message.TYPE_ACTION == type) {
            viewHolder.setUsername(message.getUsername());

        } else if (Message.TYPE_MESSAGE == type || Message.TYPE_MESSAGE_ME == type) {
            boolean canShowName = true;
            if (mLastMessage != null
                    && mLastMessage.getUsername() != null
                    && message.getUsername() != null
                    && mLastMessage.getUsername().equals(message.getUsername())) {
                canShowName = false;
            }
            if (canShowName && message.getUsername() != null) {
                if (viewHolder.mAvatarView != null)
                    viewHolder.mAvatarView.setVisibility(View.VISIBLE);
                if (viewHolder.mUsernameView != null)
                    viewHolder.mUsernameView.setVisibility(View.VISIBLE);
                viewHolder.setUsername(message.getUsername());
                viewHolder.setAvatar(message.getUsername().substring(0, 1));
            } else {
                if (viewHolder.mAvatarView != null)
                    viewHolder.mAvatarView.setVisibility(View.INVISIBLE);
                if (viewHolder.mUsernameView != null)
                    viewHolder.mUsernameView.setVisibility(View.GONE);
            }
        }

        // Message
        if (message.getMessage() != null)
            viewHolder.setMessage(message.getMessage());

        // Time
        boolean canShowTime = true;
        String time = null;
        if (canShowTime && message.getTime() != null)
            time = Utils.showTime(message.getTime());
        if (mLastMessage != null
                && mLastMessage.getTime() != null
                && time != null) {
            if (time != null) {
                String timeLast = Utils.showTime(mLastMessage.getTime());
                if (time.equals(timeLast)) {
                    canShowTime = false;
                }
            }
        }
        if (canShowTime && time != null)
            viewHolder.setTime(time);

        // Save last message
        if (message.getType() == Message.TYPE_MESSAGE || message.getType() == Message.TYPE_MESSAGE_ME)
            mLastMessage = message;
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mMessages.get(position).getType();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mAvatarView;
        public TextView mUsernameView;
        public TextView mMessageView;
        public TextView mTime;

        public ViewHolder(View itemView) {
            super(itemView);
            mAvatarView = (TextView) itemView.findViewById(R.id.username_avatar);
            mUsernameView = (TextView) itemView.findViewById(R.id.username);
            mMessageView = (TextView) itemView.findViewById(R.id.message);
            mTime = (TextView) itemView.findViewById(R.id.time);
        }

        public void setAvatar(String shortName) {
            if (null == mAvatarView) return;
            mAvatarView.setText(shortName);
            mAvatarView.setTextColor(getUsernameColor(shortName));
        }

        public void setUsername(String username) {
            if (null == mUsernameView) return;
            mUsernameView.setText(username);
            // mUsernameView.setTextColor(getUsernameColor(username));
        }

        public void setMessage(String message) {
            if (null == mMessageView) return;
            mMessageView.setText(message);
        }

        public void setTime(String time) {
            if (null == mTime) return;
            mTime.setText(time);
        }

        private int getUsernameColor(String username) {
            int hash = 7;
            for (int i = 0, len = username.length(); i < len; i++) {
                hash = username.codePointAt(i) + (hash << 5) - hash;
            }
            int index = Math.abs(hash % mUsernameColors.length);
            return mUsernameColors[index];
        }
    }
}
