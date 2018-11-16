package com.blogspot.ketikanmd.thesportdbapp.fragment


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import com.blogspot.ketikanmd.thesportdbapp.R
import com.blogspot.ketikanmd.thesportdbapp.activity.AboutActivity
import com.blogspot.ketikanmd.thesportdbapp.activity.MainActivity
import com.blogspot.ketikanmd.thesportdbapp.activity.SearchMatchActivity
import com.blogspot.ketikanmd.thesportdbapp.match.MatchPagerAdapter
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class MatchFragment : Fragment() {
    lateinit var leagueId: String
    private lateinit var leagueName: String
    private lateinit var spinnerLeague: Spinner
    private var leagueCover: Int = 0
    private var tabLayoutMatch: TabLayout? = null
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
                startActivity<SearchMatchActivity>()
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val views = inflater.inflate(R.layout.fragment_match, container, false)
        spinnerLeague = views?.findViewById(R.id.sp_match) as Spinner
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter<String>(ctx, R.layout.spinner_item, spinnerItems);
        leagueImage = views.findViewById(R.id.leagueCoverMatch) as ImageView
        spinnerLeague.adapter = spinnerAdapter
        spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                loadMatch(views)
            }
        }
        loadMatch(views)
        setHasOptionsMenu(true)
        return views
    }


    fun getLeagueId(leagueName: String) {
        if (leagueName.equals(getString(R.string.english_premier_league))) {
            leagueId = "4328"
            leagueCover = R.drawable.liga_inggris
        } else if (leagueName.equals(getString(R.string.italian_serie_a))) {
            leagueId = "4332"
            leagueCover = R.drawable.liga_serie_a
        } else if (leagueName.equals(getString(R.string.french_ligue_1))) {
            leagueId = "4334"
            leagueCover = R.drawable.ligue_1
        } else if (leagueName.equals(getString(R.string.spanish_la_liga))) {
            leagueId = "4335"
            leagueCover = R.drawable.liga_spanyol
        } else if (leagueName.equals(getString(R.string.english_league_championship))) {
            leagueId = "4329"
            leagueCover = R.drawable.liga_inggris
        } else if (leagueName.equals(getString(R.string.german_bundesliga))) {
            leagueId = "4331"
            leagueCover = R.drawable.bundesliga
        }
    }

    fun loadMatch(views: View) {
        leagueName = spinnerLeague.selectedItem.toString()
        getLeagueId(leagueName)
        leagueImage.setImageResource(leagueCover)
        tabLayoutMatch = views.findViewById(R.id.tab_match) as TabLayout
        val viewPagerMatch = views.findViewById(R.id.viewPagerMatch) as ViewPager
        val adapter = tabLayoutMatch?.getTabCount()?.let { MatchPagerAdapter(fragmentManager, it, leagueId) }
        viewPagerMatch.adapter = adapter
        viewPagerMatch.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayoutMatch))
        tabLayoutMatch?.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPagerMatch.currentItem = tab.position
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                viewPagerMatch.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                viewPagerMatch.currentItem = tab.position
            }


        })
    }


}