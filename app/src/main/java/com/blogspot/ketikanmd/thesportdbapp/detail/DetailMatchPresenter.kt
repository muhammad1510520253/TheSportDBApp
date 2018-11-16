package com.blogspot.ketikanmd.thesportdbapp.detail.detail.detailMatch


import com.blogspot.ketikanmd.thesportdbapp.ApiRepository
import com.blogspot.ketikanmd.thesportdbapp.DetailResponse
import com.blogspot.ketikanmd.thesportdbapp.TeamResponse
import com.blogspot.ketikanmd.thesportdbapp.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailMatchPresenter(
        private val view: DetailMatchView,
        private val apiRepo: ApiRepository,
        private val gson: Gson
) {

    fun loadDMatchDetail(homeId: String?, awayId: String?, matchId: String?) {
        view.showLoading()

        doAsync {
            val reqDetail = apiRepo.doRequest(TheSportDBApi.getDetail(matchId))
            val reqTeamHome = apiRepo.doRequest(TheSportDBApi.getTeam(homeId))
            val reqTeamAway = apiRepo.doRequest(TheSportDBApi.getTeam(awayId))
            val detail = gson.fromJson(reqDetail, DetailResponse::class.java)
            val home = gson.fromJson(reqTeamHome, TeamResponse::class.java)
            val away = gson.fromJson(reqTeamAway, TeamResponse::class.java)
            uiThread {

                view.hideLoading()
                view.showDetailMatch(detail?.detailItems, home?.teamsItems, away?.teamsItems)
            }
        }

    }
}