package com.mikepenz.common.repository

import co.touchlab.kermit.Logger
import co.touchlab.kermit.NSLogLogger
import org.koin.dsl.module

actual fun platformModule() = module {
    single<Logger> { NSLogLogger() }
}
