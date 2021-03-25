package com.elaniin.istrategiesapp.model.account

data class AccountResponse(val code: Int,
                        val message: String,
                        val account: Account) {
}