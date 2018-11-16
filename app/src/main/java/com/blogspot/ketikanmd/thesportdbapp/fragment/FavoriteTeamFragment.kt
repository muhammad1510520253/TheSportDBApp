package com.blogspot.ketikanmd.thesportdbapp.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blogspot.ketikanmd.thesportdbapp.R
import com.blogspot.ketikanmd.thesportdbapp.activity.DetailTeamActivity
import com.blogspot.ketikanmd.thesportdbapp.favorite.database
import com.blogspot.ketikanmd.thesportdbapp.favorite.match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteTeamFragment : Fragment() {
    private var favoritesTeam: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var adapter: FavoriteTeamAdapter
    private lateinit var srFavoriteTeam: SwipeRefreshLayout
    private lateinit var rvfavoriteTeam: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val views = inflater.inflate(R.layout.fragment_favorite_team, container, false)
        srFavoriteTeam = views.find(R.id.sr_favoriteTeam)
        rvfavoriteTeam = views.find(R.id.rv_favoriteTeam)
        views.let {
            adapter = FavoriteTeamAdapter(context, favoritesTeam) {
                ctx.startActivity<DetailTeamActivity>(
                        getString(R.string.idTeam) to it.teamId,
                        getString(R.string.strTeam) to it.teamName,
                        getString(R.string.yearTeam) to it.teamYear,
                        getString(R.string.teamBadge) to it.teamLogo,
                        getString(R.string.strStadium) to it.teamStadium
                )
            }

            rvfavoriteTeam.layoutManager = LinearLayoutManager(ctx)
            rvfavoriteTeam.adapter = adapter
            showFavoriteTeam()
            refreshTeam()

        }
        return views
    }

    private fun refreshTeam() {
        srFavoriteTeam.onRefresh {
            favoritesTeam.clear()
            showFavoriteTeam()

        }
    }


    private fun showFavoriteTeam() {
        context?.database?.use {
            srFavoriteTeam.isRefreshing = false
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favoriteTeam = result.parseList(classParser<FavoriteTeam>())
            favoritesTeam.addAll(favoriteTeam)
            adapter.notifyDataSetChanged()
        }

    }


}
