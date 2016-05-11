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
public class LogViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.message)
    TextView mMessageView;

    public LogViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setMessage(String message) {
        if (null == mMessageView) return;
        mMessageView.setText(message);
    }
}
