package com.fortatic.apps.io.firebasecoroutines

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

private const val PATH = "server/test"

object FirebaseCoroutinesAPI {

    @ExperimentalCoroutinesApi
    suspend fun getDataFromFirebase() : String = suspendCancellableCoroutine { continuation ->
        continuation.context[Job]?.invokeOnCompletion {
            Log.d("FATAL", "invokeOnCompletion")
            //Haga cualquier tarea de limpieza aquí si es necesario
        }

        FirebaseDatabase.getInstance().getReference(PATH)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    continuation.resume(dataSnapshot.value.toString()){}
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    continuation.resumeWithException(databaseError.toException())
                }
            })
    }
}