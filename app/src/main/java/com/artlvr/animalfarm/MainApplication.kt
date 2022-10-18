package com.artlvr.animalfarm

import android.app.Application
import com.artlvr.animalfarm.logging.LoggingConstants
import com.datadog.android.Datadog
import com.datadog.android.DatadogSite
import com.datadog.android.core.configuration.Configuration
import com.datadog.android.core.configuration.Credentials
import com.datadog.android.core.configuration.Credentials.Companion.NO_VARIANT
import com.datadog.android.privacy.TrackingConsent
import com.datadog.android.rum.GlobalRum
import com.datadog.android.rum.RumMonitor
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        setupDatadogMonitor()
    }

    private fun setupDatadogMonitor() {
        val configuration = Configuration.Builder(
            logsEnabled = true,
            tracesEnabled = true,
            crashReportsEnabled = true,
            rumEnabled = true
        )
            .useSite(DatadogSite.US1)
            .trackInteractions()
            .trackLongTasks()
            .useViewTrackingStrategy(null)
            .build()
        val credentials = Credentials(LoggingConstants.clientToken, LoggingConstants.environmentName, NO_VARIANT, LoggingConstants.appId)
        Datadog.initialize(this, credentials, configuration, TrackingConsent.GRANTED)

        GlobalRum.registerIfAbsent(RumMonitor.Builder().build())
    }
}
