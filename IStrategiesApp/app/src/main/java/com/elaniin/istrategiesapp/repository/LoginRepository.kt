package com.elaniin.istrategiesapp.repository

import android.util.Log
import com.elaniin.istrategiesapp.api.service
import com.elaniin.istrategiesapp.database.AppDatabase
import com.elaniin.istrategiesapp.model.user.LoginBody
import com.elaniin.istrategiesapp.model.user.LoginResponse
import com.elaniin.istrategiesapp.model.user.Session
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository (private val database: AppDatabase) {

    val session = database.sessionDao.getSession()

    suspend fun createSession(id: Long){
        return withContext(Dispatchers.IO){
            val session:Session = Session(id)

            database.sessionDao.insert(session)
        }
    }

    suspend fun login(body: LoginBody){
        return withContext(Dispatchers.IO){
            var res = service.login(body)
            var session : Session = Session(res.id)
            database.sessionDao.delete()
            database.sessionDao.insert(session)
        }
    }
}