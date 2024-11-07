package com.mricoism.asyncandawait

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mricoism.asyncandawait.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import kotlin.system.measureTimeMillis


class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val TAG = "MainActivityRIKO"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch(Dispatchers.IO) {
            // Kotlin has function to see time difference. Witch we can easily measure the time a piece of code needs for execution
            val time = measureTimeMillis {
                /*
                // This code bellow will add up. Because each of them is delay for 3 second. Then the total it till take 6 second to complete them both
//                val answer1 = networkCall1()
//                val answer2 = networkCall2()
//                Log.d(TAG, "Answer 1: $answer1")
//                Log.d(TAG, "Answer 2: $answer2")
                */

                /*
                // Some solution for make sure both executed at the same time.
                // This code below is one of solution, but its bad practice.
                var answer1: String? = null
                var answer2: String? = null
                val job1 = launch { answer1 = networkCall1() }
                val job2 = launch { answer2 = networkCall2() }
                job1.join()
                job2.join()
                Log.d(TAG, "Answer 1: $answer1")
                Log.d(TAG, "Answer 2: $answer2")
                 */

                // As i mentioned above, we have some better solution for make sure both executed at the same time.
                // Using async. Its similiar to launch, it also start new coroutine but it won't return a job.
                // not like launch, it instead will return a deferred. Can be used to get the result.
                val answer1 = async { networkCall1() }
                val answer2 = async { networkCall2() }

                // Because its return Deferred<String>. We need to use .await()
                // .await() witch will block our current coroutine. Until the answer is available
                Log.d(TAG, "Answer 1: ${answer1.await()}")
                Log.d(TAG, "Answer 2: ${answer2.await()}")
            }

            //and we can this async instead of launch for the global scope.
            //GlobalScope.async {  }

            Log.d(TAG, "Request took $time mili seconds")




        }
    }

    suspend fun networkCall1(): String {
        delay(3000L)
        return "Answter 1"
    }
    suspend fun networkCall2(): String {
        delay(3000L)
        return "Answter 2"
    }

}