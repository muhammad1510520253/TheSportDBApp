package com.blogspot.ketikanmd.thesportdbapp.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.blogspot.ketikanmd.thesportdbapp.R
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import android.view.View
import android.view.ViewGroup
import com.blogspot.ketikanmd.thesportdbapp.activity.DetailActivity
import com.blogspot.ketikanmd.thesportdbapp.favorite.database
import com.blogspot.ketikanmd.thesportdbapp.favorite.match.FavoriteMatchAdapter
import com.blogspot.ketikanmd.thesportdbapp.favorite.match.*
import org.jetbrains.anko.db.select


class FavoriteMatchFragment : Fragment() {
    private var favoritesMatch: MutableList<FavoriteMatch> = mutableListOf()
    private lateinit var adapter: FavoriteMatchAdapter
    private lateinit var srFavoriteMatch: SwipeRefreshLayout
    private lateinit var rvfavoriteMatch: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val views = inflater.inflate(R.layout.fragment_favorite_match, container, false)
        srFavoriteMatch = views.find(R.id.sr_favoriteMatch)
        rvfavoriteMatch = views.find(R.id.rv_favoriteMatch)
        views.let {
            adapter = FavoriteMatchAdapter(context, favoritesMatch) {
                ctx.startActivity<DetailActivity>(
                        getString(R.string.idhome) to it.homeTeamId,
                        getString(R.string.idAway) to it.awayTeamId,
                        getString(R.string.goalHome) to it.scoreHome,
                        getString(R.string.goalAway) to it.scoreAway,
                        getString(R.string.homeTeam) to it.teamHome,
                        getString(R.string.awayTeam) to it.teamAway,
                        getString(R.string.dateMatch) to it.dateMatch,
                        getString(R.string.matchId) to it.eventId
                )
            }

            rvfavoriteMatch.layoutManager = LinearLayoutManager(ctx)
            rvfavoriteMatch.adapter = adapter
            favoritesMatch.clear()
            showFavoriteMatch()
            refreshMatch()

        }
        return views
    }
    private fun refreshMatch() {
        srFavoriteMatch.onRefresh {
            favoritesMatch.clear()
            showFavoriteMatch()

        }
    }

    private fun showFavoriteMatch() {
        context?.database?.use {
            srFavoriteMatch.isRefreshing = false
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
            val favoriteMatch = result.parseList(classParser<FavoriteMatch>())
            favoritesMatch.addAll(favoriteMatch)
            adapter.notifyDataSetChanged()
        }

    }

}





