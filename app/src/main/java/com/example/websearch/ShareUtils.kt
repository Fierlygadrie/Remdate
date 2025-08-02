package com.remdate.app

import android.content.Context
import android.content.Intent

object ShareUtils {
    fun shareUrl(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, url)
        context.startActivity(Intent.createChooser(intent, "Bagikan melalui"))
    }
}