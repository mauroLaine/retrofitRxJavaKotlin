package com.laine.mauro.retrofitrxjavakotlin.data

import com.laine.mauro.retrofitrxjavakotlin.model.Users
import retrofit2.http.GET
import rx.Observable

interface UserServiceRx {

    @GET("/users")
    fun getUsers(): Observable<List<Users.User>>

}