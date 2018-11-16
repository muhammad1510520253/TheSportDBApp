package com.blogspot.ketikanmd.thesportdbapp.match

import com.blogspot.ketikanmd.thesportdbapp.models.PlayerModels

interface PlayerView {
    fun showPlayers(data: List<PlayerModels>?)
    fun showLoading()
    fun hideLoading()
}