package com.elaniin.istrategiesapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elaniin.istrategiesapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}