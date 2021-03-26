package com.elaniin.istrategiesapp.model.user

import androidx.room.Entity

@Entity(tableName = "session")
data class User (val id: Long,
            val email: String,
            val name: String,
            val pass: String){
}