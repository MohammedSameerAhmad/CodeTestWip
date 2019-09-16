package com.wipro.codetest.utilities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wipro.codetest.interfaces.APICallInterface
import com.wipro.codetest.viewmodels.ListFragmentViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(val apiCallInterface: APICallInterface) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListFragmentViewModel::class.java)) {
            return ListFragmentViewModel(apiCallInterface) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}
