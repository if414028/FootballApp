package com.qlue.footballapp.ui.mathces.last

import com.qlue.footballapp.model.Match

interface LastMatchMvpView {
    fun hideLoading()
    fun showLoading()
    fun hideSwipeRefresh()
    fun displayLastMatch(matchList: List<Match>)
}