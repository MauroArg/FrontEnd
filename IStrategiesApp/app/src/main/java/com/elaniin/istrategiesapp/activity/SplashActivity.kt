package com.elaniin.istrategiesapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.elaniin.istrategiesapp.database.AppDatabase
import com.elaniin.istrategiesapp.database.getDatabase
import com.elaniin.istrategiesapp.databinding.ActivitySplashBinding
import com.elaniin.istrategiesapp.model.user.Session
import com.elaniin.istrategiesapp.repository.LoginRepository

class SplashActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var repository: LoginRepository
    private lateinit var session: LiveData<MutableList<Session>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = getDatabase(application)
        repository = LoginRepository(database)
        session = repository.session

        startApp()
    }

    private fun startApp(){
        Handler(Looper.getMainLooper()).postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            session.observe(this, Observer { session ->
                if (session.count() > 0) {
                    Log.e("USER", session.elementAt(0).id.toString())
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("id", session.elementAt(0).id)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })
        }, 1500)
    }
}