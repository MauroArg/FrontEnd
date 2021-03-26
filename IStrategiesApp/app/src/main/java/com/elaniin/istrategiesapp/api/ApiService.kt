package com.elaniin.istrategiesapp.api

import com.elaniin.istrategiesapp.model.account.Account
import com.elaniin.istrategiesapp.model.account.AccountResponse
import com.elaniin.istrategiesapp.model.account.AccountsResponse
import com.elaniin.istrategiesapp.model.user.LoginBody
import com.elaniin.istrategiesapp.model.user.LoginResponse
import com.elaniin.istrategiesapp.model.user.User
import com.elaniin.istrategiesapp.model.user.UserResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface ApiService {
    ///Login
    @POST("Session")
    @Headers("Content-Type: application/json")
    suspend fun login(@Body body: LoginBody): LoginResponse

    @POST("User")
    @Headers("Content-Type: application/json")
    suspend fun createUser(@Body body: User): UserResponse

    @GET("AccountByUser")
    suspend fun getAccountByUser(@Query("id") id: Long): AccountsResponse

    @POST("Account")
    @Headers("Content-Type: application/json")
    suspend fun addAccount(@Body body: Account): AccountResponse
}
private var retrofit = Retrofit.Builder()
    .baseUrl("http://teststrategies.somee.com/api/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

var service: ApiService = retrofit.create(ApiService::class.java)