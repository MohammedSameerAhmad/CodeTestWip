package com.wipro.codetest.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(context: Context) {
    var context = context

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }
}