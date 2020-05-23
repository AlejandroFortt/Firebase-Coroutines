package com.fortatic.apps.io.firebasecoroutines

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private const val PATH = "server/test"

abstract class FirebaseCallbackAPI {

    fun getDataFromFirebase() {
        FirebaseDatabase.getInstance().getReference(PATH)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    onSuccess(dataSnapshot.value.toString())
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    onError(databaseError.toException())
                }
            })
    }

    protected abstract fun onSuccess(data: String)
    protected abstract fun onError(error: Throwable)
}