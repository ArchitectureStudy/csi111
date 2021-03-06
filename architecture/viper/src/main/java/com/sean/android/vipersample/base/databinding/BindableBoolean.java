package com.sean.android.vipersample.base.databinding;

import org.parceler.Parcel;

/**
 * Created by Seonil on 2017-02-17.
 */

@Parcel
public class BindableBoolean extends BaseObservable {
    
    boolean mValue;

    public boolean get() {
        return mValue;
    }

    public void set(boolean value) {
        if (mValue != value) {
            this.mValue = value;
            notifyChange();
        }
    }
}
