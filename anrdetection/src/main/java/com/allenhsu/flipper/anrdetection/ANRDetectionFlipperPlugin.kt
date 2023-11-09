package com.allenhsu.flipper.anrdetection

import android.app.ActivityManager
import android.app.Application
import android.app.ApplicationExitInfo
import android.os.Build
import androidx.annotation.RequiresApi
import com.facebook.flipper.core.FlipperConnection
import com.facebook.flipper.core.FlipperObject
import com.facebook.flipper.core.FlipperPlugin
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets

@RequiresApi(Build.VERSION_CODES.R)
class ANRDetectionFlipperPlugin(private val applicationContext: Application) : FlipperPlugin {
    private var connection: FlipperConnection? = null

    override fun getId(): String {
        return "ANRDetectionFlipperPlugin"
    }

    override fun onConnect(connection: FlipperConnection?) {
        this.connection = connection

        val activityManager = applicationContext.getSystemService(Application.ACTIVITY_SERVICE) as ActivityManager
        val reasons = activityManager.getHistoricalProcessExitReasons(null, 0, 0)

        reasons.filter { info ->
            info.reason == ApplicationExitInfo.REASON_ANR
        }.map { info ->
            FlipperObject.Builder()
                .put("importance", info.importance)
                .put("processName", info.processName)
                .put("reasonCode", info.reason)
                .put("pid", info.pid)
                .put("pss", info.pss)
                .put("rss", info.rss)
                .put("traceFile", info.traceInputStream?.let { it -> convertInputStreamToString(it) } ?: "")
                .put("timestamp", info.timestamp)
                .put("description", info.description)
                .build()
        }.forEach(this::newANR)
    }

    override fun onDisconnect() {
        this.connection = null
    }

    override fun runInBackground(): Boolean = false

    private fun newANR(anr: FlipperObject) {
        connection?.send("newANR", anr)
    }

    @Throws(IOException::class)
    private fun convertInputStreamToString(inputStream: InputStream): String? {
        ByteArrayOutputStream().use { byteArrayOutputStream ->
            inputStream.use { stream ->
                val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                while (true) {
                    val length = stream.read(buffer)
                    if (length == -1) {
                        break
                    }
                    byteArrayOutputStream.write(buffer, 0, length)
                }
                return byteArrayOutputStream.toString(StandardCharsets.UTF_8.name())
            }
        }
    }

    companion object {
        const val DEFAULT_BUFFER_SIZE = 4096
    }
}
