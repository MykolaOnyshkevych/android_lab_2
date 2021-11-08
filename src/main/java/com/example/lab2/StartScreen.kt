package com.example.lab2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class StartScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.start_screen, StartScreenFragment.newInstance())
            .commit()
    }
}
