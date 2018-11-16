package com.blogspot.ketikanmd.thesportdbapp.match

import com.blogspot.ketikanmd.thesportdbapp.ApiRepository
import com.blogspot.ketikanmd.thesportdbapp.TheSportDBApi
import com.blogspot.ketikanmd.thesportdbapp.PlayerResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PlayerPresenter(
        private val view: PlayerView,
        private val apiRepo: ApiRepository,
        private val gson: Gson
) {

    fun getPlayer(teamId:String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepo
                    .doRequest(TheSportDBApi.getPlayer(teamId)),
                    PlayerResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showPlayers(data.playersItems)
            }
        }
    }

}



