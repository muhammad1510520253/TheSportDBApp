package com.blogspot.ketikanmd.thesportdbapp

import com.google.gson.annotations.SerializedName

data class MatchModels(
        @SerializedName("idEvent")
        var eventId: String? = null,
        @SerializedName("strDate")
        var dateMatch: String? = null,

        @SerializedName("intHomeScore")
        var homeScore: String? = null,
        @SerializedName("intAwayScore")
        var awayScore: String? = null,


        @SerializedName("idHomeTeam")
        var homeTeamId: String? = null,
        @SerializedName("idAwayTeam")
        var awayTeamId: String? = null,

        @SerializedName("strHomeTeam")
        var homeTeam: String? = null,
        @SerializedName("strAwayTeam")
        var awayTeam: String? = null


)