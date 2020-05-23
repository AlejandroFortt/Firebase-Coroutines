package com.fortatic.apps.io.firebasecoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mediatorCallback = MediatorCallback() { result, error ->
            handleResult(result, error)
        }

        mediatorCallback.getDataFromFirebase()

    }

    private fun handleResult(result: String?, error: Throwable?) {
        if (error == null) {
            Log.d("FATAL", "Thread:${Thread.currentThread().name}, onSucces:$result")
        } else {
            Log.d("FATAL", "Thread:${Thread.currentThread().name}, onError:${error.message}")
        }
    }

}
