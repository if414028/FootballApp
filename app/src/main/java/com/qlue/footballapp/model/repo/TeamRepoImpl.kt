package com.qlue.footballapp.model.repo

import com.qlue.footballapp.model.Teams
import com.qlue.footballapp.network.APIFootball
import io.reactivex.Flowable

class TeamRepoImpl(val APIFootbal: APIFootball) : TeamRepo {
    override fun getTeam(id: String): Flowable<Teams> = APIFootbal.getAllTeam(id)

    override fun getDetailTeam(id: String): Flowable<Teams> = APIFootbal.getTeam(id)

    override fun getTeamBySearch(query: String): Flowable<Teams> = APIFootbal.getTeamBySearch(query)

    override fun getAllTeam(id: String): Flowable<Teams> = APIFootbal.getAllTeam(id)

}