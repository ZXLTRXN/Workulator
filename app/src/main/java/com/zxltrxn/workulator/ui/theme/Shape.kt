package com.zxltrxn.workulator.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp
import com.zxltrxn.workulator.utils.Constants.CORNER_CURVE_PERCENT


val Shapes = Shapes(
    small = RoundedCornerShape(CORNER_CURVE_PERCENT),
    medium = RoundedCornerShape(CORNER_CURVE_PERCENT * 2),
    large = RoundedCornerShape(topStartPercent = CORNER_CURVE_PERCENT, topEndPercent = CORNER_CURVE_PERCENT),
)