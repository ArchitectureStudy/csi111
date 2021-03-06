package com.sean.android.mvvmsample.base.databinding;

import android.databinding.BaseObservable;

import org.parceler.Parcel;

/**
 * Created by Seonil on 2017-02-17.
 */

@Parcel
public class BindableString extends BaseObservable {

    String value;

    public String get() {
        return value != null ? value : "";
    }

    public void set(String value) {
        if (!equals(this.value, value)) {
            this.value = value;
            notifyChange();
        }
    }
    public boolean isEmpty() {
        return value == null || value.isEmpty();
    }

    public boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
