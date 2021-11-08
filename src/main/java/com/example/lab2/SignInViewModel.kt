package com.example.lab2

import android.app.Activity
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import timber.log.Timber

const val congratText = "ererererererer"
const val noncongratText = "vlad(("
const val PASSWORD_PATTERN = "[A-Za-z]{8,}\$"

class SignInViewModel(private val emailValidator: EmailValidator) : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    private val _emailError = MutableLiveData<String>()
    val emailError: LiveData<String>
        get() = _emailError

    private val _registrationError = MutableLiveData<String>()
    val registrationError: LiveData<String>
        get() = _registrationError

    val user = MutableLiveData<FirebaseUser>()

    val passwordError = MutableLiveData<String>()
    val passwordConfirmError = MutableLiveData<String>()
    val nameError = MutableLiveData<String>()

    fun signIn(email: String, password: String, context: Activity) {
        if (email != null && password != null) {
            authenticateUser(email, password, context)
        } else {
            Toast.makeText(context, noncongratText, Toast.LENGTH_SHORT).show()
        }
    }

    private fun authenticateUser(email: String, password: String, context: Activity) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    Timber.d("signInWithEmail:success")
                    user.value = auth.currentUser
                    // Toast.makeText(context, congratText, Toast.LENGTH_SHORT).show()
                } else {
                    Timber.w(task.exception, "signInWithEmail:failure")
                    _registrationError.value = task.exception?.message
                }
            }
    }

    fun isValidPassword(passwordConfig: String?): Boolean {
        passwordConfig?.let {
            val passwordPattern = "[A-Za-z]{8,}$"
            val passwordMatcher = Regex(passwordPattern)

            return passwordMatcher.find(passwordConfig) != null
        } ?: return false
    }
    fun isValidEmail(emailConfig: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(emailConfig).matches()
    }
}
