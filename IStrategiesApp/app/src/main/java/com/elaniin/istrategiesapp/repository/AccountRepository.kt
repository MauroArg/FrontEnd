package com.elaniin.istrategiesapp.repository

import android.util.Log
import com.elaniin.istrategiesapp.api.service
import com.elaniin.istrategiesapp.database.AppDatabase
import com.elaniin.istrategiesapp.model.account.Account
import com.elaniin.istrategiesapp.model.account.AccountsResponse
import com.elaniin.istrategiesapp.model.user.Session
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountRepository (private val database: AppDatabase){
    val accounts = database.accountDao.getAccounts()

    suspend fun getAccount(id: Long): Boolean{
        try {
                var answer: Boolean
                val co = withContext(Dispatchers.IO){
                    var res = service.getAccountByUser(id)
                    answer = if(res.code == 0){
                        var account = parseResponse(res)
                        database.accountDao.delete()
                        database.accountDao.insert(account)
                        true
                    } else{
                        false
                    }
                }
                return answer
        }
        catch (th: Throwable){
            Log.e("ERROR: ", th.message.toString())
            return false
        }
    }

    suspend fun logout(){
        return withContext(Dispatchers.IO){
            database.sessionDao.delete()
            database.accountDao.delete()
        }
    }

    private fun parseResponse(response: AccountsResponse): MutableList<Account>{
        val account = mutableListOf<Account>()

        for(res in response.account){
            val name = res.name
            val id = res.account_id
            val user = res.user_id
            Log.e("ACCOUNT: ", name)
            account.add(Account(id,name,user))
        }
        return  account
    }

}