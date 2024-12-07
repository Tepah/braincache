package com.example.braincache

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

data class User (
    val _id: String? = null,
    val username: String,
    val password: String
)

data class Quiz(
    val _id: String? = null,
    val title: String,
    val userID: String,
    val questions: List<String>,
    val answers: List<String>,
)

interface ApiService {
    @POST("/users")
    fun createUser(@Body user: User): Call<User>

    @POST("/login")
    fun loginUser(@Body user: User): Call<List<User>>

    @GET("/users/{userid}")
    fun getUser(@Path("userid") id: String): Call<List<User>>

    @GET("/quizzes")
    fun getAllQuizzes(): Call<List<Quiz>>

    @GET("/quizzes/{userID}")
    fun getQuizzesByUser(@Path("userID") userID: String): Call<List<Quiz>>

    @POST("quizzes")
    fun createQuiz(@Body quiz: Quiz): Call<Quiz>

    @DELETE("/quizzes/{quizID}")
    fun deleteQuiz(@Path("quizID") quizID: String): Call<Quiz>
}