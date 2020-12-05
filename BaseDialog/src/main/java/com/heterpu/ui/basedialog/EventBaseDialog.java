package com.heterpu.ui.basedialog;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;

public abstract class  EventBaseDialog extends BaseDialog {

    protected ItemClickListener mItemClick;

    public EventBaseDialog(Context ctx,ItemClickListener itemClickListener) {
        super(ctx);
        mItemClick = itemClickListener;
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                mItemClick.viewDismiss();
            }
        });
        configuration();
    }

    protected void configuration(){

    }


    @Override
    public View onCreateContentView(Context ctx) {
        return createViewById(ctx, getMainLayoutId());
    }


    private View  createViewById(Context ctx,int resId){
        return  View.inflate(ctx, resId, null);
    }


    protected int getOrientation(){
        return getContext() == null ? Configuration.ORIENTATION_PORTRAIT : getContext().getResources().getConfiguration().orientation;
    }


    abstract protected int getMainLayoutId();


    public interface ItemClickListener{
        /**
         *
         * @param viewType  ViewType
         * @param position  position
         * @return Return true dismiss button, False not dismiss
         */
        boolean itemClick(int viewType,int position);

        /**
         * View dismiss callback
         */
        void viewDismiss();
    }

}
