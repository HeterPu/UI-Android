package com.heterpu.ui.basedialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.heterpu.ui.basedialog.interfaces.Showable;

import java.lang.ref.WeakReference;

public abstract class BaseDialog implements Showable {
    protected Dialog mAlertDialogue;
    protected WeakReference<Context> mContext;
    private OnDismissListener onDismissListener;

    public BaseDialog(Context ctx) {
        mContext = new WeakReference<>(ctx);
        onCreate();
    }

    protected void onCreate(){
        mAlertDialogue = new Dialog(getContext());
        Window window = mAlertDialogue.getWindow();
        mAlertDialogue.requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = getDesireDialogSize().getWidth();
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        mAlertDialogue.setContentView(getLayoutView(getContext()));
        mAlertDialogue.setCanceledOnTouchOutside(clickBackgroundDismiss());
        mAlertDialogue.setCancelable(allowBackPress());
        mAlertDialogue.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    protected View getLayoutView(Context ctx) {
        return onCreateContentView(ctx);
    }

    abstract public View onCreateContentView(Context ctx);

    abstract protected Size getDesireDialogSize();

    protected boolean clickBackgroundDismiss() {
        return false;
    }

    protected boolean allowBackPress() {
        return false;
    }


    public void setOnDismissListener(OnDismissListener dismssListener) {
        this.onDismissListener = dismssListener;
        mAlertDialogue.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                onDismissListener.onDismiss();
            }
        });
    }


    public void dismiss() {
        if (mAlertDialogue != null) {
            mAlertDialogue.dismiss();
        }
    }


    public void show() {
        if (mAlertDialogue != null) {
            if (!mAlertDialogue.isShowing()) mAlertDialogue.show();
        }
    }


    protected Context getContext() {
        if (mContext != null) {
            return mContext.get();
        }
        return null;
    }


    // ==== Interface ====


    public interface OnDismissListener {

        /**
         * View dismiss callback
         */
        void onDismiss();
    }


    // ==== INNER CLASSES ====

    public static final class Size {

        public Size(int width, int height) {
            mWidth = width;
            mHeight = height;
        }

        public int getWidth() {
            return mWidth;
        }

        public int getHeight() {
            return mHeight;
        }

        private final int mWidth;
        private final int mHeight;
    }
}
