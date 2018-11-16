package com.blogspot.ketikanmd.thesportdbapp.favorite

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.blogspot.ketikanmd.thesportdbapp.fragment.FavoriteMatchFragment
import com.blogspot.ketikanmd.thesportdbapp.fragment.FavoriteTeamFragment

class FavoritePagerAdapter(fm: FragmentManager?, it: Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return FavoriteMatchFragment()
            1 -> return FavoriteTeamFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return 2
    }


}