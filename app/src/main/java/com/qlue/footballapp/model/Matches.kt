package com.qlue.footballapp.model

import com.google.gson.annotations.SerializedName

data class Matches(
    @SerializedName("events") var match: List<Match>)