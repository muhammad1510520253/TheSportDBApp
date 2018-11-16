package com.blogspot.ketikanmd.thesportdbapp.match

import com.blogspot.ketikanmd.thesportdbapp.ApiRepository
import com.blogspot.ketikanmd.thesportdbapp.TheSportDBApi
import com.blogspot.ketikanmd.thesportdbapp.MatchResponse
import com.blogspot.ketikanmd.thesportdbapp.response.MatchSearchResponse
import com.blogspot.ketikanmd.thesportdbapp.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter(
        private val view: MatchView,
        private val apiRepo: ApiRepository,
        private val gson: Gson,
        private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getPastMatch(league: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepo
                        .doRequest(TheSportDBApi.getPastMatch(league)),
                        MatchResponse::class.java
                )
            }
            view.showMatchEvent(data.await().matchItems)
            view.hideLoading()
        }
    }

    fun getNextMatch(league: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepo
                        .doRequest(TheSportDBApi.getNextMatch(league)),
                        MatchResponse::class.java
                )
            }
            view.showMatchEvent(data.await().matchItems)
            view.hideLoading()
        }
    }

    fun getsearchMatch(teamName: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepo
                        .doRequest(TheSportDBApi.getSearchMatch(teamName)),
                        MatchSearchResponse::class.java
                )
            }
            view.showMatchEvent(data.await().matchItems)
            view.hideLoading()
        }
    }
}



