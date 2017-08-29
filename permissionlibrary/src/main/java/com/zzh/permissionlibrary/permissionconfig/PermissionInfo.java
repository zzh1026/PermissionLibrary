package com.zzh.permissionlibrary.permissionconfig;

import com.yanzhenjie.permission.Permission;
import com.zzh.permissionlibrary.R;
import com.zzh.permissionlibrary.suggestdialog.view.PermissionItem;

import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------
 * <p>
 * Created by zhaozh on 2017/8/28.
 */

public class PermissionInfo {

    private static String[] mPermissionNames = {
            "存储空间", "位置信息", "拍照权限",
            "添加行程", "修改联系人", "麦克风",
            "手机状态", "传感器", "短信修改",
    };

    private static int[] mPermissionIconRes = {
            R.drawable.permission_ic_storage, R.drawable.permission_ic_location, R.drawable.permission_ic_camera,
            R.drawable.permission_ic_calendar, R.drawable.permission_ic_contacts, R.drawable.permission_ic_micro_phone,
            R.drawable.permission_ic_phone, R.drawable.permission_ic_sensors, R.drawable.permission_ic_sms
    };

    private static String[][] mPermissions = {
            Permission.STORAGE, Permission.LOCATION, Permission.CAMERA,
            Permission.CALENDAR, Permission.CONTACTS, Permission.MICROPHONE,
            Permission.PHONE, Permission.SENSORS, Permission.SMS
    };

    public static PermissionItem getPermissionItem(PermissionType type) {
        int res = getPermissionType(type);
        return new PermissionItem(mPermissions[res], mPermissionNames[res], mPermissionIconRes[res]);
    }

    public static String[] getPermissionArrays(List<PermissionType> permissions) {
        List<String> permissionArrays = new ArrayList<>();
        for (PermissionType type : permissions) {
            int permissionType = getPermissionType(type);
            for (int i = 0; i < mPermissions[permissionType].length; i++) {
                permissionArrays.add(mPermissions[permissionType][i]);
            }
        }
        return permissionArrays.toArray(new String[permissionArrays.size()]);
    }

    private static int getPermissionType(PermissionType type) {
        int res;
        switch (type) {
            case PERMISSIONTYPE_LOCATION:
                res = 1;
                break;
            case PERMISSIONTYPE_CAMERA:
                res = 2;
                break;
            case PERMISSIONTYPE_CALENDAR:
                res = 3;
                break;
            case PERMISSIONTYPE_CONTACTS:
                res = 4;
                break;
            case PERMISSIONTYPE_MICROPHONE:
                res = 5;
                break;
            case PERMISSIONTYPE_PHONE:
                res = 6;
                break;
            case PERMISSIONTYPE_SENSORS:
                res = 7;
                break;
            case PERMISSIONTYPE_SMS:
                res = 8;
                break;
            default:
                res = 0;
                break;
        }
        return res;
    }
}
