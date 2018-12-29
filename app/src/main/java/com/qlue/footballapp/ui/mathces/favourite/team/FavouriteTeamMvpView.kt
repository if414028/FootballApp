package com.qlue.footballapp.ui.mathces.favourite.team

import com.qlue.footballapp.model.Team

interface FavouriteTeamMvpView {

    fun dispayTeams(teamList: List<Team>)
    fun hideLoading()
    fun showLoading()
    fun hideSwipeRefresh()

}