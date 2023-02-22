package com.lyubeznyh.binlist.di.module

import com.lyubeznyh.binlist.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ContributesAndroidInjector
    fun provideMainActivity(): MainActivity
}
