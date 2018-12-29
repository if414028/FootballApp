package com.qlue.footballapp.ui.teams.team

import com.qlue.footballapp.model.Team

interface TeamMvpView {
    fun displayTeam(teamList: List<Team>)
    fun hideLoading()
    fun showLoading()
}