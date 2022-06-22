package com.example.myapplication.di

import com.example.myapplication.data.api.ApiHelper
import com.example.myapplication.data.api.ApiHelperImpl
import com.example.myapplication.data.api.ApiService
import com.example.myapplication.data.api.RetrofitBuilder
import dagger.Module
import dagger.Provides


@Module
class ApiHelperModule {

    @CustomActivityScope
    @Provides
    fun provideRetrofitBuilder(): ApiService {
        return RetrofitBuilder.apiService
    }

    @CustomActivityScope
    @Provides
    fun provideApiHelper(apiService: ApiService): ApiHelper {
        return ApiHelperImpl(apiService)
    }

}