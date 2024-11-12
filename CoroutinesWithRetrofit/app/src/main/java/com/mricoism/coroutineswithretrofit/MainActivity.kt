package com.mricoism.coroutineswithretrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mricoism.coroutineswithretrofit.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.awaitResponse
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
        /*
        // OLD WAYS
        // Solve issue wrong import
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
         */

        /*
        GlobalScope.launch(Dispatchers.IO) {
            val comments = api.getComments().await()
            for (comment in comments) {
                Log.d(TAG, comment.toString())
            }
        }
        */

        /*
        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getComments().awaitResponse()
            if (response.isSuccessful) {
                for (comment in response.body()!!) {
                    Log.d(TAG, comment.toString())
                }
            }
        }
         */

        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getComments()
            if (response.isSuccessful) {
                for (comment in response.body()!!) {
                    Log.d(TAG, comment.toString())
                }
            }
        }

    }
}
