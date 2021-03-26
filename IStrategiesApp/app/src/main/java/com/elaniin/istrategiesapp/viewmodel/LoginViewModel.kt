package com.elaniin.istrategiesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.elaniin.istrategiesapp.api.ResponseStatus
import com.elaniin.istrategiesapp.database.getDatabase
import com.elaniin.istrategiesapp.model.user.LoginBody
import com.elaniin.istrategiesapp.model.user.Session
import com.elaniin.istrategiesapp.model.user.User
import com.elaniin.istrategiesapp.repository.LoginRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.UnknownHostException

class LoginViewModel(application: Application): AndroidViewModel(application) {
    val app = application

    private val database = getDatabase(application)

    private val repository = LoginRepository(database)

    private val _statusLogin = MutableLiveData<ResponseStatus>()
    val statusLogin: LiveData<ResponseStatus>
        get() = _statusLogin

    private val _statusSignup = MutableLiveData<ResponseStatus>()
    val statusSignup: LiveData<ResponseStatus>
        get() = _statusSignup

    fun login(email: String, pass: String){
        viewModelScope.launch {
            try {
                _statusLogin.value = ResponseStatus.LOADING
                var loginBody = LoginBody(email,pass)
                if(repository.login(loginBody)){
                    _statusLogin.value = ResponseStatus.DONE
                }
                else{
                    _statusLogin.value = ResponseStatus.REJECTED
                }

            } catch (e: UnknownHostException){
                _statusLogin.value = ResponseStatus.NO_INTERNET
            }catch (e: Exception){
                _statusLogin.value = ResponseStatus.ERROR
            }
        }
    }

    fun signup(email: String, name: String, pass: String){
        viewModelScope.launch {
            try {
                _statusSignup.value = ResponseStatus.LOADING
                var user = User(0, email, name, pass)
                if(repository.createUser(user)){
                    _statusSignup.value = ResponseStatus.DONE
                }
                else{
                    _statusSignup.value = ResponseStatus.REJECTED
                }
            } catch (e: UnknownHostException){
                _statusSignup.value = ResponseStatus.NO_INTERNET
            } catch (e: Exception){
                _statusSignup.value = ResponseStatus.ERROR
            }
        }
    }
}