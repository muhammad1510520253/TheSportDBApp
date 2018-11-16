package com.blogspot.ketikanmd.thesportdbapp.match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.blogspot.ketikanmd.thesportdbapp.fragment.NextMatchFragment
import com.blogspot.ketikanmd.thesportdbapp.fragment.PrevMatchFragment

class MatchPagerAdapter (fm: FragmentManager?,internal var tabsNum:Int, private val leagueId: String) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        val bundleMatch = Bundle()
        bundleMatch.putString("MATCH_ID", leagueId)
        when (position) {
            0 -> {
                val fragmentMatch = PrevMatchFragment()
                fragmentMatch.arguments = bundleMatch
                return fragmentMatch
            }
            1 -> {
                val fragmentMatch = NextMatchFragment()
                fragmentMatch.arguments = bundleMatch
                return fragmentMatch
            }
            else -> return null


        }
    }

    override fun getCount(): Int {
       return tabsNum
    }

}