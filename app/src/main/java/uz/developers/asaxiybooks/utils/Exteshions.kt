package com.example.nasiyaapp.utils

import android.util.Log

fun String.myLog() = Log.d("TTT", this)
fun String.onlyLetters() = all { it.isLetter() }