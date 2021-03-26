package com.elaniin.istrategiesapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elaniin.istrategiesapp.model.account.Account

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(regs: MutableList<Account>)

    @Query("SELECT * FROM account")
    fun getAccounts(): LiveData<MutableList<Account>>

    @Query("DELETE FROM account")
    fun delete()
}