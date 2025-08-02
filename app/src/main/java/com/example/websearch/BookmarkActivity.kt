package com.remdate.app

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class BookmarkActivity : AppCompatActivity() {
    private lateinit var bookmarkList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        bookmarkList = findViewById(R.id.bookmarkList)
        // Load bookmarks dari storage
        val bookmarks = BookmarkUtils.getBookmarks(this)
        bookmarkList.adapter = BookmarkAdapter(this, bookmarks)
    }
}