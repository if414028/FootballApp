package com.qlue.footballapp.ui.mathces.detail

interface DetailMvpPresenter {
    fun getTeamBadgeHome(id: String)
    fun getTeamBadgeAway(id:String)
    fun deleteMatch(id:String)
    fun checkMatch(id:String)
    fun insertMatch(matchId: String, homeId: String, awayId: String)
}