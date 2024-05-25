package com.example.myapplicationseven.data

import androidx.lifecycle.LiveData
import com.example.myapplicationseven.domain.Item

class ItemRepository(val itemDao: ItemDao) {

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work off the main thread.
    suspend fun insertItem(item: Item) {
        itemDao.insert(item)
    }

    suspend fun updateItem(item: Item) {
        itemDao.update(item)
    }

    fun callMethod(): LiveData<List<Item>> {
        return itemDao.getAll()
    }

}