package com.zzh.permissionlibrary.suggestdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

import com.zzh.permissionlibrary.PermissionListener.OnPermissionListener;
import com.zzh.permissionlibrary.PermissionManager;
import com.zzh.permissionlibrary.R;
import com.zzh.permissionlibrary.suggestdialog.view.PermissionAdapter;
import com.zzh.permissionlibrary.suggestdialog.view.PermissionItem;
import com.zzh.permissionlibrary.suggestdialog.view.SuggestDoalogView;

import java.util.List;

/**
 * ---------------------------
 * <p>
 * Created by zhaozh on 2017/8/28.
 * <p>
 * 建议的dialog
 */

public class SuggestDialog {
    private Context mContext;
    private PermissionManager permissionManager;

    private List<PermissionItem> mCheckPermissions;
    private String mTitle;
    private String mMsg;
    private int mAnimStyleId;
    private int mStyleId;
    private int mFilterColor;
    private int mRequestCode;
    private OnPermissionListener listener;

    private Dialog mDialog;
    private SuggestDoalogView contentView;

    private SuggestDialog(Builder builder) {
        this.mContext = builder.mContext;
        this.permissionManager = builder.permissionManager;
        this.mRequestCode = builder.requestCode;
        this.mCheckPermissions = builder.mCheckPermissions;
        this.mTitle = builder.mTitle;
        this.mMsg = builder.mMsg;
        this.mAnimStyleId = builder.mAnimStyleId;
        this.mStyleId = builder.mStyleId;
        this.mFilterColor = builder.mFilterColor;
        this.listener = builder.listener;
        initView();
        initDialog();
    }

    private void initView() {
        contentView = new SuggestDoalogView(mContext);
        contentView.setGridViewColum(mCheckPermissions.size() < 3 ? mCheckPermissions.size() : 3);
        contentView.setTitle(mTitle);
        contentView.setMsg(mMsg);
        contentView.setGridViewAdapter(new PermissionAdapter(mCheckPermissions));
        contentView.setStyleId(mStyleId);

        if (mFilterColor != 0) {
            contentView.setFilterColor(mFilterColor);
        }

        contentView.setBtnOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog != null && mDialog.isShowing())
                    mDialog.dismiss();
                String[] strs = getPermissionStrArray();
                permissionManager.requestPermission(mContext, mRequestCode, strs, listener);
            }
        });
    }

    private void initDialog() {
        mDialog = new Dialog(mContext);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(contentView);
        if (mAnimStyleId != -1)
            mDialog.getWindow().setWindowAnimations(mAnimStyleId);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
    }

    private String[] getPermissionStrArray() {
        int strLength = 0;
        for (int i = 0; i < mCheckPermissions.size(); i++) {
            strLength += mCheckPermissions.get(i).Permission.length;
        }
        String[] str = new String[strLength];
        int index = 0;
        for (int i = 0; i < mCheckPermissions.size(); i++) {
            for (int y = 0; y < mCheckPermissions.get(i).Permission.length; y++) {
                str[index] = mCheckPermissions.get(i).Permission[y];
                index++;
            }
        }
        return str;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmMsg() {
        return mMsg;
    }

    public int getmAnimStyleId() {
        return mAnimStyleId;
    }

    public int getmStyleId() {
        return mStyleId;
    }

    public int getmFilterColor() {
        return mFilterColor;
    }

    public static class Builder {
        private Context mContext;
        private PermissionManager permissionManager;
        private List<PermissionItem> mCheckPermissions;

        private String mTitle = "权限请求";
        private String mMsg = "油分期需要以下权限来进行更好的服务";
        private int mAnimStyleId = -1;
        private int mStyleId = R.style.PermissionDefaultNormalStyle;
        private int mFilterColor = 0;
        private int requestCode;
        private OnPermissionListener listener;

        public Builder(Context mContext, PermissionManager permissionManager, List<PermissionItem> mCheckPermissions) {
            this.mContext = mContext;
            this.permissionManager = permissionManager;
            this.mCheckPermissions = mCheckPermissions;
        }

        public Builder title(String mTitle) {
            this.mTitle = mTitle;
            return this;
        }

        public Builder msg(String mMsg) {
            this.mMsg = mMsg;
            return this;
        }

        public Builder animStyleId(int mAnimStyleId) {
            this.mAnimStyleId = mAnimStyleId;
            return this;
        }

        public Builder styleId(int mStyleId) {
            this.mStyleId = mStyleId;
            return this;
        }

        public Builder filterColor(int mFilterColor) {
            this.mFilterColor = mFilterColor;
            return this;
        }

        public Builder permissionListener(OnPermissionListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder requestCode(int requestCode) {
            this.requestCode = requestCode;
            return this;
        }

        public SuggestDialog build() {
            return new SuggestDialog(this);
        }
    }

    /**
     * 展示 该 建议dialog
     */
    public void show() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }
}
