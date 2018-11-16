package com.blogspot.ketikanmd.thesportdbapp.activity

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.blogspot.ketikanmd.thesportdbapp.R
import com.blogspot.ketikanmd.thesportdbapp.favorite.match.FavoriteTeam
import com.blogspot.ketikanmd.thesportdbapp.favorite.database
import com.blogspot.ketikanmd.thesportdbapp.match.MatchPagerAdapter
import com.blogspot.ketikanmd.thesportdbapp.team.TeamPagerAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailTeamActivity : AppCompatActivity() {
    private var tabLayoutTeam: TabLayout? = null
    private var viewPagerTeam: ViewPager? = null
    private var menuItem: Menu? = null
    private var isFavoriteTeam: Boolean = false
    private lateinit var idTeam: String
    private fun addFavoriteTeam() {
        try {
            database.use {
                val i = intent
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                        FavoriteTeam.teamId to i.getStringExtra("idTeam"),
                        FavoriteTeam.teamName to i.getStringExtra("strTeam"),
                        FavoriteTeam.teamLogo to i.getStringExtra("teamBadge"),
                        FavoriteTeam.teamYear to i.getStringExtra("yearTeam"),
                        FavoriteTeam.teamStadium to i.getStringExtra("strStadium")
                )
            }
            toast("Added to favorite Team").show()
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage).show()

        }
    }

    private fun removeFavoriteTeam() {
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(${FavoriteTeam.teamId} = {id})",
                        "id" to idTeam)
            }
            toast("Removed to favorite Team").show()
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage).show()
        }
    }

    private fun favoriteTeamState() {
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                    .whereArgs("(${FavoriteTeam.teamId} = {id})",
                            "id" to idTeam)
            val favoriteTeam = result.parseList(classParser<FavoriteTeam>())
            if (!favoriteTeam.isEmpty()) isFavoriteTeam = true
        }
    }

    private fun checkFavoriteTeam() {
        if (isFavoriteTeam)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites_match)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites_match)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        checkFavoriteTeam()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favoriteMatch -> {
                if (isFavoriteTeam) removeFavoriteTeam()
                else addFavoriteTeam()
                isFavoriteTeam = !isFavoriteTeam
                checkFavoriteTeam()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        viewPagerTeam = findViewById<ViewPager?>(R.id.viewPagerTeam)
        tabLayoutTeam = findViewById<View>(R.id.tab_team) as TabLayout
        val adapter = tabLayoutTeam?.getTabCount()?.let { TeamPagerAdapter(supportFragmentManager, it) }
        viewPagerTeam?.adapter = adapter
        viewPagerTeam?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayoutTeam))
        tabLayoutTeam?.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPagerTeam?.currentItem = tab.position
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                viewPagerTeam?.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                viewPagerTeam?.currentItem = tab.position
            }

        })
        actionBar?.title = intent.getStringExtra("strTeam")
        idTeam = intent.getStringExtra("idTeam")
        favoriteTeamState()
        val i = intent
        TeamName.text = i.getStringExtra("strTeam") + "\n" + i.getStringExtra("yearTeam") + "\n" + i.getStringExtra("strStadium")
        Picasso.get().load(i.getStringExtra("teamBadge")).into(imgTeam)
    }


}
