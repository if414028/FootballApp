package com.qlue.footballapp.ui.mathces.searchmatches

import com.qlue.footballapp.model.Match
import com.qlue.footballapp.model.Matches

interface SearchMatchesMvpView {

    fun showLoading()
    fun hideLoading()
    fun displayMatch(matchList: List<Match>)
}