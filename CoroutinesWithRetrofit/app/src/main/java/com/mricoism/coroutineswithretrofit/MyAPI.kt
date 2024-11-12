package com.mricoism.coroutineswithretrofit

import retrofit2.Call
import retrofit2.http.GET

interface MyAPI {
    @GET("/comments")
    fun getComments(): Call<List<Comment>> // salah import comment ternyata malah import: import org.w3c.dom.Comment
}