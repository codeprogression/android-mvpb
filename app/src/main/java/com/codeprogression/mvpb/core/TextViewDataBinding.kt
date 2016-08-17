package com.codeprogression.mvpb.core

import android.databinding.BindingAdapter
import android.widget.TextView

@BindingAdapter("numberText")
fun setNumberText(view: TextView, number: Int) {
    view.text = number.toString()
}