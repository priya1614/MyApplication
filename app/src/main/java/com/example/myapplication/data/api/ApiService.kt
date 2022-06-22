package com.example.myapplication.data.api

import com.example.myapplication.data.model.ApiUser
import com.example.myapplication.data.model.NewsArticles
import retrofit2.http.GET

interface ApiService {

    @GET("7c27fa874f0a4d46e4d4/articles")
    suspend fun getListOfNewsArticles() : List<NewsArticles>

    @GET("/0774724810730d4ee184")
    suspend fun getUser() : ApiUser

}