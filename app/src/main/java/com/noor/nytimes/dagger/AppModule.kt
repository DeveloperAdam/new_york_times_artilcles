package com.noor.nytimes.dagger

import android.content.Context
import com.noor.nytimes.network.ApiServiceHelper
import com.noor.nytimes.network.ApiServiceHelperImplementation
import com.noor.nytimes.network.RetrofitServiceInstance
import com.noor.nytimes.utils.NyTimesApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Adam Noor on 27/12/2021.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context) = context as NyTimesApp

}