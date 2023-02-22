package com.lyubeznyh.binlist.di.module

import android.content.Context
import androidx.room.Room
import com.lyubeznyh.binlist.data.local.AppDatabase
import com.lyubeznyh.binlist.data.local.BinHistoryDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "bin_data_base").build()

    @Singleton
    @Provides
    fun provideBinHistoryDao(appDatabase: AppDatabase): BinHistoryDao =
        appDatabase.getBinHistoryDao()
}
