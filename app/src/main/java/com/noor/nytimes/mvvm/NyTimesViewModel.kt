package com.noor.nytimes.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noor.nytimes.data.NyTimesData
import com.noor.nytimes.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Adam Noor on 27/12/2021.
 */

@HiltViewModel
class NyTimesViewModel @Inject constructor(
    private val repository: NyTimesArticlesRepository
) :ViewModel() {


    private val _mutableData = MutableLiveData<DataState<NyTimesData>>()

    val liveData: LiveData<DataState<NyTimesData>>
        get() = _mutableData


    fun getArticles() {
        viewModelScope.launch {
            fetchArticles {
                repository.fetchArticles()
                    .flowOn(Dispatchers.IO)
            }
        }
    }

    private fun fetchArticles(block: suspend () -> Flow<DataState<NyTimesData>>) {
        viewModelScope.launch {
            try {
                val data = block()
                data.map {
                    _mutableData.value = it
                }.launchIn(viewModelScope)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

        viewModelScope.cancel()
    }
}