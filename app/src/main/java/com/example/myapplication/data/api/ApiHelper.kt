package com.example.myapplication.data.api

import com.example.myapplication.data.model.ApiUser
import com.example.myapplication.data.model.NewsArticles

interface ApiHelper {

      suspend fun getListOfNewsArticles(): List<NewsArticles>

      suspend fun getUser():ApiUser

      //suspend fun getUsers(): List<ApiUser>
//    suspend fun getMoreUsers(): List<ApiUser>
//
//    suspend fun getUsersWithError(): List<ApiUser>

}