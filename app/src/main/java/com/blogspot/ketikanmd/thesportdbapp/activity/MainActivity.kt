package com.blogspot.ketikanmd.thesportdbapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import com.blogspot.ketikanmd.thesportdbapp.R
import com.blogspot.ketikanmd.thesportdbapp.fragment.*


class MainActivity : AppCompatActivity() {
    private var mRecentlyBackPressed = false
    private var mExitHandler: android.os.Handler? = android.os.Handler()
    private val mExitRunnable = Runnable { mRecentlyBackPressed = false }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationView(savedInstanceState)

    }

    override fun onBackPressed() {
        if (mRecentlyBackPressed) {
            mExitHandler!!.removeCallbacks(mExitRunnable)
            mExitHandler = null
            super.onBackPressed()
        } else {
            mRecentlyBackPressed = true
            Toast.makeText(this, "press again to exit", Toast.LENGTH_SHORT).show()
            mExitHandler!!.postDelayed(mExitRunnable, delay)
        }
    }

    companion object {
        private val delay = 2000L
    }


    private fun getMatch(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MatchFragment(), MatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun getTeam(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamFragment(), TeamFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun getFavorite(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                    .commit()
        }
    }


    private fun navigationView(savedInstanceState: Bundle?) {
        bottom_navigation.setOnNavigationItemSelectedListener({ item ->
            when (item.itemId) {
                R.id.Match -> {
                    getMatch(savedInstanceState)
                    supportActionBar?.title = "Match"
                }
                R.id.Team -> {
                    getTeam(savedInstanceState)
                    supportActionBar?.title = "Team"
                }
                R.id.Favorite -> {
                    getFavorite(savedInstanceState)
                    supportActionBar?.title = "Favorite"
                }
            }
            true
        })
        bottom_navigation.selectedItemId = R.id.Match
    }


}



