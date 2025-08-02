package com.remdate.app

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var searchBox: EditText
    private lateinit var filterSpinner: Spinner
    private lateinit var dateSpinner: Spinner
    private lateinit var refreshButton: ImageButton
    private lateinit var loadingBar: ProgressBar
    private lateinit var bookmarkButton: ImageButton
    private lateinit var historyButton: ImageButton
    private lateinit var settingsButton: ImageButton
    private lateinit var shareButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        // Aktifkan dark mode auto
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        searchBox = findViewById(R.id.searchBox)
        filterSpinner = findViewById(R.id.filterSpinner)
        dateSpinner = findViewById(R.id.dateSpinner)
        refreshButton = findViewById(R.id.refreshButton)
        loadingBar = findViewById(R.id.loadingBar)
        bookmarkButton = findViewById(R.id.bookmarkButton)
        historyButton = findViewById(R.id.historyButton)
        settingsButton = findViewById(R.id.settingsButton)
        shareButton = findViewById(R.id.shareButton)

        setupWebView()
        setupFilter()
        setupDate()
        setupSearch()
        setupRefresh()
        setupNavigation()
        setupShare()
    }

    private fun setupWebView() {
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                loadingBar.visibility = ProgressBar.VISIBLE
            }
            override fun onPageFinished(view: WebView?, url: String?) {
                loadingBar.visibility = ProgressBar.GONE
            }
        }
        webView.settings.javaScriptEnabled = true
    }

    private fun setupFilter() {
        val filterOptions = arrayOf(
            "rempang", "rempang update", "rempang galang",
            "sembulang", "galang", "pulau rempang", "pulau galang",
            "rempang galang", "tanjung banun", "sei raya",
            "sungai raya", "rempang batam", "rempang eco city"
        )
        filterSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, filterOptions)
        filterSpinner.setSelection(0)
    }

    private fun setupDate() {
        val dateOptions = arrayOf("Hari Ini", "Minggu Ini", "Bulan Ini")
        dateSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dateOptions)
        dateSpinner.setSelection(0)
    }

    private fun setupSearch() {
        findViewById<Button>(R.id.searchButton).setOnClickListener {
            val keyword = filterSpinner.selectedItem.toString()
            val dateFilter = dateSpinner.selectedItem.toString()
            val query = searchBox.text.toString()
            val url = buildSearchUrl(keyword, query, dateFilter)
            webView.loadUrl(url)
            // Simpan ke history, bisa ditambah logic sesuai kebutuhan
        }
    }

    private fun buildSearchUrl(keyword: String, query: String, dateFilter: String): String {
        val dateParam = when(dateFilter) {
            "Hari Ini" -> "d"
            "Minggu Ini" -> "w"
            else -> "m"
        }
        return "https://www.google.com/search?q=${keyword}+${query}&hl=id&tbs=qdr:${dateParam}"
    }

    private fun setupRefresh() {
        refreshButton.setOnClickListener {
            webView.reload()
        }
    }

    private fun setupNavigation() {
        bookmarkButton.setOnClickListener {
            startActivity(Intent(this, BookmarkActivity::class.java))
        }
        historyButton.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
        settingsButton.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    private fun setupShare() {
        shareButton.setOnClickListener {
            val url = webView.url ?: ""
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, url)
            startActivity(Intent.createChooser(intent, "Bagikan melalui"))
        }
    }
}