package com.qlue.footballapp.ui.players.player

interface PlayerMvpPresenter {
    fun getAllPlayer(teamId: String?)
    fun onDestroy()
}