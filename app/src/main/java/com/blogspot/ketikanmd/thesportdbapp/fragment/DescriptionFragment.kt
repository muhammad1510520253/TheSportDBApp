package com.blogspot.ketikanmd.thesportdbapp.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.blogspot.ketikanmd.thesportdbapp.ApiRepository

import com.blogspot.ketikanmd.thesportdbapp.R
import com.blogspot.ketikanmd.thesportdbapp.TeamModels
import com.blogspot.ketikanmd.thesportdbapp.match.TeamView
import com.blogspot.ketikanmd.thesportdbapp.utils.invisible
import com.blogspot.ketikanmd.thesportdbapp.utils.visible
import com.blogspot.ketikanmd.thesportdbapp.team.TeamDetailPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_description.*
import org.jetbrains.anko.support.v4.toast


class DescriptionFragment : Fragment(), TeamView {
    lateinit var pbDescription: ProgressBar
    private lateinit var teamId: String
    private lateinit var presenterTeamDetail: TeamDetailPresenter
    override fun showTeam(data: List<TeamModels>?) {
        tvOverview.text = data?.get(0)?.strDescription
    }

    override fun showLoading() {
        pbDescription.visible()
    }

    override fun hideLoading() {
        pbDescription.invisible()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val views = inflater.inflate(R.layout.fragment_description, container, false)
        pbDescription = views?.findViewById(R.id.pb_description) as ProgressBar
        val i = activity?.intent
        teamId = i?.getStringExtra("idTeam").toString()
        presenterTeamDetail = TeamDetailPresenter(this, ApiRepository(), Gson())
        presenterTeamDetail.getDetaailTeam(teamId)
        return views
    }

    override fun showNull() {
        toast(R.string.teamNotFound)
    }
}
