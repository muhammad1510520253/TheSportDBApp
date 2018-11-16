package com.blogspot.ketikanmd.thesportdbapp.activity

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.blogspot.ketikanmd.thesportdbapp.*
import com.blogspot.ketikanmd.thesportdbapp.R.drawable.ic_add_to_favorites_match
import com.blogspot.ketikanmd.thesportdbapp.R.drawable.ic_added_to_favorites_match
import com.blogspot.ketikanmd.thesportdbapp.R.id.add_to_favoriteMatch
import com.blogspot.ketikanmd.thesportdbapp.R.menu.detail_menu
import com.blogspot.ketikanmd.thesportdbapp.detail.detail.detailMatch.DetailMatchPresenter
import com.blogspot.ketikanmd.thesportdbapp.detail.detail.detailMatch.DetailMatchView
import com.blogspot.ketikanmd.thesportdbapp.favorite.match.FavoriteMatch
import com.blogspot.ketikanmd.thesportdbapp.favorite.database
import com.blogspot.ketikanmd.thesportdbapp.utils.dateFormat
import com.blogspot.ketikanmd.thesportdbapp.utils.invisible
import com.blogspot.ketikanmd.thesportdbapp.utils.visible
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast


class DetailActivity : AppCompatActivity(), DetailMatchView {
    private lateinit var detailPresenter: DetailMatchPresenter
    private var menuItem: Menu? = null
    private var isFavoriteMatch: Boolean = false
    private lateinit var idMatch: String


    private fun addFavoriteMatch() {
        try {
            database.use {
                val i = intent
                insert(FavoriteMatch.TABLE_FAVORITE_MATCH,
                        FavoriteMatch.eventId to i.getStringExtra("matchId"),
                        FavoriteMatch.dateMatch to i.getStringExtra("dateMatch"),
                        FavoriteMatch.teamHome to i.getStringExtra("homeTeam"),
                        FavoriteMatch.teamAway to i.getStringExtra("awayTeam"),
                        FavoriteMatch.scoreHome to i.getStringExtra("goalHome"),
                        FavoriteMatch.scoreAway to i.getStringExtra("goalAway"),
                        FavoriteMatch.awayTeamId to i.getStringExtra("idAway"),
                        FavoriteMatch.homeTeamId to i.getStringExtra("idHome")
                )
            }
            toast("Added to favorite Match").show()
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage).show()

        }
    }

    private fun removeFavoriteMatch() {
        try {
            database.use {
                delete(FavoriteMatch.TABLE_FAVORITE_MATCH, "(${FavoriteMatch.eventId} = {id})",
                        "id" to idMatch)

            }

            toast("Removed to favorite Match").show()
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage).show()
        }
    }

    private fun favoriteMatchState() {
        database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                    .whereArgs("(${FavoriteMatch.eventId} = {id})",
                            "id" to idMatch)
            val favoriteMatch = result.parseList(classParser<FavoriteMatch>())
            if (!favoriteMatch.isEmpty()) isFavoriteMatch = true
        }
    }

    private fun checkFavoriteMatch() {
        if (isFavoriteMatch)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites_match)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites_match)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        checkFavoriteMatch()
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favoriteMatch -> {
                if (isFavoriteMatch) removeFavoriteMatch()
                else addFavoriteMatch()
                isFavoriteMatch = !isFavoriteMatch
                checkFavoriteMatch()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        idMatch = intent.getStringExtra("matchId")
        favoriteMatchState()

        ctx.let {
            val i = intent
            actionBar.title = i.getStringExtra("homeTeam") + " vs " + i.getStringExtra("awayTeam")
            val homeId: String? = i.getStringExtra("idHome")
            val awayId: String? = i.getStringExtra("idAway")
            val matchId: String? = i.getStringExtra("matchId")

            detailPresenter = DetailMatchPresenter(this, ApiRepository(), Gson())
            if (i.getStringExtra("dateMatch") == null) {
                date_match.text = "-"
            } else {
                date_match.text = i.getStringExtra("dateMatch").toString().dateFormat()
            }
            home_team.text = i.getStringExtra("homeTeam")
            scoreHome.text = i.getStringExtra("goalHome")
            home_team_side.text = i.getStringExtra("awayTeam")
            scoreAway.text = i.getStringExtra("goalAway")
            detailPresenter.loadDMatchDetail(homeId, awayId, matchId)

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun showDetailMatch(detail: List<DetailModels>?, home: List<TeamModels>?, away: List<TeamModels>?) {
        try {
            let {
                val data = detail?.get(0)
                val homeData = home?.get(0)
                val awayData = away?.get(0)

                Glide.with(ctx).load(homeData?.teamBadge).into(img_home)
                Glide.with(ctx).load(awayData?.teamBadge).into(img_away)
                home_manager.text = homeData?.manager
                away_manager.text = awayData?.manager
                tv_goal_home.text = data?.homeGoals
                tv_goal_away.text = data?.awayGoals
                tv_shots_home.text = data?.homeShots
                tv_shots_away.text = data?.awayShots
                tv_red_home.text = data?.homeRedCard
                tv_red_away.text = data?.awayRedCard
                tv_yellow_home.text = data?.homeYellowCard
                tv_yellow_away.text = data?.awayYellowCard
                tv_gk_home.text = data?.homeGK
                tv_gk_away.text = data?.awayGK
                tv_def_home.text = data?.homeDef
                tv_def_away.text = data?.awayDef
                tv_mid_home.text = data?.homeMidfield
                tv_mid_away.text = data?.awayMidfield
                tv_for_home.text = data?.homeForward
                tv_for_away.text = data?.awayForward
                tv_sub_home.text = data?.homeSubs
                tv_sub_away.text = data?.awaySubs
            }
        } catch (e: Exception) {

            toast(e.toString()).show()
        }
    }


    override fun showLoading() {
        pb_detail.visible()
        mainLayout.invisible()
    }

    override fun hideLoading() {
        pb_detail.invisible()
        mainLayout.visible()
    }

}

