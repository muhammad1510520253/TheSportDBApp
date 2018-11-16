package com.blogspot.ketikanmd.thesportdbapp.player

import com.blogspot.ketikanmd.thesportdbapp.ApiRepository
import com.blogspot.ketikanmd.thesportdbapp.PlayerResponse
import com.blogspot.ketikanmd.thesportdbapp.TheSportDBApi
import com.blogspot.ketikanmd.thesportdbapp.match.PlayerView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PlayerDetailPresenter (
        private val view: PlayerView,
        private val apiRepo: ApiRepository,
        private val gson: Gson
) {

    fun getDetailPlayer(playerId: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepo
                    .doRequest(TheSportDBApi.getPlayerDetail(playerId)),
                    PlayerResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showPlayers(data.playersItems!!)
            }
        }
    }
}