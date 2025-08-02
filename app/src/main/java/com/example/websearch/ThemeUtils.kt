package com.remdate.app

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

object ThemeUtils {
    fun setDarkMode(context: Context, enabled: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (enabled) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )
    }
}