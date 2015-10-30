package com.codeprogression.mvpb.core;

import android.databinding.BindingAdapter;
import android.widget.TextView;

public final class TextViewDataBinding {

    @BindingAdapter("numberText")
    public static void setNumberText(TextView view, int number){
        view.setText(String.valueOf(number));
    }
}