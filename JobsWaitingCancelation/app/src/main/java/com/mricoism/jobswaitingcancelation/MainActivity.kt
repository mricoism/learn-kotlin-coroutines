package com.mricoism.jobswaitingcancelation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mricoism.jobswaitingcancelation.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout


class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val TAG = "MainActivityRIKO"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        // This launch function actually will return a JOB. and you can save job inside variable
        // by defult job will executed. and no need to call. you can wait job done by using .join() and cancel using .cancel()
        val job = GlobalScope.launch(Dispatchers.Default) {
            repeat(5) {
                Log.d(TAG, "Coroutine is still working..")
                delay(1000L)
            }
        }


        runBlocking {
            // join function is suspend function, so you need call it inside coroutine scope
            // .join() function is to suspend coroutine until job is complete execute.
//            job.join()

//            delay(2000L)
            // .cancel() is used for cancel job that running.
//            job.cancel() // its can accept CancelException.
            // How ever canceling coroutine is not always as easy as it is here.
            // Because cancellation is actually cooperative, witch means our coroutine need to be setup to be correctly canceled.
            // So there needs to be enough time tell our coroutine that it has been canceled.
//            Log.d(TAG, "Main thread is continuing.. ")
        }
         */

        /*
        val job2 = GlobalScope.launch(Dispatchers.Default) {
            // This code below will give you example of coroutine that cannot easy to canceled.
            // And demonstrate you have to take care about that in some scenarios.
            Log.d(TAG, "Starting long runing calculation..")
            for (i in 30..40) {
                Log.d(TAG, "Result for i = $i: ${fib(i)}")
            }
            Log.d(TAG, "Ending long runing calculation..")
        }

        runBlocking {
            // this code below will show you, even tho we cancel the job. This job it won't actually be canceled.
            delay(2000L)
            job2.cancel()
            Log.d(TAG, "Canceled job!")
        }
         */


        val job3 = GlobalScope.launch(Dispatchers.Default) {
            Log.d(TAG, "Starting long runing calculation..")

            // in practice usually the reason why we want to cancel coroutine is due to some timeout.
            // let say you have a network call that just take to much time and you want to cancel it.
            // For that coroutine come with very useful suspend function called withTimeout, witch we can just wrap around our long running calculations or long running code.
            withTimeout(2000L) {
                // function and block code will do is..
                // Will do execute this code but if it need longer than three seconds it will cancel it automatically.
                // Without needing us to cancel it manually

                for (i in 30..40) {
                    Log.d(TAG, "Result for i = $i: ${fib(i)}")
                }
            }
            Log.d(TAG, "Ending long runing calculation..")
        }
    }

    fun fib(n: Int): Long {
        return if(n==0) 0
        else if(n==1) 1
        else fib(n - 1) + fib(n - 2)
    }

}