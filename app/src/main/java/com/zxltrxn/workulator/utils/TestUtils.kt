package com.zxltrxn.workulator.utils

import com.zxltrxn.workulator.data.storage.entities.Task

fun Task.isEquivalent(other:Task): Boolean = (this.name == other.name &&
        this.target_time == other.target_time && this.presets == other.presets &&
        this.active == other.active)