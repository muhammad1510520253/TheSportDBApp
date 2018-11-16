package com.blogspot.ketikanmd.thesportdbapp.detail.detail.detailMatch

import com.blogspot.ketikanmd.thesportdbapp.DetailModels
import com.blogspot.ketikanmd.thesportdbapp.TeamModels

interface DetailMatchView {
    fun showDetailMatch(detail: List<DetailModels>?, home: List<TeamModels>?, away: List<TeamModels>?)
    fun showLoading()
    fun hideLoading()
}