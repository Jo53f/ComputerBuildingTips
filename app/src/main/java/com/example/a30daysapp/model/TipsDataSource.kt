package com.example.a30daysapp.model

import androidx.compose.ui.res.stringResource
import com.example.a30daysapp.R

object TipsDataSource {
    val tips = listOf(
        Tip(
            tipTitle = R.string.tip1_title,
            tipContent = R.string.tip1_content,
            tipPicture = R.drawable.pexels_photo_2582935
        ),
        Tip(
            tipTitle = R.string.tip2_title,
            tipContent = R.string.tip2_content,
            tipPicture = R.drawable.pexels_iseeghoststoo_1010487
        ),
        Tip(
            tipTitle = R.string.tip3_title,
            tipContent = R.string.tip3_content,
            tipPicture = R.drawable.cpu_processor_macro_pen_40879
        )
    )
}