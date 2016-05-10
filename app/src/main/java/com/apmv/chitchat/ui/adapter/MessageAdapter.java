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
        mUsernameColors = new int[12];
        mUsernameColors[0] = R.drawable.bg_avatar_0;
        mUsernameColors[1] = R.drawable.bg_avatar_1;
        mUsernameColors[2] = R.drawable.bg_avatar_2;
        mUsernameColors[3] = R.drawable.bg_avatar_3;
        mUsernameColors[4] = R.drawable.bg_avatar_4;
        mUsernameColors[5] = R.drawable.bg_avatar_5;
        mUsernameColors[6] = R.drawable.bg_avatar_6;
        mUsernameColors[7] = R.drawable.bg_avatar_7;
        mUsernameColors[8] = R.drawable.bg_avatar_8;
        mUsernameColors[9] = R.drawable.bg_avatar_9;
        mUsernameColors[10] = R.drawable.bg_avatar_10;
        mUsernameColors[11] = R.drawable.bg_avatar_11;
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
                viewHolder.mStableBottomLine.setVisibility(View.VISIBLE);
                if (viewHolder.mAvatarView != null)
                    viewHolder.mAvatarView.setVisibility(View.VISIBLE);
                if (viewHolder.mUsernameView != null)
                    viewHolder.mUsernameView.setVisibility(View.VISIBLE);

                String userName = message.getUsername();
                viewHolder.setUsername(userName);
                String[] nameArr = userName.split(" ");
                String name;
                if (nameArr.length > 1) {
                    name = nameArr[0].substring(0, 1);
                    name += nameArr[1].substring(0, 1);
                } else {
                    name = nameArr[0].substring(0, 1);
                }
                viewHolder.setAvatar(name);
            } else {
                viewHolder.mStableBottomLine.setVisibility(View.GONE);
                if (viewHolder.mAvatarView != null)
                    viewHolder.mAvatarView.setVisibility(View.GONE);
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
                && time != null
                && mLastMessage.getUsername().equals(message.getUsername())) {
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
        public View mStableBottomLine;

        public ViewHolder(View itemView) {
            super(itemView);
            mAvatarView = (TextView) itemView.findViewById(R.id.username_avatar);
            mUsernameView = (TextView) itemView.findViewById(R.id.username);
            mMessageView = (TextView) itemView.findViewById(R.id.message);
            mTime = (TextView) itemView.findViewById(R.id.time);
            mStableBottomLine = (View) itemView.findViewById(R.id.stable_bottom_line);
        }

        public void setAvatar(String shortName) {
            if (null == mAvatarView) return;
            mAvatarView.setText(shortName);
            mAvatarView.setBackgroundResource(getUsernameColor(shortName));
        }

        public void setUsername(String username) {
            if (null == mUsernameView) return;
            mUsernameView.setText(username);
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
