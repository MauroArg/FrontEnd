package com.elaniin.istrategiesapp.api

import com.elaniin.istrategiesapp.model.user.LoginBody
import com.elaniin.istrategiesapp.model.user.LoginResponse
import com.elaniin.istrategiesapp.model.user.User
import com.elaniin.istrategiesapp.model.user.UserResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    ///Login
    @POST("Session")
    @Headers("Content-Type: application/json")
    suspend fun login(@Body body: LoginBody): LoginResponse

    @POST("User")
    @Headers("Content-Type: application/json")
    suspend fun createUser(@Body body: User): UserResponse
}
private var retrofit = Retrofit.Builder()
    .baseUrl("http://teststrategies.somee.com/api/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

var service: ApiService = retrofit.create(ApiService::class.java)