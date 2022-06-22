package com.example.myapplication.data.model

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName

data class NewsArticles(

    @SerializedName("content")
    val content: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("author")
    val author: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("publishedAt")
    val publishedAt: String = "",
    @SerializedName("source")
    val source :Source,
    @SerializedName("urlToImage")
    val imageUrl :String = ""

)


