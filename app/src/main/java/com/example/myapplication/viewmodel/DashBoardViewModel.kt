package com.example.myapplication.viewmodel


import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.api.ApiHelper
import com.example.myapplication.data.model.NewsArticles
import com.example.myapplication.utils.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DashBoardViewModel(
    private val apiHelper: ApiHelper

) : ViewModel() {
    private val users = MutableLiveData<Resource<List<NewsArticles>>>()

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        GlobalScope.launch {
            users.postValue(Resource.loading(null))
            try {
                Log.d(ContentValues.TAG, "before coroutine ")
                val usersFromApi = apiHelper.getListOfNewsArticles()
                val allUsersFromApi = mutableListOf<NewsArticles>()
                allUsersFromApi.addAll(usersFromApi)
                users.postValue(Resource.success(allUsersFromApi))
                Log.d(ContentValues.TAG, "before coroutine ")
            } catch (e: Exception) {
                users.postValue(Resource.error("Something Went Wrong", null))
            }
        }
    }

    fun getUsers(): LiveData<Resource<List<NewsArticles>>> {
        return users
    }


}