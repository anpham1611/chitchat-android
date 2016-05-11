package com.apmv.chitchat.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apmv.chitchat.R;
import com.apmv.chitchat.helper.Utils;
import com.apmv.chitchat.ui.viewholder.ActionViewHolder;
import com.apmv.chitchat.ui.viewholder.LogViewHolder;
import com.apmv.chitchat.ui.viewholder.MessageMeViewHolder;
import com.apmv.chitchat.ui.viewholder.MessageViewHolder;
import com.apmv.chitchat.ui.widget.Message;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Message> mMessages;
    private Message mLastMessage;
    private int[] mUsernameColors;

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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType) {
            case Message.TYPE_MESSAGE:
                v = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_message, parent, false);
                return new MessageViewHolder(v, mUsernameColors);
            case Message.TYPE_MESSAGE_ME:
                v = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_message_me, parent, false);
                return new MessageMeViewHolder(v);
            case Message.TYPE_LOG:
                v = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_log, parent, false);
                return new LogViewHolder(v);
            case Message.TYPE_ACTION:
                v = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_action, parent, false);
                return new ActionViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Message message = mMessages.get(position);
        int viewType = viewHolder.getItemViewType();
        switch (viewType) {
            case Message.TYPE_MESSAGE:
                bindMessageView(viewHolder, message);
                break;
            case Message.TYPE_MESSAGE_ME:
                bindMessageMeView(viewHolder, message);
                break;
            case Message.TYPE_LOG:
                bindLogView(viewHolder, message);
                break;
            case Message.TYPE_ACTION:
                bindActionView(viewHolder, message);
                break;
        }
        // Save last message
        if (message.getType() == Message.TYPE_MESSAGE || message.getType() == Message.TYPE_MESSAGE_ME)
            mLastMessage = message;
    }

    private void bindMessageView(RecyclerView.ViewHolder viewHolder, Message message) {
        MessageViewHolder holder = (MessageViewHolder) viewHolder;

        // Name & Avatar & Space
        boolean canShowName = true;
        if (mLastMessage != null
                && mLastMessage.getUsername() != null
                && message.getUsername() != null
                && mLastMessage.getUsername().equals(message.getUsername())) {
            canShowName = false;
        }
        if (canShowName && message.getUsername() != null) {
            holder.getStableBottomLine().setVisibility(View.VISIBLE);
            holder.getAvatarView().setVisibility(View.VISIBLE);
            holder.getUsernameView().setVisibility(View.VISIBLE);

            String userName = message.getUsername();
            holder.setUsername(userName);
            String[] nameArr = userName.split(" ");
            String name;
            if (nameArr.length > 1) {
                name = nameArr[0].substring(0, 1);
                name += nameArr[1].substring(0, 1);
            } else {
                name = nameArr[0].substring(0, 1);
            }
            holder.setAvatar(name);
        } else {
            holder.getStableBottomLine().setVisibility(View.GONE);
            holder.getAvatarView().setVisibility(View.GONE);
            holder.getUsernameView().setVisibility(View.GONE);
        }

        // Message
        holder.setMessage(message.getMessage());

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
            holder.setTime(time);
    }

    private void bindMessageMeView(RecyclerView.ViewHolder viewHolder, Message message) {
        MessageMeViewHolder holder = (MessageMeViewHolder) viewHolder;

        // Space
        boolean canShowName = true;
        if (mLastMessage != null
                && mLastMessage.getUsername() != null
                && message.getUsername() != null
                && mLastMessage.getUsername().equals(message.getUsername())) {
            canShowName = false;
        }
        if (canShowName && message.getUsername() != null) {
            holder.getStableBottomLine().setVisibility(View.VISIBLE);
        } else {
            holder.getStableBottomLine().setVisibility(View.GONE);
        }

        // Message
        holder.setMessage(message.getMessage());

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
            holder.setTime(time);
    }

    private void bindActionView(RecyclerView.ViewHolder viewHolder, Message message) {
        ActionViewHolder holder = (ActionViewHolder) viewHolder;
        holder.setUsername(message.getUsername());
    }

    private void bindLogView(RecyclerView.ViewHolder viewHolder, Message message) {
        LogViewHolder holder = (LogViewHolder) viewHolder;
        holder.setMessage(message.getMessage());
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mMessages.get(position).getType();
    }

}
