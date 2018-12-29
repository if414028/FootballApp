package com.qlue.footballapp.model.repo

import com.qlue.footballapp.network.APIFootball

class PlayerRepoImpl(private val APIFootbal: APIFootball) : PlayerRepo {

    override fun getAllPlayer(teamId: String?) = APIFootbal.getAllPlayers(teamId)

    override fun getDetailPlayer(playerId: String?) = APIFootbal.getPlayerDetail(playerId)
}