package com.example.braincache

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.example.braincache.PreferenceHelper


class MainActivity : AppCompatActivity() {
    private lateinit var signUpButton: Button
    private lateinit var createQuizButton: Button
    private lateinit var getAllQuizzesButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if the user is logged in
        if (!PreferenceHelper.isLoggedIn(this)) {
            navigateToLoginPage()
            return
        }
        Thread.sleep(6000)
        installSplashScreen()
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        this.signUpButton = this.findViewById(R.id.signUpButton)
        this.createQuizButton = this.findViewById(R.id.testCreateQuizButton)
        this.getAllQuizzesButton = this.findViewById(R.id.getQuizzesButton)

        setupOnClickListeners()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun navigateToLoginPage() {
        val intent = Intent(this, LoginPage::class.java)
        startActivity(intent)
        finish() // Close MainActivity so the user cannot return using the back button
    }

    private fun setupOnClickListeners() {

        this.signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpPage::class.java)
            startActivity(intent)
            createUserData(User(null, "test", "test")) { id ->
                println("User created with id: $id")
            }
        }

        this.createQuizButton.setOnClickListener {
            val questions = listOf("What is 1+1?", "What is 2+2?")
            val answers = listOf("2", "Paris")
            createQuiz(Quiz(null, "Test", "1", questions, answers)) { quiz ->
                println("Quiz created with id: ${quiz._id}")
            }
        }

        this.getAllQuizzesButton.setOnClickListener {
            getAllQuizzes { quizzes ->
                println("Quizzes retrieved: $quizzes")
            }
        }
    }
}

