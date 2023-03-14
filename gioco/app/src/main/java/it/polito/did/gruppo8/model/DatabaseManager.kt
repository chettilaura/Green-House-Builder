package it.polito.did.gruppo8.model

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DatabaseManager(private val scope: CoroutineScope) {

    private val url = "https://gioco8-32e43-default-rtdb.europe-west1.firebasedatabase.app/"
    private val firebase = Firebase.database(url)
    private val auth = Firebase.auth

    /**
     * Authenticate anonymously to the database.
     */
    fun authenticate(){
        scope.launch {
            auth.signInAnonymously().await()
            Log.d("DatabaseManager", "Current User: ${auth.uid}")
            delay(500)
        }
    }

    /**
     * Retrieve the ID of the current user authenticated to the database.
     *
     * @return a String with the user ID, that can be null if there's no user authenticated.
     */
    fun getCurrentUserID(): String? {
        return auth.uid
    }

    // Reference for read/write operations: https://firebase.google.com/docs/database/android/read-and-write -Mattia
    /**
     * Write a data of type T into the database, at a specific path.
     *
     * Note: in order to properly serialize data, type T must be a class with a default constructor
     * that takes no arguments and no private fields.
     *
     * @param path the destination path to write.
     * @param data the data to write.
     * @param T the data type.
     */
    fun <T> writeData(path: String, data: T){
        firebase.getReference(path).setValue(data)
        Log.d("DatabaseManager", "Data $data written at path $path")
    }

    /**
     * Read a specific data once from the database.
     *
     * @param path the data to read with its path in the database.
     * @param valueType the class of the data requested (T.class).
     * @param T the data type.
     *
     * @return n object of type T that can be null if the read operation has failed.
     */
    fun <T> readData(path: String, valueType: Class<T>): T?{
        var value : T? = null
        firebase.getReference(path).get().addOnSuccessListener {
            value = it.getValue(valueType)
            Log.d("DatabaseManager", "Data $value read from path $path")
        }
        return value
    }

    //TODO: Find a way to implement addValueEventListener managing inside the class -Mattia
}