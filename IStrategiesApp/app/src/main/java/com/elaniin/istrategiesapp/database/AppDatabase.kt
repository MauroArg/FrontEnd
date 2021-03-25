package com.elaniin.istrategiesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elaniin.istrategiesapp.model.account.Account
import com.elaniin.istrategiesapp.model.user.Session

@Database(entities = [Session::class, Account::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract val sessionDao: SessionDao
    abstract val accountDao: AccountDao
}

private lateinit var INSTANCE:AppDatabase

fun getDatabase(context: Context):AppDatabase{
    synchronized(AppDatabase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "db")
                .fallbackToDestructiveMigration()
                .build()
        }
        return INSTANCE
    }
}