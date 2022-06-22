package com.example.myapplication.data.api

import com.example.myapplication.data.model.ApiUser
import com.example.myapplication.data.model.NewsArticles
import retrofit2.http.GET

interface ApiService {

    @GET("7c27fa874f0a4d46e4d4/articles")
    suspend fun getListOfNewsArticles() : List<NewsArticles>

    @GET("/0774724810730d4ee184")
    suspend fun getUser() : ApiUser


//    @GET("articles")
//    suspend fun getUsers(): List<ApiUser>

    //  https://s3-ap-southeast-1.amazonaws.com/he-public-data/placesofinterest39c1c48.json
    //  https://demo.ezetap.com/mobileapps/android_assignment.json

}