package com.zzh.permissionlibrary;

import android.app.Activity;
import android.content.Context;

import com.yanzhenjie.permission.AndPermission;
import com.zzh.permissionlibrary.PermissionListener.OnPermissionListener;
import com.zzh.permissionlibrary.PermissionListener.RequestListener;
import com.zzh.permissionlibrary.PermissionListener.RequestRationaleListener;
import com.zzh.permissionlibrary.permissionconfig.Common;
import com.zzh.permissionlibrary.permissionconfig.PermissionInfo;
import com.zzh.permissionlibrary.permissionconfig.PermissionType;
import com.zzh.permissionlibrary.settingdialog.SettingDialog;
import com.zzh.permissionlibrary.suggestdialog.SuggestDialog;
import com.zzh.permissionlibrary.suggestdialog.view.PermissionItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.yanzhenjie.permission.AndPermission.hasPermission;

/**
 * ---------------------------
 * <p>
 * Created by zhaozh on 2017/8/25.
 * <p>
 * Permission的管理类
 */

public class PermissionManager {
    private final Context mContext;
    private static final int DEFAULT_SETTING_DIALOG_REQUESTCODE = 0;

    private PermissionManager(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 获取创建 一个 permissionManager 的实例对象
     *
     * @param mContext
     * @return PermissionManager
     */
    public static PermissionManager create(Context mContext) {
        return new PermissionManager(mContext);
    }

    /**
     * 获取默认的权限 , 会使用建议弹窗
     *
     * @return
     */
    public SuggestDialog requestNormalPermission(OnPermissionListener listener) {
        return new SuggestDialog.Builder(mContext, this, getNormalPermissions())
                .animStyleId(R.style.PermissionAnimModal)
                .permissionListener(listener)
                .requestCode(Common.REQUESTCODE_NORMAL)
                .build();
    }

    /**
     * 获取某个权限或者某个权限组 , 会使用建议弹窗
     *
     * @return
     */
    public SuggestDialog.Builder requestPermission(int requestCode, PermissionType... permissionTypes) {
        return new SuggestDialog.Builder(mContext, this, getPermissions(permissionTypes))
                .requestCode(requestCode)
                .animStyleId(R.style.PermissionAnimModal);
    }

    /**
     * 获取权限不用弹出建议 , 不适用弹窗
     *
     * @param listener
     * @param permissionTypes
     */
    public void requestPermission(OnPermissionListener listener, int requestCode, PermissionType... permissionTypes) {
        requestPermission(mContext, requestCode, getPermissionArrays(permissionTypes), listener);
    }

    /**
     * 获取某个或某几个权限,不使用 弹窗
     *
     * @param listener
     * @param permissions
     */
    public void requestPermission(OnPermissionListener listener, int requestCode, String... permissions) {
        requestPermission(mContext, requestCode, permissions, listener);
    }

    private List<PermissionItem> getNormalPermissions() {
        return getPermissions(PermissionType.PERMISSIONTYPE_STORAGE,
                PermissionType.PERMISSIONTYPE_LOCATION, PermissionType.PERMISSIONTYPE_CAMERA);
    }

    private static String[] getPermissionArrays(PermissionType... permissionTypes) {
        List<PermissionType> permissions = Arrays.asList(permissionTypes);
        return PermissionInfo.getPermissionArrays(permissions);
    }

    private List<PermissionItem> getPermissions(PermissionType... permissions) {
        List<PermissionType> permissionTypes = Arrays.asList(permissions);
        List<PermissionItem> lists = new ArrayList<>();
        for (PermissionType type : permissionTypes) {
            lists.add(PermissionInfo.getPermissionItem(type));
        }
        return lists;
    }

    /**
     * 请求permission 最终执行的结果
     *
     * @param mContext
     * @param permissionArrays 要请求的权限列表
     */
    public void requestPermission(Context mContext, int requestCode, String[] permissionArrays, OnPermissionListener listener) {
        AndPermission.with(mContext)
                .requestCode(requestCode)
                .permission(permissionArrays)
                .rationale(new RequestRationaleListener(mContext))
                .callback(new RequestListener(mContext, listener))
                .start();
    }

    /**
     * 展示默认的dialog
     *
     * @param mContext
     */
    public static void showSettingDialog(Context mContext) {
        AndPermission.defaultSettingDialog(mContext).show();
    }

    /**
     * 展示 自定义信息的 dialog
     *
     * @param mActivity
     * @param title
     * @param message
     * @param positiveButtonText
     */
    public static void showSettingDialog(Activity mActivity, String title, String message, String positiveButtonText) {
        AndPermission.defaultSettingDialog(mActivity, DEFAULT_SETTING_DIALOG_REQUESTCODE)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText)
                .show();
    }

    /**
     * 自己自定义dialog
     *
     * @param mContext
     * @return
     */
    public static SettingDialog showCustomSettingDialog(Context mContext) {
        return new SettingDialog(mContext);
    }

    /**
     * 判断权限是否存在
     *
     * @param mContext
     * @param permissionTypes
     * @return
     */
    public static boolean hasPermissions(Context mContext, PermissionType... permissionTypes) {
        return hasPermissions(mContext, getPermissionArrays(permissionTypes));
    }

    public static boolean hasPermissions(Context mContext, String... permissions) {
        return hasPermissions(mContext, Arrays.asList(permissions));
    }

    public static boolean hasPermissions(Context mContext, List<String> permissions) {
        return AndPermission.hasPermission(mContext, permissions);
    }
}

