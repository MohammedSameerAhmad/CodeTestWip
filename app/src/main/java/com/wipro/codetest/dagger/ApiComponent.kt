package com.wipro.codetest.dagger

import com.wipro.codetest.views.InfoListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface ApiComponent {
    fun inject(infoListFragment: InfoListFragment)
}