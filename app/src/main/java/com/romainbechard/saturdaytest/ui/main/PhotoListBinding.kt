package com.romainbechard.saturdaytest.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.romainbechard.saturdaytest.data.model.Photo
import timber.log.Timber

@BindingAdapter("android:items")
fun setItems(listView: RecyclerView, items: List<Photo>?) {
    Timber.d("android:items")
    items?.let {
        Timber.d("setItems $items")
        (listView.adapter as PhotoListAdapter).submitList(items)
    }
}