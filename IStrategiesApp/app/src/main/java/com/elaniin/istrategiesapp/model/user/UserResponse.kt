package com.elaniin.istrategiesapp.model.user

data class UserResponse(val code: Int,
                    val message: String,
                    val user: User) {
}