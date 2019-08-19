package com.laine.mauro.retrofitrxjavakotlin.model

data class Users(val response: List<User>) {
    inner class User(val login: String, val id: Int, val url: String, val blog: String, val created_at: String)
}


