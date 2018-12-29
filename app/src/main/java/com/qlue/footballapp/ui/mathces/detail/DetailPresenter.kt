package com.qlue.footballapp.ui.mathces.detail

import com.qlue.footballapp.model.repo.LocalRepoImpl
import com.qlue.footballapp.model.repo.MatchRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailPresenter(val mView: DetailMvpView, val matchRepoImpl: MatchRepoImpl, val localRepoImpl: LocalRepoImpl) :
    DetailMvpPresenter {

    override fun deleteMatch(id: String) {
        localRepoImpl.deleteMatchData(id)
    }

    override fun checkMatch(id: String) {
        mView.setFavoriteState(localRepoImpl.checkFavMatch(id))
    }

    override fun insertMatch(matchId: String, homeId: String, awayId: String) {
        localRepoImpl.insertMatchData(matchId, homeId, awayId)
    }

    val compositeDisposable = CompositeDisposable()

    override fun getTeamBadgeHome(id: String) {
        compositeDisposable.add(matchRepoImpl.getTeams(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                mView.displayTeamBadgeHome(it.teams[0])
            })
    }

    override fun getTeamBadgeAway(id: String) {
        compositeDisposable.add(matchRepoImpl.getTeams(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                mView.displayTeamBadgeAway(it.teams[0])
            })
    }
}