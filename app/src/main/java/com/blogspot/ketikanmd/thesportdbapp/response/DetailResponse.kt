package com.blogspot.ketikanmd.thesportdbapp

import com.google.gson.annotations.SerializedName

data class DetailResponse(
        @SerializedName("events")
        val detailItems: List<DetailModels>?
)