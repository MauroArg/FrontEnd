package com.elaniin.istrategiesapp.model.account

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class Account(@PrimaryKey val account_id: Long,
                   val name: String,
                   val user_id: Long) {
}