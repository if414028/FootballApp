package com.qlue.footballapp.ui.mathces.favourite.match

import com.qlue.footballapp.model.Match

interface FavouriteMatchMvpView {

    fun hideLoading()
    fun showLoading()
    fun displayFootballMatch(matchList: List<Match>)
    fun hideSwipeRefresh()

}