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
public class ActionViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.username)
    TextView mUsernameView;

    public ActionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setUsername(String username) {
        if (null == mUsernameView) return;
        mUsernameView.setText(username);
    }

}
