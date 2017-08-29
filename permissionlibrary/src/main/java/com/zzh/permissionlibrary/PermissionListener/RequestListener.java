package com.zzh.permissionlibrary.PermissionListener;

import android.content.Context;
import android.support.annotation.NonNull;

import com.yanzhenjie.permission.PermissionListener;
import com.zzh.permissionlibrary.PermissionManager;

import java.util.List;

/**
 * ---------------------------
 * <p>
 * Created by zhaozh on 2017/8/28.
 */

public class RequestListener implements PermissionListener {
    private Context mContext;
    private OnPermissionListener listener;

    public RequestListener(Context mContext, OnPermissionListener listener) {
        this.listener = listener;
        this.mContext = mContext;
    }

    @Override
    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
        if (listener == null) return;
//        if (PermissionManager.hasPermissions(mContext, grantPermissions)) {
        listener.onSucceed(requestCode, grantPermissions);
//        } else {
//            listener.onFailed(requestCode, grantPermissions);
//        }
    }

    @Override
    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
        if (listener == null) return;
        if (PermissionManager.hasPermissions(mContext, deniedPermissions)) {
            listener.onSucceed(requestCode, deniedPermissions);
        } else {
            listener.onFailed(requestCode, deniedPermissions);
        }
    }
}
