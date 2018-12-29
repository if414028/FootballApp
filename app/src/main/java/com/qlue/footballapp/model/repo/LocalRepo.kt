package com.qlue.footballapp.model.repo

import com.qlue.footballapp.model.db.FavouriteMatch
import com.qlue.footballapp.model.db.FavouriteTeam

interface LocalRepo {

    //match
    fun getMatchFromDb(): List<FavouriteMatch>

    fun insertMatchData(eventId: String, homeId: String, awayId: String)

    fun deleteMatchData(eventId: String)

    fun checkFavMatch(eventId: String): List<FavouriteMatch>

    //team
    fun getTeamFromDb(): List<FavouriteTeam>

    fun insertTeamData(teamId: String, imageUrl: String)

    fun deleteTeamData(teamId: String)

    fun checkFavTeam(teamId: String): List<FavouriteTeam>
}