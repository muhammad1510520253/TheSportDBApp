package com.blogspot.ketikanmd.thesportdbapp

import com.google.gson.annotations.SerializedName

data class MatchResponse(
        @SerializedName("events")
        val matchItems: List<MatchModels>?
)