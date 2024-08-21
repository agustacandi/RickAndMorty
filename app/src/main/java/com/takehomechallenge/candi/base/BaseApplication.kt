package com.takehomechallenge.candi.base

import android.app.Application
import com.takehomechallenge.candi.di.useCaseModule
import com.takehomechallenge.candi.di.viewModelModule
import com.takehomechallenge.core.utils.ConstVal
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val koinModules = mutableListOf(useCaseModule, viewModelModule)
        koinModules.addAll(ConstVal.coreModules)

        startKoin {
            androidContext(this@BaseApplication)
            modules(koinModules)
        }
    }
}