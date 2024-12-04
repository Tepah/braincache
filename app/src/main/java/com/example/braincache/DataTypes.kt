package com.example.braincache

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

data class User (
    val id: String? = null,
    val username: String,
)

data class Quiz(
    val id: String,
    val userId: Long,
    val questions: List<String>,
    val answers: List<String>,
)

interface UserService {
}