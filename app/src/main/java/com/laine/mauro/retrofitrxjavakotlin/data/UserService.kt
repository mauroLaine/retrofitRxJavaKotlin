package com.laine.mauro.retrofitrxjavakotlin.data

import com.laine.mauro.retrofitrxjavakotlin.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface UserService {

    @GET("/users")
    fun getUsers(): Call<List<Users.User>>

}