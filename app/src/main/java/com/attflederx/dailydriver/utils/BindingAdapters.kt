package com.attflederx.dailydriver.utils

import android.util.Log
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    val imgUri = url.toUri().buildUpon().scheme("https").build()
    Glide.with(imageView.context).load(imgUri).into(imageView)
}