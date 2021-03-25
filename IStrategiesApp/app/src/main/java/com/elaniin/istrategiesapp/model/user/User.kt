package com.elaniin.istrategiesapp.model.user

import androidx.room.Entity

@Entity(tableName = "session")
data class User (val id: Long,
            val name: String,
            val email: String,
            val pass: String){
}