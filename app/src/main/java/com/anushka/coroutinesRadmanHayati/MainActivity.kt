package com.anushka.coroutinesRadmanHayati

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    lateinit var job1: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*   CoroutineScope(Dispatchers.IO).launch {
               Log.i("MyTag", "calculation just started")
               var stock1 = async { getStuck1() }
               var stock2 = async { getStuck2() }
              val total = stock1.await() + stock2.await()
               Log.i("MyTag", "total is $total")
           }*/
        job1 = CoroutineScope(Dispatchers.Main).launch {
            downloadData()
        }
        cancelButton.setOnClickListener(){
            job1.cancel()
        }
        statusButton.setOnClickListener(){
            if (job1.isActive){
                textView.text="active"
            }else if (job1.isCancelled){
                textView.text="canceled"
            }else if (job1.isCompleted){
                textView.text="completed"
            }
        }
    }

    private suspend fun downloadData() {
        withContext(Dispatchers.IO) {
            repeat(30) {
                delay(1000)
                Log.i("MYTAG", "repeating $it")
            }
        }
    }

}
/*
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
*/


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

