package com.example.myapplicationseven

import android.app.Application
import com.example.myapplicationseven.data.ItemDatabase
import com.example.myapplicationseven.data.ItemRepository

class ItemApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { ItemDatabase.getDatabase(this) }
    val repository by lazy { ItemRepository(database.itemDao()) }
}