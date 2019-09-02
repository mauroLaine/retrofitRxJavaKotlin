package com.laine.mauro.retrofitrxjavakotlin.data

import com.laine.mauro.retrofitrxjavakotlin.model.Users
import io.reactivex.Observable
import retrofit2.http.GET

interface UserServiceRx {

    @GET("/users")
    fun getUsers(): Observable<List<Users.User>>

}