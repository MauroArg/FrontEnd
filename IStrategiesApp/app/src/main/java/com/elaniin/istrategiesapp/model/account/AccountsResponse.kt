package com.elaniin.istrategiesapp.model.account

data class AccountsResponse(val code: Int,
                        val message: String,
                        val account: MutableList<Account>) {
}