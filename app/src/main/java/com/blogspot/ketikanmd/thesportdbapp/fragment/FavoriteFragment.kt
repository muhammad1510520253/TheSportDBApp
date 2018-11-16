package com.blogspot.ketikanmd.thesportdbapp.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.*
import com.blogspot.ketikanmd.thesportdbapp.R
import com.blogspot.ketikanmd.thesportdbapp.activity.AboutActivity
import com.blogspot.ketikanmd.thesportdbapp.activity.MainActivity
import com.blogspot.ketikanmd.thesportdbapp.favorite.FavoritePagerAdapter
import org.jetbrains.anko.support.v4.startActivity

class FavoriteFragment : Fragment() {
    private var tabLayoutFavorite: TabLayout? = null

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.search_menu, menu)
        menu.findItem(R.id.buttonSearch).setVisible(false)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.buttonAbout -> {
                MainActivity().finish()
                startActivity<AboutActivity>()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val views = inflater.inflate(R.layout.fragment_favorite, container, false)
        loadFavorite(views)
        setHasOptionsMenu(true)
        return views

    }


    fun loadFavorite(views: View) {
        tabLayoutFavorite = views.findViewById(R.id.tab_favorite) as TabLayout
        val viewPager = views.findViewById(R.id.viewPagerFavorite) as ViewPager
        val adapter = tabLayoutFavorite?.getTabCount()?.let { FavoritePagerAdapter(this.fragmentManager, it) }
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayoutFavorite))
        tabLayoutFavorite?.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
        })
    }
}