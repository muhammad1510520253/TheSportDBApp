package com.blogspot.ketikanmd.thesportdbapp.match

import com.blogspot.ketikanmd.thesportdbapp.MatchModels

interface MatchView {
    fun showMatchEvent(data: List<MatchModels>?)
    fun showLoading()
    fun hideLoading()
    fun showNull()
}