package com.example.lab2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputLayout

class SignInFragment : Fragment() {
    private var signInConfirmButton: Button? = null
    private var signUpForNoAccButton: Button? = null

    private var emailTextView: TextView? = null
    private var passwordTextView: TextView? = null
    private var passwordTextString: String? = null
    private var emailTextInputEditLayout: TextInputLayout? = null
    private var passwordTextInputEditLayout: TextInputLayout? = null
    private var congratText: TextView? = null
    private val viewModel by viewModels<SignInViewModel> { SignInViewModelFactory() }
    private var signUpListener: SignUpListener? = null
    // private var viewModel: SignUpViewModel? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SignUpListener) {
            signUpListener = context
        } else {
            throw IllegalStateException("Activity should implement SignUpListener")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailTextView = view.findViewById(R.id.sign_in_email)
        passwordTextView = view.findViewById(R.id.sign_in_password)
        emailTextInputEditLayout = view.findViewById(R.id.sign_in_email_layout)
        passwordTextInputEditLayout = view.findViewById(R.id.sign_in_password_layout)
        initViewModel()
        setupSignUpButton(view)
        confirmSignInButton(view)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }
    private fun initViewModel() {
        // viewModel = ViewModelProvider(this, SignUpViewModelFactory()).get(SignUpViewModel::class.java)
        viewModel.emailError.observe(viewLifecycleOwner) { error ->
            emailTextInputEditLayout?.error = error
            passwordTextInputEditLayout?.error = error
        }
        viewModel.passwordError.observe(viewLifecycleOwner) { error ->
            passwordTextInputEditLayout?.error = error
        }
        viewModel.user.observe(viewLifecycleOwner) {
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            SignInFragment().apply {
            }
    }
    interface SignUpListener {
        fun onSingUpClicked()
    }

    private fun confirmSignInButton(view: View) {
        signInConfirmButton = view.findViewById(R.id.sign_in_confirm_button)
        signInConfirmButton?.setOnClickListener {
            clearErrors()
            viewModel.signIn(
                emailTextView?.getText().toString(), passwordTextView?.getText().toString(),
                requireActivity()
            )
        }
    }
    private fun clearErrors() {
        emailTextInputEditLayout?.isErrorEnabled = false
        passwordTextInputEditLayout?.isErrorEnabled = false
    }
    private fun setupSignUpButton(view: View) {
        signUpForNoAccButton = view.findViewById(R.id.sign_up_for_no_account_button)
        signUpForNoAccButton?.setOnClickListener {
            signUpListener?.onSingUpClicked()
        }
    }
}
