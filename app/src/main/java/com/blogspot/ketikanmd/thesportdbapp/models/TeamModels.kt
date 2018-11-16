package com.blogspot.ketikanmd.thesportdbapp

import com.google.gson.annotations.SerializedName

data class TeamModels(
        @SerializedName("strManager")
        val manager: String? = null,
        @SerializedName("idTeam")
        val idTeam: String? = null,
        @SerializedName("strTeam")
        val strTeam: String? = null,
        @SerializedName("intFormedYear")
        val yearTeam: String? = null,
        @SerializedName("strTeamBadge")
        val teamBadge: String? = null,
        @SerializedName("strStadium")
        val strStadium: String? = null,
        @SerializedName("strDescriptionEN")
        val strDescription: String? = null

)