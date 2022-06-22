package com.example.myapplication.data.api

import com.example.myapplication.data.model.ApiUser

class ApiHelperImpl( val apiService: ApiService) : ApiHelper {

   // override suspend fun getUsers() = apiService.getUsers()
    override suspend fun getListOfNewsArticles() = apiService.getListOfNewsArticles()
    override suspend fun getUser() = apiService.getUser()

}