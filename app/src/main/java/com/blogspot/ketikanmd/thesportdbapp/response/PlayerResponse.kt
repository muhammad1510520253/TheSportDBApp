package com.blogspot.ketikanmd.thesportdbapp

import com.blogspot.ketikanmd.thesportdbapp.models.PlayerModels
import com.google.gson.annotations.SerializedName


data class PlayerResponse(
        @SerializedName("player")
        val playersItems: List<PlayerModels>?
)