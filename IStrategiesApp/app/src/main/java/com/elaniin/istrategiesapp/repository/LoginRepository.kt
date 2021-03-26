package com.elaniin.istrategiesapp.repository

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.elaniin.istrategiesapp.api.service
import com.elaniin.istrategiesapp.database.AppDatabase
import com.elaniin.istrategiesapp.model.user.LoginBody
import com.elaniin.istrategiesapp.model.user.LoginResponse
import com.elaniin.istrategiesapp.model.user.Session
import com.elaniin.istrategiesapp.model.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository (private val database: AppDatabase) {

    val session = database.sessionDao.getSession()

    suspend fun login(body: LoginBody): Boolean{
        try{
            var answer : Boolean
            val co = withContext(Dispatchers.IO){
                var res = service.login(body)
                answer = if(res.code == 0){
                    var session : Session = Session(res.id)
                    Log.e("API", session.id.toString())
                    database.sessionDao.delete()
                    database.sessionDao.insert(session)
                    true
                } else{
                    false
                }
            }
            return answer
        }
        catch (th: Throwable){
            return false
        }
    }

    suspend fun createUser(body: User): Boolean{
        return try{
            var answer : Boolean
            val co = withContext(Dispatchers.IO){
                var res = service.createUser(body)
                 answer = res.code == 0
            }
            answer
        } catch (th: Throwable){
            false
        }
    }
}