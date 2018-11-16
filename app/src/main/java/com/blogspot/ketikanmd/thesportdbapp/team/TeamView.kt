package com.blogspot.ketikanmd.thesportdbapp.match

import com.blogspot.ketikanmd.thesportdbapp.TeamModels

interface TeamView {
    fun showTeam(data: List<TeamModels>?)
    fun showLoading()
    fun hideLoading()
    fun showNull()
}