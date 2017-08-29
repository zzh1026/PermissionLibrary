package com.zzh.permissionlibrary.settingdialog;

import android.content.Context;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.SettingService;

/**
 * ---------------------------
 * <p>
 * Created by zhaozh on 2017/8/29.
 * <p>
 * 自定义的 没权限提示配置的 dailog 管理
 */

public class SettingDialog implements SettingEventCallback {
    private SettingService settingService;

    public SettingDialog(Context mContext) {
        settingService = AndPermission.defineSettingDialog(mContext);
    }

    @Override
    public void execute() {
        settingService.execute();
    }

    @Override
    public void cancel() {
        settingService.cancel();
    }
}
