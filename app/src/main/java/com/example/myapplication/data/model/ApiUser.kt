package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class ApiUser (
    @SerializedName("token")
    var token :String ="",
    @SerializedName("full_name")
    var name:String = " "
)
