package com.lyubeznyh.binlist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BinHistoryEntity::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun getBinHistoryDao(): BinHistoryDao
}
