package com.wipro.codetest.dagger

import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wipro.codetest.interfaces.APICallInterface
import com.wipro.codetest.utilities.BASE_URL
import com.wipro.codetest.utilities.ViewModelFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class ApiModule {

    @Provides
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(httpLoggingInterceptor)
        return okHttpClient.build()
    }



    @Provides
    fun provideLogginInterceptor(): HttpLoggingInterceptor {
        var logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        return logging
    }

    @Provides
    fun provideAPICallInterface(retrofit: Retrofit): APICallInterface {
        return retrofit.create<APICallInterface>(APICallInterface::class.java)
    }

    @Provides
    fun provideViewModelFactory(apiCallInterface: APICallInterface): ViewModelProvider.Factory {
        return ViewModelFactory(apiCallInterface)
    }


}