package com.wipro.codetest.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wipro.codetest.models.MainListDataClass
import com.wipro.codetest.interfaces.APICallInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListFragmentViewModel(val apiCallInterface: APICallInterface) : ViewModel() {

    val response = MutableLiveData<MainListDataClass>()
    val error = MutableLiveData<Boolean>().default(false)
    val loading = MutableLiveData<Boolean>().default(true)

    var responseCall: Call<MainListDataClass>? = null

    init {
        fetchResponse()
    }

    fun fetchResponse() {
        //when refresh call set default value again to loading and error
        loading.value = true
        error.value = false

        responseCall = apiCallInterface.getNetworkData()
        responseCall?.enqueue(object : Callback<MainListDataClass> {
            override fun onResponse(
                call: Call<MainListDataClass>,
                output: Response<MainListDataClass>
            ) {
                response.value = output.body()
                loading.value = false
                responseCall = null
            }

            override fun onFailure(call: Call<MainListDataClass>, t: Throwable) {
                error.value = true
                loading.value = false
                responseCall = null
            }
        })
    }

    override fun onCleared() {
        if (responseCall != null) {
            responseCall!!.cancel()
            responseCall = null
        }
    }
}

fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }