package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.api.ApiHelper
import com.example.myapplication.data.model.ApiUser
import com.example.myapplication.utils.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel (private val apiHelper: ApiHelper

) : ViewModel() {
    private val users = MutableLiveData<Resource<ApiUser>>()

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        GlobalScope.launch {
            users.postValue(Resource.loading(null))
            try {
                val usersFromApi = apiHelper.getUser()
                users.postValue(Resource.success(usersFromApi))
            } catch (e: Exception) {
                users.postValue(Resource.error("something went wrong",null))
            }
        }
    }
    fun getUsers(): LiveData<Resource<ApiUser>> {
        return users
    }
}