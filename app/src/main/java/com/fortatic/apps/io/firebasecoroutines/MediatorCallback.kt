package com.fortatic.apps.io.firebasecoroutines

class MediatorCallback(
    private val callbackAPI: (result: String?, error: Throwable?) -> Unit
) : FirebaseCallbackAPI() {
    override fun onSuccess(data: String) {
        callbackAPI(data, null)
    }

    override fun onError(error: Throwable) {
        callbackAPI(null, error)
    }
}