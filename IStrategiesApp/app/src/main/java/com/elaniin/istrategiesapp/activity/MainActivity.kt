package com.elaniin.istrategiesapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elaniin.istrategiesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}