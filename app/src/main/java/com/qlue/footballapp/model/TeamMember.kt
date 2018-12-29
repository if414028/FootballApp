package com.qlue.footballapp.model

import com.google.gson.annotations.SerializedName

class TeamMember(
    @SerializedName("player")
    var player: List<Player>
)