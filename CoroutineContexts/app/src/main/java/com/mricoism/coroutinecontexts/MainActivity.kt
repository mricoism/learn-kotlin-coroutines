package com.mricoism.coroutinecontexts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mricoism.coroutinecontexts.databinding.ActivityMainBinding

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


    }
}