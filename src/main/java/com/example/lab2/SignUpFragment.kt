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

class SignUpFragment : Fragment() {
    private var goHomeSuButton: Button? = null
    private var signUpConfirmButton: Button? = null

    private var emailTextView: TextView? = null
    private var passwordTextView: TextView? = null
    private var nameTextView: TextView? = null
    private var passwordConfirmTextView: TextView? = null

    private var emailTextInputEditLayout: TextInputLayout? = null
    private var passwordTextInputEditLayout: TextInputLayout? = null
    private var nameTextInputEditLayout: TextInputLayout? = null
    private var passwordConfirmTextInputEditLayout: TextInputLayout? = null
    private var signUpListener: SignUpFragment.SignUpListener? = null
    private val viewModel by viewModels<SignUpViewModel> { SignUpViewModelFactory() }
    private var congratText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    interface SignUpListener {
        fun onSingUpClicked()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SignUpFragment.SignUpListener) {
            signUpListener = context
        } else {
            throw IllegalStateException("Activity should implement SignUpListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailTextView = view.findViewById(R.id.sign_up_email)
        passwordTextView = view.findViewById(R.id.sign_up_password)
        passwordConfirmTextView = view.findViewById(R.id.sign_up_confirm_password)
        nameTextView = view.findViewById(R.id.sign_up_name)
        emailTextInputEditLayout = view.findViewById(R.id.sign_in_email_layout)
        passwordTextInputEditLayout = view.findViewById(R.id.sign_in_password_layout)
        nameTextInputEditLayout = view.findViewById(R.id.sign_up_name_layout)
        passwordConfirmTextInputEditLayout = view.findViewById(R.id.sign_up_confirm_password_layout)
        initViewModel()
        confirmSignUpButton(view)
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
        viewModel.passwordConfirmError.observe(viewLifecycleOwner) { error ->
            passwordTextInputEditLayout?.error = error
        }
        viewModel.nameError.observe(viewLifecycleOwner) { error ->
            passwordTextInputEditLayout?.error = error
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SignUpFragment().apply {
            }
    }
    private fun confirmSignUpButton(view: View) {
        signUpConfirmButton = view.findViewById(R.id.sign_in_confirm_button)
        signUpConfirmButton?.setOnClickListener {
            clearErrors()
            viewModel.signUp(
                emailTextView?.getText().toString(), passwordTextView?.getText().toString(),
                passwordConfirmTextView?.getText().toString(), nameTextView?.getText().toString(), requireActivity()
            )
        }
    }
    private fun clearErrors() {
        emailTextInputEditLayout?.isErrorEnabled = false
        passwordTextInputEditLayout?.isErrorEnabled = false
    }
}
