package com.laine.mauro.retrofitrxjavakotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.laine.mauro.retrofitrxjavakotlin.data.GitHubServiceGenerator
import com.laine.mauro.retrofitrxjavakotlin.data.UserService
import com.laine.mauro.retrofitrxjavakotlin.model.Users
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        load_btn.setOnClickListener {
            getData()
        }
    }

    fun getData() {
        val userService: UserService = GitHubServiceGenerator.createService(UserService::class.java)
        val call = userService.getUsers()

        call.enqueue(object : Callback<List<Users.User>> {
            override fun onFailure(call: Call<List<Users.User>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<Users.User>>, response: Response<List<Users.User>>) {
                val users = response.body()
                Log.d("####", "response: " + users?.size)
            }

        })
    }
}
