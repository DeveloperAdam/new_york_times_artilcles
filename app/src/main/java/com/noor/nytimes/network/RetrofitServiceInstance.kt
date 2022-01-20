package com.noor.nytimes.network

import com.noor.nytimes.data.NyTimesData
import com.noor.nytimes.utils.AppConstants
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Adam Noor on 27/12/2021.
 */
interface RetrofitServiceInstance {


    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json?")
    suspend fun loadNyTimesArticles(
        @Query("api-key") key: String = AppConstants.apiKey
    ): Response<NyTimesData>
}