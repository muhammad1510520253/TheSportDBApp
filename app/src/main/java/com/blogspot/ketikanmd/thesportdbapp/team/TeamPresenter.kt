package com.blogspot.ketikanmd.thesportdbapp.match

import com.blogspot.ketikanmd.thesportdbapp.ApiRepository
import com.blogspot.ketikanmd.thesportdbapp.TeamResponse
import com.blogspot.ketikanmd.thesportdbapp.TheSportDBApi
import com.blogspot.ketikanmd.thesportdbapp.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamPresenter(
        private val view: TeamView,
        private val apiRepo: ApiRepository,
        private val gson: Gson,
        private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getListTeams(league: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepo
                        .doRequest(TheSportDBApi.getListTeam(league)),
                        TeamResponse::class.java
                )
            }
            view.showTeam(data.await().teamsItems)
            view.hideLoading()
        }
    }
}






