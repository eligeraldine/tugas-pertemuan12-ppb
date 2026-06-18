package com.loginpage.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.loginpage.model.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var loginMessage by mutableStateOf<String?>(null)

    init {
        viewModelScope.launch {
            repository.insertDummyUser()
        }
    }

    fun onLoginClick() {
        if (username.isBlank() || password.isBlank()) {
            loginMessage = "Username dan Password tidak boleh kosong!"
            return
        }

        viewModelScope.launch {
            val isSuccess = repository.login(username, password)
            loginMessage = if (isSuccess) {
                "Login Berhasil!"
            } else {
                "Login Gagal: Username atau Password salah."
            }
        }
    }

    fun clearMessage() {
        loginMessage = null
    }
}

class LoginViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}