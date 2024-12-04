package com.example.braincache

import android.content.ContentValues.TAG
import android.util.Log
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FirestoreUtils {
    private const val BASE_URL = "https://firestore.googleapis.com/"
    private const val apiKey = "AIzaSyCBL5IMF6aphrnLF73o_pvZ9IYFuGdA1xA"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val userService: UserService = retrofit.create(UserService::class.java)

    fun createUserData(user: User, callBack: (FirestoreUserResponse?) -> Unit) {

    }
}