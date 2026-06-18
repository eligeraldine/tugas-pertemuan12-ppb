package com.loginpage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.loginpage.model.UserDatabase
import com.loginpage.model.UserRepository
import com.loginpage.ui.theme.LoginpageTheme
import com.loginpage.view.LoginScreen
import com.loginpage.viewmodel.LoginViewModel
import com.loginpage.viewmodel.LoginViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = UserDatabase.getDatabase(applicationContext)
        val repository = UserRepository(database.userDao())
        val factory = LoginViewModelFactory(repository)
        val viewModel: LoginViewModel by viewModels { factory }

        setContent {
            LoginpageTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    LoginScreen(viewModel)
                }
            }
        }
    }
}