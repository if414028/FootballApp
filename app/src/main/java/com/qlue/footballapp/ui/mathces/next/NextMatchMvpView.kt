package com.qlue.footballapp.ui.mathces.next

import com.qlue.footballapp.model.Match

interface NextMatchMvpView {
    fun hideLoading()
    fun showLoading()
    fun hideSwipeRefresh()
    fun displayNextMatch(mathcList: List<Match>)
}