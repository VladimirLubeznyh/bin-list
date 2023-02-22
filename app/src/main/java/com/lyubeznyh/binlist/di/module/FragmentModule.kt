package com.lyubeznyh.binlist.di.module

import com.lyubeznyh.binlist.presentation.BinSearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun binSearchFragmentInject(): BinSearchFragment
}
