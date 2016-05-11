package com.apmv.chitchat.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.apmv.chitchat.R;
import com.apmv.chitchat.helper.NetUtils;
import com.apmv.chitchat.ui.LoadingDialog;

import cn.pedant.SweetAlert.SweetAlertDialog;

public abstract class BaseActivity extends FragmentActivity {

    private LoadingDialog dialogMessage;
    private SweetAlertDialog pDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetUtils.registerCheckStateInternetConnection(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetUtils.unregisterCheckStateInternetConnection(this);
    }

    public void showLoadingMessage(int strResId) {
        if (isFinishing()) {
            return;
        }
        if (dialogMessage != null) {
            return;
        }
        try {
            dialogMessage = LoadingDialog.newInstance(R.string.processing);
            dialogMessage.show(getFragmentManager(), "LoadingDialog");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismissLoadingMessage() {
        if (dialogMessage == null) {
            return;
        }
        try {
            dialogMessage.dismiss();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } finally {
            dialogMessage = null;
        }
    }

    public void showToastMessage(int strResId) {
        Toast.makeText(BaseActivity.this, getStringResource(strResId), Toast.LENGTH_SHORT).show();
    }

    public void showToastMessage(String message) {
        Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public String getStringResource(int strResId) {
        return getResources().getString(strResId);
    }

    public void showConnectDialog() {
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(getStringResource(R.string.processing));
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public void dismissConnectDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
        }
    }
}
