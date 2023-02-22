package com.lyubeznyh.binlist.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bin_history")
data class BinHistoryEntity(
    @PrimaryKey
    @ColumnInfo
    val bin:Int
)
