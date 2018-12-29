package com.qlue.footballapp.ui.players.Detail

interface PlayerDetailMvpPresenter {
    fun getPlayerData(idPlayer: String)
    fun onDestroy()
}