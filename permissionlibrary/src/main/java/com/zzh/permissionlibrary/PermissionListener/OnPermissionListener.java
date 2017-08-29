package com.zzh.permissionlibrary.PermissionListener;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * ---------------------------
 * <p>
 * Created by zhaozh on 2017/8/28.
 */

public interface OnPermissionListener {

    void onSucceed(int requestCode, @NonNull List<String> grantPermissions);

    void onFailed(int requestCode, @NonNull List<String> deniedPermissions);

}
