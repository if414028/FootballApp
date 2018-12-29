package com.qlue.footballapp.ui.players.player

import com.qlue.footballapp.model.repo.PlayerRepoImpl
import com.qlue.footballapp.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class PlayerPresenter(
    val mView: PlayerMvpView,
    val playerRepoImpl: PlayerRepoImpl,
    val schedulerProvider: SchedulerProvider
) : PlayerMvpPresenter {

    val compositeDisposable = CompositeDisposable()

    override fun getAllPlayer(teamId: String?) {
        mView.showLoading()
        compositeDisposable.add(playerRepoImpl.getAllPlayer(teamId)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe {
                mView.hideLoading()
                mView.displayPlayers(it.player)
            })
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}