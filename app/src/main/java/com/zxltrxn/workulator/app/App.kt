package com.zxltrxn.workulator.app

import android.app.Application
import com.zxltrxn.workulator.di.appModule
import com.zxltrxn.workulator.di.dataModule
import com.zxltrxn.workulator.di.domainModule
import org.koin.android.BuildConfig
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.android.ext.koin.androidLogger
import org.koin.android.ext.koin.androidContext

class App:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule)) }
    }
}