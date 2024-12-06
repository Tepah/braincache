package com.example.braincache

import android.content.ContentValues.TAG
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:3000"

    val instance: ApiService by lazy {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}

fun createUserData(user: User, callback: (String) -> Unit) {
    val call = RetrofitClient.instance

    call.createUser(user).enqueue(object: Callback<User> {
        override fun onResponse(call: Call<User>, response: Response<User>) {
            if (response.isSuccessful) {
                val createdUser = response.body()
                Log.v(TAG, "User created: $createdUser")
            } else {
                Log.e(TAG, "Failed to create user")
            }
        }

        override fun onFailure(call: Call<User>, t: Throwable) {
            Log.e(TAG, "Failed to create user", t)
        }
    })
}

