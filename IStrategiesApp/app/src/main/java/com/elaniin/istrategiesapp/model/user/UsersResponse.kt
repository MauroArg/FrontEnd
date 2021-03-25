package com.elaniin.istrategiesapp.model.user

data class UsersResponse(val code: Int,
                    val message: String,
                    val user: MutableList<User>) {
}