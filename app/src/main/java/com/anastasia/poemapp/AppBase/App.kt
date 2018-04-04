package com.anastasia.poemapp.AppBase
import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.net.ConnectivityManager
import io.paperdb.Paper


class App : Application(){
    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
    }


    fun isOnline(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}
