package com.heterpu.ui.basedialog;

import android.content.Context;

import com.heterpu.ui.basedialog.interfaces.ColorChangeable;

public abstract class ColorBaseDialog extends EventBaseDialog implements ColorChangeable {


    public ColorBaseDialog(Context ctx, ItemClickListener itemClickListener) {
        super(ctx, itemClickListener);
    }

    public void setDimLevel(float level){

    }

    public void setDimMode(int mode){

    }

    public void setBackgroundColor(float level){

    }

    public void delayCreate(){
        super.onCreate();
    }

    @Override
    protected void onCreate() {

    }

}
