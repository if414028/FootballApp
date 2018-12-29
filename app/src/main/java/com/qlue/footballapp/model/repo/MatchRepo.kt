package com.qlue.footballapp.model.repo

import com.qlue.footballapp.model.Matches
import com.qlue.footballapp.model.SearchMatchResult
import com.qlue.footballapp.model.Teams
import io.reactivex.Flowable

interface MatchRepo {

    fun getLastMatch(id: String): Flowable<Matches>

    fun getNextMatch(id: String): Flowable<Matches>

    fun getTeams(id: String = "0"): Flowable<Teams>

    fun getEventById(id: String): Flowable<Matches>

    fun searchMatches(query: String?): Flowable<SearchMatchResult>
}