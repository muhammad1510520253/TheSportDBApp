package com.blogspot.ketikanmd.thesportdbapp.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.*
import com.blogspot.ketikanmd.thesportdbapp.ApiRepository
import com.blogspot.ketikanmd.thesportdbapp.R
import com.blogspot.ketikanmd.thesportdbapp.TeamModels
import com.blogspot.ketikanmd.thesportdbapp.activity.AboutActivity
import com.blogspot.ketikanmd.thesportdbapp.activity.DetailTeamActivity
import com.blogspot.ketikanmd.thesportdbapp.activity.MainActivity
import com.blogspot.ketikanmd.thesportdbapp.activity.SearchTeamActivity
import com.blogspot.ketikanmd.thesportdbapp.match.*
import com.blogspot.ketikanmd.thesportdbapp.player.TeamAdapter
import com.blogspot.ketikanmd.thesportdbapp.utils.invisible
import com.blogspot.ketikanmd.thesportdbapp.utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast


class TeamFragment : Fragment(), TeamView {
    private lateinit var progressTeam: ProgressBar
    private lateinit var rvTeam: RecyclerView
    private val teamListItems: MutableList<TeamModels> = mutableListOf()
    private lateinit var teamPresen: TeamPresenter
    private lateinit var teamAdapter: TeamAdapter
    private lateinit var league: String
    private var leagueCover: Int = 0
    private lateinit var spinnerLeague: Spinner
    private lateinit var leagueImage: ImageView

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.search_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.buttonSearch -> {
                MainActivity().finish()
                startActivity<SearchTeamActivity>()
                return true
            }
            R.id.buttonAbout -> {
                MainActivity().finish()
                startActivity<AboutActivity>()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val views = inflater.inflate(R.layout.fragment_team, container, false)
        progressTeam = views?.findViewById(R.id.pb_team) as ProgressBar
        spinnerLeague = views.findViewById(R.id.sp_team) as Spinner
        rvTeam = views.findViewById(R.id.rv_team) as RecyclerView
        leagueImage = views.findViewById(R.id.leagueCover) as ImageView
        val itemsSpinner = resources.getStringArray(R.array.league)
        val adapterSpinner = ArrayAdapter<String>(ctx, R.layout.spinner_item, itemsSpinner)
        spinnerLeague.adapter = adapterSpinner
        views.let {
            teamAdapter = TeamAdapter(context, teamListItems) {
                ctx.startActivity<DetailTeamActivity>(
                        getString(R.string.idTeam) to it.idTeam,
                        getString(R.string.strTeam) to it.strTeam,
                        getString(R.string.yearTeam) to it.yearTeam,
                        getString(R.string.teamBadge) to it.teamBadge,
                        getString(R.string.strStadium) to it.strStadium
                )
            }
            loadListTeam()
            setHasOptionsMenu(true)
        }

        spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                loadListTeam()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        setHasOptionsMenu(true)
        return views
    }


    override fun hideLoading() {
        progressTeam.invisible()
        rvTeam.visible()
    }

    override fun showNull() {
        toast(R.string.teamNotFound)
    }

    override fun showLoading() {
        progressTeam.visible()
        rvTeam.invisible()
    }

    override fun showTeam(data: List<TeamModels>?) {
        data?.let {
            teamAdapter.refreshTeams(it)
            if (false) {
                toast("There is upcoming match")
            }
        }
    }

    fun getLeague(leagueName: String) {
        if (leagueName.equals(getString(R.string.english_premier_league))) {
            league = "English%20Premier%20League"
            leagueCover = R.drawable.liga_inggris
        } else if (leagueName.equals(getString(R.string.italian_serie_a))) {
            league = "Italian%20Serie%20A"
            leagueCover = R.drawable.liga_serie_a
        } else if (leagueName.equals(getString(R.string.french_ligue_1))) {
            league = "French%20Ligue%201"
            leagueCover = R.drawable.ligue_1
        } else if (leagueName.equals(getString(R.string.spanish_la_liga))) {
            league = "Spanish%20La%20Liga"
            leagueCover = R.drawable.liga_spanyol
        } else if (leagueName.equals(getString(R.string.english_league_championship))) {
            league = "English%20League%20Championship"
            leagueCover = R.drawable.liga_inggris
        } else if (leagueName.equals(getString(R.string.german_bundesliga))) {
            league = "German%20Bundesliga"
            leagueCover = R.drawable.bundesliga
        }

    }

    fun loadListTeam() {
        rvTeam.layoutManager = LinearLayoutManager(ctx)
        rvTeam.adapter = teamAdapter
        teamPresen = TeamPresenter(this, ApiRepository(), Gson())
        league = spinnerLeague.selectedItem.toString()
        getLeague(league)
        leagueImage.setImageResource(leagueCover)
        teamPresen.getListTeams(league)
    }
}