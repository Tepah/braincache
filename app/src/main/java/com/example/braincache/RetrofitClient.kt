package com.example.braincache

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
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
                callback(createdUser?._id ?: "")
            } else {
                Log.e(TAG, "Failed to create user")
            }
        }

        override fun onFailure(call: Call<User>, t: Throwable) {
            Log.e(TAG, "Failed to create user", t)
        }
    })
}

fun getUserData(id: String, callback: (List<User>) -> Unit) {
    val call = RetrofitClient.instance

    call.getUser(id).enqueue(object: Callback<List<User>> {
        override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
            if (response.isSuccessful) {
                val user = response.body()
                Log.v(TAG, "User retrieved: $user")
                if (user != null) {
                    callback(user)
                } else {
                    Log.e(TAG, "Failed to retrieve user")
                }
            } else {
                Log.e(TAG, "Failed to retrieve user")
            }
        }

        override fun onFailure(call: Call<List<User>>, t: Throwable) {
            Log.e(TAG, "Failed to retrieve user", t)
        }
    })
}

fun loginUser(userData: User, callback: (List<User>) -> Unit) {
    val call = RetrofitClient.instance

    call.loginUser(userData).enqueue(object: Callback<List<User>> {
        override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
            if (response.isSuccessful) {
                val user = response.body()
                Log.v(TAG, "User retrieved: $user")
                if (user != null) {
                    callback(user)
                } else {
                    Log.e(TAG, "Failed to retrieve user")
                }
            } else {
                Log.e(TAG, "Failed to retrieve user")
            }
        }

        override fun onFailure(call: Call<List<User>>, t: Throwable) {
            Log.e(TAG, "Failed to retrieve user", t)
            callback(emptyList())
        }
    })
}

fun getAllQuizzes(callback: (List<Quiz>) -> Unit) {
    val call = RetrofitClient.instance

    call.getAllQuizzes().enqueue(object: Callback<List<Quiz>> {
        override fun onResponse(call: Call<List<Quiz>>, response: Response<List<Quiz>>) {
            if (response.isSuccessful) {
                val quizzes = response.body()
                Log.v(TAG, "Quizzes retrieved: $quizzes")
                if (quizzes != null) {
                    callback(quizzes)
                } else {
                    Log.e(TAG, "Failed to retrieve quizzes")
                }
            } else {
                Log.e(TAG, "Failed to retrieve quizzes")
            }
        }

        override fun onFailure(call: Call<List<Quiz>>, t: Throwable) {
            Log.e(TAG, "Failed to retrieve quizzes", t)
        }
    })
}

fun getQuizzesByUser(userID: String, callback: (List<Quiz>) -> Unit ) {
    val call = RetrofitClient.instance

    call.getQuizzesByUser(userID).enqueue(object: Callback<List<Quiz>> {
        override fun onResponse(call: Call<List<Quiz>>, response: Response<List<Quiz>>) {
            if (response.isSuccessful) {
                val quizzes = response.body()
                Log.v(TAG, "Quizzes retrieved: $quizzes")
                if (quizzes != null) {
                    callback(quizzes)
                } else {
                    Log.e(TAG, "Failed to retrieve quizzes")
                }
            }
        }

        override fun onFailure(call: Call<List<Quiz>>, t: Throwable) {
            Log.e(TAG, "Failed to retrieve quizzes", t)
        }
    })
}

fun createQuiz(quiz:Quiz, callback: (Quiz) -> Unit) {
    val call = RetrofitClient.instance

    call.createQuiz(quiz).enqueue(object: Callback<Quiz> {
        override fun onResponse(call: Call<Quiz>, response: Response<Quiz>) {
            if (response.isSuccessful) {
                val createdQuiz = response.body()
                Log.v(TAG, "Quiz created: $createdQuiz")
                if (createdQuiz != null) {
                    callback(createdQuiz)
                }
            } else {
                Log.e(TAG, "Failed to create quiz")
            }
        }

        override fun onFailure(call: Call<Quiz>, t: Throwable) {
            Log.e(TAG, "Failed to create quiz", t)
        }
    })
}

fun deleteQuiz(quizID: String, callback: (Quiz) -> Unit) {
    val call = RetrofitClient.instance

    call.deleteQuiz(quizID).enqueue(object: Callback<Quiz> {
        override fun onResponse(call: Call<Quiz>, response: Response<Quiz>) {
            if (response.isSuccessful) {
                val deletedQuiz = response.body()
                Log.v(TAG, "Quiz deleted: $deletedQuiz")
                if (deletedQuiz != null) {
                    callback(deletedQuiz)
                }
            } else {
                Log.e(TAG, "Failed to delete quiz")
            }
        }

        override fun onFailure(call: Call<Quiz>, t: Throwable) {
            Log.e(TAG, "Failed to delete quiz", t)
        }
    })
}