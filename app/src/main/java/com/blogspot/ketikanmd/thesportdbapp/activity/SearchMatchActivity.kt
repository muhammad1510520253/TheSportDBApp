package com.blogspot.ketikanmd.thesportdbapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.blogspot.ketikanmd.thesportdbapp.ApiRepository
import com.blogspot.ketikanmd.thesportdbapp.MatchModels
import com.blogspot.ketikanmd.thesportdbapp.R
import com.blogspot.ketikanmd.thesportdbapp.match.*
import com.blogspot.ketikanmd.thesportdbapp.utils.invisible
import com.blogspot.ketikanmd.thesportdbapp.utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class SearchMatchActivity : AppCompatActivity(), MatchView {
    private lateinit var pbMatch: ProgressBar
    private lateinit var rvMatch: RecyclerView
    private val matchItems: MutableList<MatchModels> = mutableListOf()
    private lateinit var matchAdapter: MatchAdapter
    private lateinit var matchSearchPresenter: MatchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_prev_match)
        rvMatch = findViewById(R.id.rv_prev) as RecyclerView
        pbMatch = findViewById(R.id.pb_prev) as ProgressBar

        matchAdapter = MatchAdapter(this, matchItems) {
            ctx.startActivity<DetailActivity>(
                    getString(R.string.matchId) to it.eventId,
                    getString(R.string.dateMatch) to it.dateMatch,
                    getString(R.string.idhome) to it.homeTeamId,
                    getString(R.string.idAway) to it.awayTeamId,
                    getString(R.string.homeTeam) to it.homeTeam,
                    getString(R.string.awayTeam) to it.awayTeam,
                    getString(R.string.goalHome) to it.homeScore,
                    getString(R.string.goalAway) to it.awayScore
            )
        }

        rvMatch.layoutManager = LinearLayoutManager(this)
        rvMatch.adapter = matchAdapter

        supportActionBar?.title = "Search Match"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun showNull() {
        toast(getString(R.string.foundMatch))
    }

    override fun showMatchEvent(data: List<MatchModels>?) {
        data?.let {
            matchAdapter.refreshMatch(it)
            if (false) {
                toast(R.string.foundMatch)
            }
        }
    }

    override fun showLoading() {
        pbMatch.visible()
        rvMatch.invisible()
    }

    override fun hideLoading() {
        pbMatch.invisible()
        rvMatch.visible()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.clear()
        menuInflater.inflate(R.menu.search, menu)
        val searchItem = menu.findItem(R.id.button_search)
        if (searchItem != null) {
            val matchSearchView = searchItem.actionView as android.support.v7.widget.SearchView
            matchSearchView.queryHint = getString(R.string.hintSearchMatch)
            matchSearchView.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    matchSearchPresenter = MatchPresenter(this@SearchMatchActivity, ApiRepository(), Gson())
                    matchSearchPresenter.getsearchMatch(query)
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    matchSearchPresenter = MatchPresenter(this@SearchMatchActivity, ApiRepository(), Gson())
                    matchSearchPresenter.getsearchMatch(query)
                    return true
                }

            })
        }
        return true
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
