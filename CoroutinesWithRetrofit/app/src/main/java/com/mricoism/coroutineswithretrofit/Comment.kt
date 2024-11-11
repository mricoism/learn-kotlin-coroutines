package com.mricoism.coroutineswithretrofit

import com.google.gson.annotations.SerializedName

data class Comment(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)
