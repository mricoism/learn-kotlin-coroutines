package com.mricoism.coroutinecontexts

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mricoism.coroutinecontexts.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val TAG = "MainActivityRIKO"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        GlobalScope.launch(Dispatchers.Main) {  }
//        GlobalScope.launch(Dispatchers.IO) {  }
//        GlobalScope.launch(Dispatchers.Default) {  }
//        GlobalScope.launch(Dispatchers.Unconfined) {  }
//        GlobalScope.launch(newSingleThreadContext("MyThread")) {  }

        GlobalScope.launch(Dispatchers.IO) { // Data operation using Dispatcher.IO

            Log.d(TAG, "Starting coroutine in thread: ${Thread.currentThread().name}")
            val answer = doNetworkCall()

            // We can switch the context to Dispatcher.main, by using withContext.
            // Means we want to change the context of this current coroutine to Dispatcher.main.
            // withContext very helpful to change context
            withContext(Dispatchers.Main) { // UI related using Dispatcher.Main

                Log.d(TAG, "Setting UI/Text in thread: ${Thread.currentThread().name}")
                binding.textView.text = answer
            }
            
        }

    }

    suspend fun doNetworkCall(): String {
        delay(3000L)
        return "This is the answer"
    }
}