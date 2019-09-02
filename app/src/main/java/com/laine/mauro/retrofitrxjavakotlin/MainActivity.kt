package com.laine.mauro.retrofitrxjavakotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.laine.mauro.retrofitrxjavakotlin.data.GitHubServiceGenerator
import com.laine.mauro.retrofitrxjavakotlin.data.UserServiceRx
import com.laine.mauro.retrofitrxjavakotlin.model.Users
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable = CompositeDisposable()
        load_btn.setOnClickListener {
            try {
                Thread.sleep(5000)
                getData()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    fun getData() {
        val userService: UserServiceRx = GitHubServiceGenerator.createService(UserServiceRx::class.java)
        compositeDisposable.add(
            userService.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse)
        )
    }

    private fun handleResponse(users: List<Users.User>) {
        for (user in users) {
            Log.d("####", user.url)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("####", "destroy")
        compositeDisposable.dispose()
    }
}
