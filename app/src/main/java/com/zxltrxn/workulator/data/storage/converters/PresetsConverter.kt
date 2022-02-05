package com.zxltrxn.workulator.data.storage.converters

import androidx.room.TypeConverter

class PresetsConverter {

    val sep =","
    @TypeConverter
    fun fromPresets(presets:List<Int>):String = presets.joinToString(sep)

    @TypeConverter
    fun toPresets(str:String):List<Int> = str.split(sep).map{ it.toInt() }
}
