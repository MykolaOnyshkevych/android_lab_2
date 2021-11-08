package com.example.lab2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SignInFragment.SignUpListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, SignInFragment.newInstance())
            .commit()
    }

    override fun onSingUpClicked() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, SignUpFragment.newInstance())
            .commit()
    }
}
