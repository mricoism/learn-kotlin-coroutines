package com.mricoism.runblocking

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mricoism.runblocking.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val TAG = "MainActivityRIKO"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "Before runBlocking")
        GlobalScope.launch(Dispatchers.Main) {
            // This wont block the thread, and we still can use our UI.
            delay(3000L)
        }

        // Question: WHY WE NEED THIS ?
        runBlocking {
            // This is coroutine scope too.
            // this actually will block the main threads, and will block our UI update.


            // And we can start new coroutine like inside GlobalScope.launch, By writing launch inside runBlocking.
            // this code bellow won't add up. Because its will executed at the same time (Async).
            launch(Dispatchers.IO) {
                delay(10_000L)
                Log.d(TAG, "Finish IO Coroutine 1")
            }
            launch(Dispatchers.IO) {
                delay(10_000L)
                Log.d(TAG, "Finish IO Coroutine 2")
            }

            Log.d(TAG, "Start of runBlocking")
            delay(5000L)
            Log.d(TAG, "End of blocking")
            Log.d(TAG, "After runBlocking")
        }

//        Thread.sleep(5000L) // This no need call inside coroutine scope
//        delay(3000L) // This need to call inside Coroutine scope. And runBlocing actually give you way to call delay without coroutines scope. LIKE THIS



    }
}