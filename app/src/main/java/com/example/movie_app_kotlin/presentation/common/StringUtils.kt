package com.example.movie_app_kotlin.presentation.common

import java.text.NumberFormat
import java.util.Locale

fun Int.formatInt(): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
    return numberFormat.format(this.toLong())
}