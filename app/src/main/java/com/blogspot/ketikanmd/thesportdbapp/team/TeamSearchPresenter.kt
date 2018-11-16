package com.blogspot.ketikanmd.thesportdbapp.match

import com.blogspot.ketikanmd.thesportdbapp.ApiRepository
import com.blogspot.ketikanmd.thesportdbapp.TeamResponse
import com.blogspot.ketikanmd.thesportdbapp.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamSearchPresenter(
        private val view: TeamView,
        private val apiRepo: ApiRepository,
        private val gson: Gson
) {

    fun getSearchTeam(team: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepo
                    .doRequest(TheSportDBApi.getSearchTeam(team)),
                    TeamResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showTeam(data.teamsItems)
            }
        }
    }

}



