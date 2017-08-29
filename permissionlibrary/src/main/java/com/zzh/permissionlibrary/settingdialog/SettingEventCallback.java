package com.zzh.permissionlibrary.settingdialog;

/**
 * ---------------------------
 * <p>
 * Created by zhaozh on 2017/8/29.
 * <p>
 * 对自定义 设置 dialog 的事件监听
 */

public interface SettingEventCallback {

    /**
     * dialog 点击了确定调用
     */
    void execute();

    /**
     * dialog 点击了取消调用
     */
    void cancel();
}
