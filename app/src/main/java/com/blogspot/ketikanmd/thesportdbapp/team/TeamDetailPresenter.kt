package com.blogspot.ketikanmd.thesportdbapp.team

import com.blogspot.ketikanmd.thesportdbapp.ApiRepository
import com.blogspot.ketikanmd.thesportdbapp.TeamResponse
import com.blogspot.ketikanmd.thesportdbapp.TheSportDBApi
import com.blogspot.ketikanmd.thesportdbapp.match.TeamView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamDetailPresenter(
        private val view: TeamView,
        private val apiRepo: ApiRepository,
        private val gson: Gson
) {

    fun getDetaailTeam(teamId: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepo
                    .doRequest(TheSportDBApi.getTeam(teamId)),
                    TeamResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showTeam(data.teamsItems!!)
            }
        }
    }
}

