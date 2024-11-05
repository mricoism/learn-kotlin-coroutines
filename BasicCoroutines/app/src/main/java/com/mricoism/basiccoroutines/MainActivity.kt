package com.mricoism.basiccoroutines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mricoism.basiccoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity: AppCompatActivity() {

    val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "RIKO TEST")

        // Semua coroutin harus di jalakan di dalam coroutines scope
        // Global scope means coroutines will live as long as our application does.
        // if coroutines finish its job it will be destroyed and not kept alive until the application dies.
        // And if we kill application and the coroutine still has instruction or job. then means the coroutine will also die (destroyed). Because we declare that GlobalScope
        GlobalScope.launch {
            //Pause and resume
            // Delay is work very diffrent than sleep. BCS delay will only pause current coroutines and it will not block the whole thread. (which is very big missConception of coroutines newbies)
            Thread.sleep(3000L) // for thread or from thread
            delay(3000L) //Coroutines own. Only Impact in coroutines not all thread
            Log.d(TAG, "Coroutines this is in thread ${Thread.currentThread().name}")
        }
        Log.d(TAG, "And This is thread ${Thread.currentThread().name}")

        // LAST IMPORTANT THING
        // IF The main thread finishes with his work, then that means all other threads and corroutines will cancelled.
        // We can see if we increase that delay to 5 seconds, then kill the app. the process will cancelled
    }
}