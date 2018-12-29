package com.qlue.footballapp.ui.teams.team

import com.qlue.footballapp.model.repo.TeamRepoImpl
import com.qlue.footballapp.utils.SchedulerProviderImpl
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class TeamPresenter(val mView: TeamMvpView, val teamRepoImpl: TeamRepoImpl, val scheduler: SchedulerProviderImpl) :
    TeamMvpPresenter {

    val compositeDisposable = CompositeDisposable()

    override fun getTeamData(leagueName: String) {
        mView.showLoading()
        compositeDisposable.add(teamRepoImpl.getAllTeam(leagueName)
            .observeOn(scheduler.ui())
            .subscribeOn(scheduler.io())
            .subscribe {
                mView.displayTeam(it.teams)
                mView.hideLoading()
            })
    }

    override fun searchTeam(teamName: String) {
        mView.showLoading()
        compositeDisposable.add(teamRepoImpl.getTeamBySearch(teamName)
            .observeOn(scheduler.ui())
            .subscribeOn(scheduler.io())
            .subscribe {
                mView.displayTeam(it.teams ?: Collections.emptyList())
                mView.hideLoading()
            })
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}