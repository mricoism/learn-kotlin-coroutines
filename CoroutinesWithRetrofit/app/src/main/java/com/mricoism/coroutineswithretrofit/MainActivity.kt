package com.mricoism.coroutineswithretrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mricoism.coroutineswithretrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val TAG = "MainActivityRIKO"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyAPI::class.java)

        api.getComments().enqueue(object : Callback<List<Comment>> {
            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Log.e(TAG, "Error: $t")
            }

            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        for (comment in it) {
                            Log.d(TAG, comment.toString())
                        }
                    }
                }
            }
        })

    }
}
