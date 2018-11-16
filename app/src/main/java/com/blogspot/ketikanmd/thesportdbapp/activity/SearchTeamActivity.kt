package com.blogspot.ketikanmd.thesportdbapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.blogspot.ketikanmd.thesportdbapp.ApiRepository
import com.blogspot.ketikanmd.thesportdbapp.TeamModels
import com.blogspot.ketikanmd.thesportdbapp.R
import com.blogspot.ketikanmd.thesportdbapp.match.TeamSearchPresenter
import com.blogspot.ketikanmd.thesportdbapp.match.TeamView
import com.blogspot.ketikanmd.thesportdbapp.utils.invisible
import com.blogspot.ketikanmd.thesportdbapp.utils.visible
import com.blogspot.ketikanmd.thesportdbapp.player.TeamAdapter
import android.support.v7.widget.SearchView
import com.google.gson.Gson
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SearchTeamActivity : AppCompatActivity(), TeamView {
    private lateinit var pbTeam: ProgressBar
    private lateinit var rvTeam: RecyclerView
    private val teamItems: MutableList<TeamModels> = mutableListOf()
    private lateinit var teamAdapter: TeamAdapter
    private lateinit var teamSearchPresenter: TeamSearchPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)
        setContentView(R.layout.fragment__search_team)
        rvTeam = findViewById(R.id.rv_teamSearch) as RecyclerView
        pbTeam = findViewById(R.id.pb_teamSearch) as ProgressBar

        teamAdapter = TeamAdapter(this, teamItems) {
            ctx.startActivity<DetailTeamActivity>(
                    getString(R.string.idTeam) to it.idTeam,
                    getString(R.string.strTeam) to it.strTeam,
                    getString(R.string.yearTeam) to it.yearTeam,
                    getString(R.string.teamBadge) to it.teamBadge,
                    getString(R.string.strStadium) to it.strStadium
            )
        }

        rvTeam.layoutManager = LinearLayoutManager(this)
        rvTeam.adapter = teamAdapter
        supportActionBar?.title = "Search Team"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun showNull() {
        toast(R.string.teamNotFound)
    }

    override fun showTeam(data: List<TeamModels>?) {
        data?.let {
            teamAdapter.refreshTeams(it)
            if (false) {
                toast(getString(R.string.teamNotFound))
            }
        }
    }

    override fun showLoading() {
        pbTeam.visible()
        rvTeam.invisible()
    }

    override fun hideLoading() {
        pbTeam.invisible()
        rvTeam.visible()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.clear()
        menuInflater.inflate(R.menu.search, menu)
        val searchItem = menu.findItem(R.id.button_search)
        if (searchItem != null) {
            val teamSearchView = searchItem.actionView as SearchView
            teamSearchView.queryHint = getString(R.string.hintSearchTeam)
            teamSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    teamSearchPresenter = TeamSearchPresenter(this@SearchTeamActivity, ApiRepository(), Gson())
                    teamSearchPresenter.getSearchTeam(query)
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {

                    teamSearchPresenter = TeamSearchPresenter(this@SearchTeamActivity, ApiRepository(), Gson())
                    teamSearchPresenter.getSearchTeam(query)
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
