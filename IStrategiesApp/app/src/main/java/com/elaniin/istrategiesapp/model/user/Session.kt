package com.elaniin.istrategiesapp.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "session")
data class Session(@PrimaryKey val id: Long) {
}