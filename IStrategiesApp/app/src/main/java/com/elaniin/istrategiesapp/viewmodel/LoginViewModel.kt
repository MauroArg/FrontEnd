package com.elaniin.istrategiesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.elaniin.istrategiesapp.database.getDatabase
import com.elaniin.istrategiesapp.model.user.LoginBody
import com.elaniin.istrategiesapp.model.user.Session
import com.elaniin.istrategiesapp.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application) {
    val app = application

    private val database = getDatabase(application)

    private val repository = LoginRepository(database)

    fun login(email: String, pass: String){
        viewModelScope.launch {
            var loginBody = LoginBody(email,pass)
            repository.login(loginBody)
        }
    }
}