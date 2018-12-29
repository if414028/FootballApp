package com.qlue.footballapp.ui.teams.team.detail

interface TeamDetailMvpPresneter {
    fun deleteTeam(id: String)
    fun checkTeam(id: String)
    fun insertTeam(id: String, imageUrl: String)
}