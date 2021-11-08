package com.example.lab2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SignUpViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignUpViewModel(EmailValidator()) as T
    }
}
