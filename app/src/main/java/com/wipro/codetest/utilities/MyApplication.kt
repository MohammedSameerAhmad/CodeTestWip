package com.wipro.codetest.utilities

import android.app.Application
import com.wipro.codetest.dagger.ApiComponent
import com.wipro.codetest.dagger.ApiModule
import com.wipro.codetest.dagger.AppModule
import com.wipro.codetest.dagger.DaggerApiComponent

class MyApplication : Application() {

    lateinit var apiComponent: ApiComponent
    override fun onCreate() {
        super.onCreate()

        apiComponent = DaggerApiComponent.builder()
            .appModule(AppModule(this))
            .apiModule(ApiModule())
            .build()

    }

    fun apiComponent(): ApiComponent {
        return apiComponent
    }

}