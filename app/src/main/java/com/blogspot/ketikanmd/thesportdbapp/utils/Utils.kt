package com.blogspot.ketikanmd.thesportdbapp.utils

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun String.dateFormat(format: String? = "EEEE, dd MMMM yyyy"): String {
    val date: Date = SimpleDateFormat("dd/MM/yy").parse(this)
    return SimpleDateFormat(format, Locale("in", "ID")).format(date)
}