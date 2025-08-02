package com.remdate.app;

import android.content.Context;
import android.webkit.WebView;

public class WebUtils {
    public static void clearWebViewCache(Context context) {
        try {
            WebView webView = new WebView(context);
            webView.clearCache(true);
        } catch (Exception e) {
            // Handle error
        }
    }
}