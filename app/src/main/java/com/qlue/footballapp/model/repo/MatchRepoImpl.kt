package com.qlue.footballapp.model.repo

import com.qlue.footballapp.model.Matches
import com.qlue.footballapp.model.Teams
import com.qlue.footballapp.network.APIFootball
import io.reactivex.Flowable

class MatchRepoImpl(private val apiFootball: APIFootball) : MatchRepo {

    override fun getEventById(id: String): Flowable<Matches> = apiFootball.getEventById(id)

    override fun getLastMatch(id: String): Flowable<Matches> = apiFootball.getLastmatch(id)

    override fun getNextMatch(id: String): Flowable<Matches> = apiFootball.getNextMatch(id)

    override fun getTeams(id: String): Flowable<Teams> = apiFootball.getTeam(id)

    override fun searchMatches(query: String?) = apiFootball.searchMatches(query)

}