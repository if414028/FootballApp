package com.qlue.footballapp.model

import com.google.gson.annotations.SerializedName

data class SearchMatchResult(@SerializedName("event") var match: List<Match>)