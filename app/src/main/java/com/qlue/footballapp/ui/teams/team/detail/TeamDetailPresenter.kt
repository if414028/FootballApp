package com.qlue.footballapp.ui.teams.team.detail

import com.qlue.footballapp.model.repo.LocalRepoImpl

class TeamDetailPresenter(
    val mView: TeamDetailMvpView,
    val localRepoImpl: LocalRepoImpl
) : TeamDetailMvpPresneter {

    override fun deleteTeam(id: String) {
        localRepoImpl.deleteTeamData(id)
    }

    override fun checkTeam(id: String) {
        mView.setFavoriteState(localRepoImpl.checkFavTeam(id))
    }

    override fun insertTeam(id: String, imageUrl: String) {
        localRepoImpl.insertTeamData(id, imageUrl)
    }
}