package com.blogspot.ketikanmd.thesportdbapp.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.blogspot.ketikanmd.thesportdbapp.ApiRepository
import com.blogspot.ketikanmd.thesportdbapp.R
import com.blogspot.ketikanmd.thesportdbapp.activity.DetailPlayerActivity
import com.blogspot.ketikanmd.thesportdbapp.match.*
import com.blogspot.ketikanmd.thesportdbapp.models.PlayerModels
import com.blogspot.ketikanmd.thesportdbapp.utils.invisible
import com.blogspot.ketikanmd.thesportdbapp.utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx


class ListPlayerFragment : Fragment(), PlayerView {
    private lateinit var teamId: String
    private lateinit var progressPlayer: ProgressBar
    private lateinit var rvPlayer: RecyclerView
    private val playerItems: MutableList<PlayerModels> = mutableListOf()
    private lateinit var playerPresen: PlayerPresenter
    private lateinit var playerAdapter: PlayerAdapter

    override fun showPlayers(data: List<PlayerModels>?) {
        data?.let {
            playerAdapter.refreshPlayer(it)
        }
    }

    override fun showLoading() {
        progressPlayer.visible()

    }

    override fun hideLoading() {
        progressPlayer.invisible()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val views = inflater.inflate(R.layout.fragment_list_player, container, false)
        progressPlayer = views?.findViewById(R.id.pb_player) as ProgressBar
        rvPlayer = views.findViewById(R.id.rv_player) as RecyclerView
        views.let {
            playerAdapter = PlayerAdapter(context, playerItems) {
                ctx.startActivity<DetailPlayerActivity>(
                        getString(R.string.idPlayer) to it.idPlayer,
                        getString(R.string.playerImage) to it.strFanart1,
                        getString(R.string.strPlayer) to it.strPlayer,
                        getString(R.string.strPosition) to it.strPosition,
                        getString(R.string.strWeight) to it.strWeight,
                        getString(R.string.strHeight) to it.strHeight,
                        getString(R.string.strDescriptionEN) to it.strDescriptionEN

                )
            }
            rvPlayer.layoutManager = LinearLayoutManager(ctx)
            rvPlayer.adapter = playerAdapter
            playerPresen = PlayerPresenter(this, ApiRepository(), Gson())
            val i = activity?.intent
            teamId = i?.getStringExtra("idTeam").toString()
            playerPresen.getPlayer(teamId)
        }

        return views
    }


}