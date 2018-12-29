package com.qlue.footballapp.ui.mathces.detail

import com.qlue.footballapp.model.Team
import com.qlue.footballapp.model.db.FavouriteMatch

interface DetailMvpView {
    fun displayTeamBadgeHome(team: Team)
    fun displayTeamBadgeAway(team: Team)
    fun setFavoriteState(favList:List<FavouriteMatch>)
}