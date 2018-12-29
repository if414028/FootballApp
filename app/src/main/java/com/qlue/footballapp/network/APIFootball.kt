package com.qlue.footballapp.network

import com.qlue.footballapp.model.*
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface APIFootball {

    @GET("eventspastleague.php")
    fun getLastmatch(@Query("id") id:String) : Flowable<Matches>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id:String) : Flowable<Matches>

    @GET("lookupteam.php")
    fun getTeam(@Query("id") id:String) : Flowable<Teams>

    @GET("lookupevent.php")
    fun getEventById(@Query("id") id:String) : Flowable<Matches>

    @GET("searchevents.php")
    fun searchMatches(@Query("e") query: String?) : Flowable<SearchMatchResult>

    @GET("searchteams.php")
    fun getTeamBySearch(@Query("t") query: String) : Flowable<Teams>

    @GET("lookup_all_teams.php")
    fun getAllTeam(@Query("id") id:String) : Flowable<Teams>

    @GET("lookup_all_players.php")
    fun getAllPlayers(@Query("id") id:String?) : Flowable<TeamMember>

    @GET("lookupplayer.php")
    fun getPlayerDetail(@Query("id") id:String?) : Flowable<Players>
}