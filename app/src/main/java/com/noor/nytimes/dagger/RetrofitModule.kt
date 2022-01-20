package com.noor.nytimes.dagger

import androidx.multidex.BuildConfig
import com.noor.nytimes.network.ApiServiceHelper
import com.noor.nytimes.network.ApiServiceHelperImplementation
import com.noor.nytimes.network.RetrofitServiceInstance
import com.noor.nytimes.utils.AppConstants
import com.noor.nytimes.utils.StringConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Adam Noor on 19/01/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideConverter(): Converter.Factory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return if (BuildConfig.DEBUG)
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        else
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }

    @Named("JSONInterceptor")
    @Provides
    fun providesOkHttpInterceptorJSON(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val original: Request = chain.request()
            val requestBuilder: Request.Builder =
                original.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    @Singleton
    @Named("OkHttpClientJSON")
    @Provides
    fun provideOkHttpClientJSON(
        loggingInterceptor: HttpLoggingInterceptor,
        @Named("JSONInterceptor") interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .build()
    }

    @Singleton
    @Named("RetrofitJSON")
    @Provides
    fun provideRetrofitJSON(
        @Named("OkHttpClientJSON")okHttpClient: OkHttpClient,
        baseUrl: String,
        converter: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(StringConverterFactory.create())
            .addConverterFactory(converter)
            .build()
    }

    @Singleton
    @Named("APIServiceJSON")
    @Provides
    fun provideApiServiceJSON(@Named("RetrofitJSON") retrofit: Retrofit): RetrofitServiceInstance {
        return retrofit.create(RetrofitServiceInstance::class.java)
    }

    @Provides
    @Singleton
    fun provideApiServiceHelper(apiHelper: ApiServiceHelperImplementation) : ApiServiceHelper = apiHelper

    @Singleton
    @Provides
    fun provideBaseUrl() = AppConstants.baseUrl

}