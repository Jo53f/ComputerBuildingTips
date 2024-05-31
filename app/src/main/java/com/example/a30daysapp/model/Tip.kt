package com.example.a30daysapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Tip(
    @StringRes val tipTitle: Int,
    @StringRes val tipContent: Int,
    @DrawableRes val tipPicture: Int
)