package com.sean.android.mvpsample.base.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.sean.android.mvpsample.R;

/**
 * Created by sean on 2017. 1. 7..
 */

public class ProgressIndicator extends Dialog {
    private Context context;

    public ProgressIndicator(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.context = context;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        LayoutInflater inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inf.inflate(R.layout.dialog_indicator, null);
        setContentView(layout);
        setCancelable(false);
    }

    @Override
    public void show() {

        try{super.show();}catch(Exception e){}
    }

    @Override
    public void hide() {

        try{super.hide();}catch(Exception e){}
    }

    @Override
    public void dismiss() {

        try{super.dismiss();}catch(Exception e){}
    }
}
