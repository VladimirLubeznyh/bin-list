package com.lyubeznyh.binlist.di

import android.content.Context
import com.lyubeznyh.binlist.BinListApplication
import com.lyubeznyh.binlist.di.module.ActivityModule
import com.lyubeznyh.binlist.di.module.DatabaseModule
import com.lyubeznyh.binlist.di.module.FragmentModule
import com.lyubeznyh.binlist.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        ActivityModule::class,
        FragmentModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class
    ]
)
@Singleton
interface AppComponent:AndroidInjector<BinListApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}
