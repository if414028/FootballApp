package com.qlue.footballapp.ui.teams.team

interface TeamMvpPresenter {
    fun getTeamData(leagueName: String)
    fun searchTeam(teamName: String)
    fun onDestroy()
}