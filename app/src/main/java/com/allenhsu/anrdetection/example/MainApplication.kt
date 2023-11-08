package com.allenhsu.anrdetection.example

import android.app.Application
import android.os.Build
import com.allenhsu.flipper.anrdetection.ANRDetectionFlipperPlugin
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.soloader.SoLoader

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)

        val client = AndroidFlipperClient.getInstance(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            client.addPlugin(ANRDetectionFlipperPlugin(this))
        }
        client.start()
    }
}
