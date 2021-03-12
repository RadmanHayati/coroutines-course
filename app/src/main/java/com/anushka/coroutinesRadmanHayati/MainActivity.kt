package com.anushka.coroutinesRadmanHayati

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("MyTag", "calculation jus")
            var stock1 = async { getStuck1() }
            var stock2 = async { getStuck2() }
           val total = stock1.await() + stock2.await()
            Log.i("MyTag", "total is $total")
        }
    }
}
private suspend fun getStuck1(): Int {
    delay(10000)
    Log.i("MyTag", "stock1 returned")
    return 1111
}
private suspend fun getStuck2(): Int {
    delay(5000)
    Log.i("MyTag", "stock2 returned")
    return 1111
}


/*
1
    private suspend fun downloadUserData() {
        for (i in 1..200000) {
            withContext(Dispatchers.Main){
                tvUserMessage.text =  "Downloading user $i in ${Thread.currentThread().name}"
            }

        }
    }

 */

