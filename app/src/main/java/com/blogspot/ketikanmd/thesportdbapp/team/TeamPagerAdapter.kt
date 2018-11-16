package com.blogspot.ketikanmd.thesportdbapp.team

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.blogspot.ketikanmd.thesportdbapp.activity.DetailTeamActivity
import com.blogspot.ketikanmd.thesportdbapp.fragment.*

class TeamPagerAdapter(fm: FragmentManager?, internal var tabsNum: Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return DescriptionFragment()
            1 -> return ListPlayerFragment()
            else -> return null
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Description"
            1 -> return "Players"
            else -> return null
        }
    }

    override fun getCount(): Int {
        return tabsNum
    }
}