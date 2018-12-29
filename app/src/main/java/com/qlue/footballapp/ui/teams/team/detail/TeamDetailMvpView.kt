package com.qlue.footballapp.ui.teams.team.detail

import com.qlue.footballapp.model.db.FavouriteTeam

interface TeamDetailMvpView {
    fun setFavoriteState(favList: List<FavouriteTeam>)
}