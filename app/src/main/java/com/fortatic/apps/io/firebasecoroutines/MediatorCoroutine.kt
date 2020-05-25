package com.fortatic.apps.io.firebasecoroutines

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

object MediatorCoroutine : CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    @ExperimentalCoroutinesApi
    fun getDataFromNetworkAsync() = async {
        FirebaseCoroutinesAPI.getDataFromFirebase()
    }

    fun release() {
        this.job.cancel()
    }
}