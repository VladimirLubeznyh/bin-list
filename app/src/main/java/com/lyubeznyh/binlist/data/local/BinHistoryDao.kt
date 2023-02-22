package com.lyubeznyh.binlist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BinHistoryDao {

    @Query("SELECT * FROM bin_history")
    fun getBinHistory(): Flow<List<BinHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBin(binHistoryEntity:BinHistoryEntity)
}
