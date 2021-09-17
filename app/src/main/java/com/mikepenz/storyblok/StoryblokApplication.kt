package com.mikepenz.storyblok

import android.app.Application
import co.touchlab.kermit.Kermit
import com.mikepenz.common.di.initKoin
import com.mikepenz.storyblok.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class StoryblokApplication : Application(), KoinComponent {
    private val logger: Kermit by inject()

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@StoryblokApplication)
            modules(appModule)
        }

        logger.d { "StoryblokApplication" }
    }
}
