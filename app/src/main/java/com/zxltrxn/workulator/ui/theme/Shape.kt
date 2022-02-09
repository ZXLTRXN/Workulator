package com.zxltrxn.workulator.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp
import com.zxltrxn.workulator.utils.Constants.CORNERCURVEPERCENT


val Shapes = Shapes(
    small = RoundedCornerShape(CORNERCURVEPERCENT),
    medium = RoundedCornerShape(CORNERCURVEPERCENT * 2),
    large = RoundedCornerShape(topStartPercent = CORNERCURVEPERCENT, topEndPercent = CORNERCURVEPERCENT),
)