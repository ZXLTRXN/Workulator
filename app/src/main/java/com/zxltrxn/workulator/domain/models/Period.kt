package com.zxltrxn.workulator.domain.models

enum class Period(val minutes:Int) {
    DAY(1440),
    WEEK(10080),
    MONTHS(43200)
}