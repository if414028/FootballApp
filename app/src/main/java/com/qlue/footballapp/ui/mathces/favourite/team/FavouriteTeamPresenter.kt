package com.qlue.footballapp.ui.mathces.favourite.team

import com.qlue.footballapp.model.Team
import com.qlue.footballapp.model.repo.LocalRepoImpl
import com.qlue.footballapp.model.repo.TeamRepoImpl
import com.qlue.footballapp.utils.SchedulerProviderImpl
import io.reactivex.disposables.CompositeDisposable

class FavouriteTeamPresenter(
    val mView: FavouriteTeamMvpView,
    val localRepoImpl: LocalRepoImpl,
    val teamRepoImpl: TeamRepoImpl,
    val schedulerProviderImpl: SchedulerProviderImpl
) : FavouriteTeamMvpPresenter {

    val compositeDisposable = CompositeDisposable()

    override fun getTeamData() {
        mView.showLoading()
        val teamList = localRepoImpl.getTeamFromDb()
        var teamLists: MutableList<Team> = mutableListOf()
        for (fav in teamList) {
            compositeDisposable.add(teamRepoImpl.getDetailTeam(fav.idTeam)
                .observeOn(schedulerProviderImpl.ui())
                .subscribeOn(schedulerProviderImpl.io())
                .subscribe {
                    teamLists.add(it.teams[0])
                    mView.dispayTeams(teamLists)
                    mView.hideLoading()
                    mView.hideSwipeRefresh()
                })
        }

        if (teamList.isEmpty()) {
            mView.hideLoading()
            mView.hideSwipeRefresh()
            mView.dispayTeams(teamLists)
        }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}