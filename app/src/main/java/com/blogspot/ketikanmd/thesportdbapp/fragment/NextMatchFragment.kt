package com.blogspot.ketikanmd.thesportdbapp.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.blogspot.ketikanmd.thesportdbapp.*
import com.blogspot.ketikanmd.thesportdbapp.activity.DetailActivity
import com.google.gson.Gson
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.blogspot.ketikanmd.thesportdbapp.match.*
import com.blogspot.ketikanmd.thesportdbapp.utils.invisible
import com.blogspot.ketikanmd.thesportdbapp.utils.visible
import org.jetbrains.anko.support.v4.toast


class NextMatchFragment : Fragment(), MatchView {
    private lateinit var progressNext: ProgressBar
    private lateinit var rvNext: RecyclerView
    private val matchItems: MutableList<MatchModels> = mutableListOf()
    private lateinit var matchPresen: MatchPresenter
    private lateinit var matchAdapter: MatchAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val views = inflater.inflate(R.layout.fragment_next_match, container, false)
        progressNext = views?.findViewById(R.id.pb_next) as ProgressBar
        rvNext = views.findViewById(R.id.rv_next) as RecyclerView
        views.let {
            matchAdapter = MatchAdapter(context, matchItems) {
                ctx.startActivity<DetailActivity>(
                        getString(R.string.dateMatch) to it.dateMatch,
                        getString(R.string.homeTeam) to it.homeTeam,
                        getString(R.string.awayTeam) to it.awayTeam,
                        getString(R.string.matchId) to it.eventId,
                        getString(R.string.idhome) to it.homeTeamId,
                        getString(R.string.idAway) to it.awayTeamId,
                        getString(R.string.goalHome) to "",
                        getString(R.string.goalAway) to ""

                )
            }
            rvNext.layoutManager = LinearLayoutManager(ctx)
            rvNext.adapter = matchAdapter
            matchPresen = MatchPresenter(this, ApiRepository(), Gson())
            var leagueId: String = arguments?.getString("MATCH_ID", "4328").toString()
            matchPresen.getNextMatch(leagueId)
        }

        return views
    }


    override fun showNull() {
        toast(R.string.foundMatch)
    }

    override fun showMatchEvent(data: List<MatchModels>?) {
        data?.let {
            matchAdapter.refreshMatch(it)
            if (false) {
                toast("There is upcoming match")
            }
        }
    }

    override fun showLoading() {
        progressNext.visible()
    }

    override fun hideLoading() {
        progressNext.invisible()
    }


}