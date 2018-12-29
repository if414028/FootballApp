package com.qlue.footballapp.ui.players.player

import com.qlue.footballapp.model.Player

interface PlayerMvpView {
    fun showLoading()
    fun hideLoading()
    fun displayPlayers(playerList: List<Player>)
}