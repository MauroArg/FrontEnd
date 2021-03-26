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
import com.elaniin.istrategiesapp.repository.AccountRepository
import com.elaniin.istrategiesapp.repository.LoginRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.UnknownHostException

class AccountViewModel(application: Application): AndroidViewModel(application) {
    val app = application

    private val database = getDatabase(application)

    private val repository = AccountRepository(database)

    private val _statusGet = MutableLiveData<ResponseStatus>()
    val statusGet: LiveData<ResponseStatus>
        get() = _statusGet

    private var _userLiveData: MutableLiveData<Session>? = MutableLiveData()

    val userLiveData: LiveData<Session>?
        get() = _userLiveData

    fun GetAccount(id: Long){
        viewModelScope.launch {
            try {
                _statusGet.value = ResponseStatus.LOADING
                if(repository.getAccount(id)){
                    _statusGet.value = ResponseStatus.DONE
                }
                else{
                    _statusGet.value = ResponseStatus.REJECTED
                }

            } catch (e: UnknownHostException){
                _statusGet.value = ResponseStatus.NO_INTERNET
            }catch (e: Exception){
                _statusGet.value = ResponseStatus.ERROR
            }
        }
    }

    fun logout(){
        destroySession()
        _userLiveData?.value = null
    }

    private fun destroySession(){
        viewModelScope.launch {
            repository.logout()
        }
    }

    val acList = repository.accounts
}