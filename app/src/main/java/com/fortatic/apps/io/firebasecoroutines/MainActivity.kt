package com.fortatic.apps.io.firebasecoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Usando Callbacks
        /*val mediatorCallback = MediatorCallback() { result, error ->
            handleResult(result, error)
        }
        mediatorCallback.getDataFromFirebase()*/

        //Usando Coroutinas.
        CoroutineScope(Dispatchers.IO).launch {
            val data = MediatorCoroutine.getDataFromNetworkAsync().await()
            Log.d("FATAL", "Thread:${Thread.currentThread().name}, Result: $data")
        }

    }

    private fun handleResult(result: String?, error: Throwable?) {
        if (error == null) {
            Log.d("FATAL", "Thread:${Thread.currentThread().name}, onSucces:$result")
        } else {
            Log.d("FATAL", "Thread:${Thread.currentThread().name}, onError:${error.message}")
        }
    }

}
