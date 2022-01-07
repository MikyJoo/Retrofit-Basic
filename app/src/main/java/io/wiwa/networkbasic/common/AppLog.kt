package io.wiwa.networkbasic.common

import android.util.Log
import io.wiwa.networkbasic.BuildConfig

class AppLog {
    companion object {
        var isDebug = BuildConfig.DEBUG

        fun e(TAG: String, message: String) {
            if (isDebug)
                Log.e(TAG, buildLogMessage(message))
        }

        fun e(isForce: Boolean, TAG: String, message: String) {
            Log.e(TAG, buildLogMessage(message))
        }

        fun w(TAG: String, message: String) {
            if (isDebug) Log.w(TAG, buildLogMessage(message))
        }

        fun i(TAG: String, message: String) {
            if (isDebug) Log.i(TAG, buildLogMessage(message))
        }

        fun d(TAG: String, message: String) {
            if (isDebug) Log.d(TAG, buildLogMessage(message))
        }

        fun v(TAG: String, message: String) {
            if (isDebug) Log.v(TAG, buildLogMessage(message))
        }

        private fun buildLogMessage(message: String): String {
            val ste = Thread.currentThread().stackTrace[4]
            val sb = StringBuilder()
            sb.append("[")
            sb.append(ste.fileName.replace(".java", ""))
            sb.append("::")
            sb.append(ste.methodName)
            sb.append(":")
            sb.append(ste.lineNumber)
            sb.append("] ")
            sb.append(message)
            return sb.toString()
        }
    }
}
