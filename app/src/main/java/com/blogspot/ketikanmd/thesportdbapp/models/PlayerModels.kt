package com.blogspot.ketikanmd.thesportdbapp.models

import com.google.gson.annotations.SerializedName

class PlayerModels(
        @SerializedName("idPlayer")
        val idPlayer: String? = null,
        @SerializedName("strPlayer")
        val strPlayer: String? = null,
        @SerializedName("strDescriptionEN")
        val strDescriptionEN: String? = null,
        @SerializedName("strPosition")
        val strPosition: String? = null,
        @SerializedName("strHeight")
        val strHeight: String? = null,
        @SerializedName("strWeight")
        val strWeight: String? = null,
        @SerializedName("strFanart1")
        val strFanart1: String? = null,
        @SerializedName("strCutout")
        val strCutout: String? = null
)