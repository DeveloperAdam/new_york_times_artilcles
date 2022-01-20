package com.noor.nytimes.mvvm

import com.noor.nytimes.utils.NyTimesApp
import com.noor.nytimes.R
import com.noor.nytimes.data.NyTimesData
import com.noor.nytimes.network.ApiServiceHelper
import com.noor.nytimes.network.ApiServiceHelperImplementation
import com.noor.nytimes.utils.AppConstants
import com.noor.nytimes.utils.DataState
import com.noor.nytimes.utils.NetworkHelper
import java.lang.Exception
import java.net.HttpURLConnection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Adam Noor on 19/01/2022.
 */
@Singleton
class NyTimesArticlesRepository @Inject constructor(
    private val context: NyTimesApp,
    private val networkHelper: NetworkHelper,
    private val apiServiceHelper: ApiServiceHelperImplementation
){

    suspend fun fetchArticles() : Flow<DataState<NyTimesData>> = flow{

        try {

            if (!networkHelper.hasActiveInternetConnection()) {
                emit(DataState.NetworkError(context.getString(R.string.timeout_error)))
                return@flow
            }

            //Initiate
            emit(DataState.Loading)
            val response = apiServiceHelper.fetchNyTimesArticles()

            when(response.code()) {
                HttpURLConnection.HTTP_OK -> { emit(DataState.Success(response.body())) }

                HttpURLConnection.HTTP_INTERNAL_ERROR -> emit(DataState.Error(context.getString(R.string.generic_error)))
            }
        }
        catch (e: Exception)
        {
            emit(DataState.Error(context.getString(R.string.generic_error)))
            e.printStackTrace()
        }
    }
}