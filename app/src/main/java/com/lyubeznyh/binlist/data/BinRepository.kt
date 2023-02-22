package com.lyubeznyh.binlist.data

import android.content.Context
import com.lyubeznyh.binlist.data.local.BinHistoryEntity
import com.lyubeznyh.binlist.data.local.BinHistoryDao
import com.lyubeznyh.binlist.data.network.BinApi
import com.lyubeznyh.binlist.domain.BinModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BinRepository @Inject constructor(
    private val binApi: BinApi,
    private val context: Context,
    private val binHistoryDao: BinHistoryDao
) {

    fun getBinHistory(): Flow<List<Int>> = binHistoryDao.getBinHistory().toFlowBinNumbers()

    suspend fun insertBin(bin: Int) {
        withContext(Dispatchers.IO) {
            binHistoryDao.insertBin(BinHistoryEntity(bin))
        }
    }

    suspend fun getBinInfo(binNumber: Int): BinModel = withContext(Dispatchers.IO) {
        binApi.getBinInfo(binNumber).mapToBinModel(context)
    }
}
