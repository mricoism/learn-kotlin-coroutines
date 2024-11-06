package com.mricoism.suspendfunctions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mricoism.suspendfunctions.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text = "MAILLLL"
        binding.Button.setOnClickListener {
            // DO SOMETING HERE
        }

        GlobalScope.launch {
            delay(1000L) // This is suspend function if you see documentation
        }
    }

//    suspend fun d
}