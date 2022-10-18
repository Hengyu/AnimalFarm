package com.artlvr.animalfarm.logging

import com.datadog.android.log.Logger

val logger = Logger.Builder()
    .setNetworkInfoEnabled(true)
    .setDatadogLogsEnabled(true)
    .setBundleWithTraceEnabled(true)
    .setLoggerName("General")
    .build()

object LoggingConstants {
    val appId: String = "6a1eb5ff-7bdc-4e20-a5fe-e2213d061e17"
    val clientToken: String = "pub3811aaccd656a0c7c20fc3d7e06020f2"
    val environmentName: String = "prod"
}
