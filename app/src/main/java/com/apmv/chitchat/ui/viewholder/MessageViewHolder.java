package com.apmv.chitchat.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.apmv.chitchat.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by an.pham1611 on 5/11/16.
 */
public class MessageViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.username_avatar)
    TextView mAvatarView;
    @Bind(R.id.username)
    TextView mUsernameView;
    @Bind(R.id.message)
    TextView mMessageView;
    @Bind(R.id.time)
    TextView mTime;
    @Bind(R.id.stable_bottom_line)
    View mStableBottomLine;

    private int[] mUsernameColors;

    public MessageViewHolder(View itemView, int[] usernameColors) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mUsernameColors = usernameColors;
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

    public View getStableBottomLine() {
        return mStableBottomLine;
    }

    public TextView getAvatarView() {
        return mAvatarView;
    }

    public TextView getUsernameView() {
        return mUsernameView;
    }
}
