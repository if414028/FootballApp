package com.qlue.footballapp.ui.mathces.searchmatches

interface SearchMatchesMvpPresenter {
    fun searchMatch(query: String?)
    fun onDestroy()
}