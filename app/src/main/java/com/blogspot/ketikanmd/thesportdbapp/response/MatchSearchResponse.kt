package com.blogspot.ketikanmd.thesportdbapp.response

import com.blogspot.ketikanmd.thesportdbapp.MatchModels
import com.google.gson.annotations.SerializedName

data class MatchSearchResponse(
        @SerializedName("event")
        val matchItems: List<MatchModels>?
)