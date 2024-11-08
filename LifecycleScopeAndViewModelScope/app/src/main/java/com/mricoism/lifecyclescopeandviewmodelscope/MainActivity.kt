package com.mricoism.lifecyclescopeandviewmodelscope

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mricoism.lifecyclescopeandviewmodelscope.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis


class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val TAG = "MainActivityRIKO"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Button.setOnClickListener {
            /*
            // This is to demonstrate that GlobalScope become bad practice
            GlobalScope.launch {
                while (true) { // Create Global scope and keep it alive (infinity loop)
                    delay(1000L) // delay each loop iteration for 1 second
                    Log.d(TAG, "Strill running..")
                }
            }

            // this GlobalScope will executed at the same time with GlobalScope above.
            GlobalScope.launch { // Another Global Scope for start new activity.
                delay(5000L)

                // using `this@MainActivity` because we inside coroutine scope, so we cannot just use `this`
                Intent(this@MainActivity, SecondActivity::class.java).also {
                    startActivity(it) // start new activity (SecondActivity)
                    finish() // its will quit our current main activity, after we started our SecondActivity
                }
            }
            */
            // Explanation:
            // So our goal is to show that if we using GlobalScope (infinity loop) and we change or move activity.
            // it will keep live. Even tho we has move to SecondActivity.
            // It won't be destroy if our activity destroy. Instead it will be destroyed when our whole application is destroyed.
            // And its very big mistake, that can easily create memory leaks.
            // Because if the coroutine started in main activity, uses resources of that main activity, witch is now destroyed.
            // The resources won't garbage collected, even though the activity is destroyed. because the coroutine still uses those resources.



            // And to solve this problem, we should swap out Global scope with lifecycleScope
            lifecycleScope.launch {
                // What this will do ?, it will stick this coroutine to the lifecycle of our activity.
                // So if our activity is destroyed, that means that all coroutines launch in this lifecycleScope will also destroyed.
                while (true) {
                    delay(1000L)
                    Log.d(TAG, "Strill running..")
                }
                // Just keep in mind if you long running calculation and doesn't suspend.
                // you must regularly check if it is active. Otherwise it can't be canceled (check learn source about jobs and cancellation)
            }
            GlobalScope.launch {
                delay(5000L)
                Intent(this@MainActivity, SecondActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }

            // Another scope that is included is viewModelScope.
            // This don't have example because its just the same as lifecycleScope.
            // Only that it will keep your coroutines alive as long as your viewModel is alive.



        }

    }
}