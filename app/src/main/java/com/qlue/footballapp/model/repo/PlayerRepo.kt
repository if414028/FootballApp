package com.qlue.footballapp.model.repo

import com.qlue.footballapp.model.TeamMember
import com.qlue.footballapp.model.Players
import io.reactivex.Flowable

interface PlayerRepo {

    fun getAllPlayer(teamId: String?): Flowable<TeamMember>
    fun getDetailPlayer(playerId: String?): Flowable<Players>

}