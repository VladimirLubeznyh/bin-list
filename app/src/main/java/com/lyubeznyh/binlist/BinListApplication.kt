package com.lyubeznyh.binlist

import com.lyubeznyh.binlist.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BinListApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent
            .builder()
            .context(this)
            .build()
}
