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
public class MessageMeViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.message)
    TextView mMessageView;
    @Bind(R.id.time)
    TextView mTime;
    @Bind(R.id.stable_bottom_line)
    View mStableBottomLine;

    public MessageMeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setMessage(String message) {
        if (null == mMessageView) return;
        mMessageView.setText(message);
    }

    public void setTime(String time) {
        if (null == mTime) return;
        mTime.setText(time);
    }

    public View getStableBottomLine() {
        return mStableBottomLine;
    }

}
