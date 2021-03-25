package com.elaniin.istrategiesapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elaniin.istrategiesapp.model.user.Session

@Dao
interface SessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(us: Session)

    @Query("SELECT * FROM session")
    fun getSession(): LiveData<MutableList<Session>>

    @Query("DELETE FROM session")
    fun delete()
}