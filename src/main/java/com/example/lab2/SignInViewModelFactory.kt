package com.example.lab2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SignInViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignInViewModel(EmailValidator()) as T
    }
}
