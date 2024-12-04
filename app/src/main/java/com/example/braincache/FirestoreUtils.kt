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
        val call = userService.createUser(apiKey, user)
        call.enqueue(object : retrofit2.Callback<FirestoreUserResponse>{
            override fun onResponse(call: Call<FirestoreUserResponse>, response: retrofit2.Response<FirestoreUserResponse>) {
                if (response.isSuccessful) {
                    callBack(response.body())
                } else {
                    Log.e(TAG, "Error: ${response.code()} - ${response.message()}")
                    callBack(null)
                }
            }
            override fun onFailure(call: Call<FirestoreUserResponse>, t: Throwable) {
                Log.e("FirestoreUtils", "Failed to create user data", t)
                callBack(null)
            }
        })
    }
}