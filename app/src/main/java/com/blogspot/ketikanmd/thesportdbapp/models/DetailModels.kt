package com.blogspot.ketikanmd.thesportdbapp

import com.google.gson.annotations.SerializedName

data class DetailModels(
        @SerializedName("intHomeShots")
        val homeShots: String? = null,
        @SerializedName("intAwayShots")
        val awayShots: String? = null,

        @SerializedName("strHomeGoalDetails")
        val homeGoals: String? = null,
        @SerializedName("strAwayGoalDetails")
        val awayGoals: String? = null,

        @SerializedName("strHomeLineupForward")
        val homeForward: String? = null,
        @SerializedName("strAwayLineupForward")
        val awayForward: String? = null,

        @SerializedName("strHomeLineupSubstitutes")
        val homeSubs: String? = null,
        @SerializedName("strAwayLineupSubstitutes")
        val awaySubs: String? = null,

        @SerializedName("strHomeFormation")
        val homeForm: String? = null,
        @SerializedName("strAwayFormation")
        val awayForm: String? = null,


        @SerializedName("strHomeRedCards")
        val homeRedCard: String? = null,
        @SerializedName("strAwayRedCards")
        val awayRedCard: String? = null,

        @SerializedName("strHomeYellowCards")
        val homeYellowCard: String? = null,
        @SerializedName("strAwayYellowCards")
        val awayYellowCard: String? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        val homeGK: String? = null,
        @SerializedName("strAwayLineupGoalkeeper")
        val awayGK: String? = null,

        @SerializedName("strHomeLineupDefense")
        val homeDef: String? = null,
        @SerializedName("strAwayLineupDefense")
        val awayDef: String? = null,

        @SerializedName("strHomeLineupMidfield")
        val homeMidfield: String? = null,
        @SerializedName("strAwayLineupMidfield")
        val awayMidfield: String? = null


)