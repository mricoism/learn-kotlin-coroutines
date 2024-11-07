package com.mricoism.jobswaitingcancelation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mricoism.jobswaitingcancelation.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text = "MAILLLL"
        binding.Button.setOnClickListener {
            // DO SOMETING HERE
        }

        GlobalScope.launch {
//            delay(1000L) // This is suspend function if you see documentation
            val networkCallAnswer = doNetworkCall() // This first delay call will affect the second delay call. So it will add up, Because they are executed in the same coroutine.
            val networkCallAnswer2 = doNetworkCall2()
            Log.d(TAG, networkCallAnswer)
            Log.d(TAG, networkCallAnswer2)
        }
    }
    // suspend function, only can executed inside another suspend function or coroutine scope like GlobalScope.launch
    suspend fun doNetworkCall(): String {
        delay(3000L)
        return "This is the answer"
    }

    suspend fun doNetworkCall2(): String {
        delay(3000L)
        return "This is the answer 2"
    }
}