package com.example.braincache

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var loginInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var loginButton: Button
    private lateinit var signUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        this.loginInput = this.findViewById(R.id.usernameInput)
        this.passwordInput = this.findViewById(R.id.passwordInput)
        this.loginButton = this.findViewById(R.id.loginButton)
        this.signUpButton = this.findViewById(R.id.signUpButton)

        setupOnClickListeners()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupOnClickListeners() {
        // Setup OnClick for login button
        this.loginButton.setOnClickListener {
            val txt = this.loginInput.text
            val user = User(null, username = txt.toString(), password = "")
            println("Username: $txt clicked!")
        }

        this.signUpButton.setOnClickListener {
            // Set up code to change the view to the sign up screen
            createUserData(User(null, "test", "test")) { id ->
                println("User created with id: $id")
            }
        }
    }
}