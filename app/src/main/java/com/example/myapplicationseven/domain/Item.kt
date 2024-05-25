package com.example.myapplicationseven.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "items")
data class Item (
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo(name = "title")
    var title: String?,
    @ColumnInfo(name = "description")
    var description: String?,
    @ColumnInfo(name = "priority")
    var priority: Int?,
    @ColumnInfo(name = "type")
    var type: String?,
    @ColumnInfo(name = "count")
    var count: Int?,
    @ColumnInfo(name = "period")
    var period: Int?
)