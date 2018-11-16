package com.blogspot.ketikanmd.thesportdbapp

import com.google.gson.annotations.SerializedName

data class TeamResponse(
        @SerializedName("teams")
        val teamsItems: List<TeamModels>?
)