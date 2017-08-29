package com.zzh.permissionlibrary.PermissionListener;

import android.content.Context;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

/**
 * ---------------------------
 * <p>
 * Created by zhaozh on 2017/8/28.
 */

public class RequestRationaleListener implements RationaleListener {

    private Context mContext;

    public RequestRationaleListener(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
        // 此对话框可以自定义，调用rationale.resume()就可以继续申请。
        AndPermission.rationaleDialog(mContext, rationale).show();
    }
}
