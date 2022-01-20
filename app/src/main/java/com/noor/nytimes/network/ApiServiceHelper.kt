package com.noor.nytimes.network

import com.noor.nytimes.data.NyTimesData
import retrofit2.Response
import javax.inject.Singleton

/**
 * Created by Adam Noor on 19/01/2022.
 */
@Singleton
interface ApiServiceHelper {

    suspend fun fetchNyTimesArticles() : Response<NyTimesData>
}