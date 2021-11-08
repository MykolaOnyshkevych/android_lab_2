package com.example.lab2

class EmailValidator {
    fun validate(email: String): Boolean {
        if (email?.isNullOrEmpty()) {
            throw IllegalStateException("Email is not validated")
        } else if (!isEmailValid(email)) {
            throw IllegalStateException("The email is incorrect")
        }
        return true
    }

    fun isEmailValid(emailConfig: String?): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailConfig).matches()
    }
}
