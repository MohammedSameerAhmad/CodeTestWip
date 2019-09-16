package com.wipro.codetest.interfaces

import com.wipro.codetest.models.MainListDataClass
import retrofit2.Call
import retrofit2.http.GET

interface APICallInterface {

    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getNetworkData(): Call<MainListDataClass>
}