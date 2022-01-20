package com.noor.nytimes.network

import com.noor.nytimes.data.NyTimesData
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Adam Noor on 19/01/2022.
 */
@Singleton
class ApiServiceHelperImplementation @Inject constructor(
    @Named("APIServiceJSON") private val apiServiceJSON: RetrofitServiceInstance
) : ApiServiceHelper {


    override suspend fun fetchNyTimesArticles(): Response<NyTimesData> = apiServiceJSON.loadNyTimesArticles()

}