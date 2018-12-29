package com.qlue.footballapp.ui.players.Detail

import com.qlue.footballapp.model.repo.PlayerRepoImpl
import com.qlue.footballapp.utils.SchedulerProviderImpl
import io.reactivex.disposables.CompositeDisposable

class PlayerDetailPresenter(
    val mView: PlayerDetailMvpView,
    val playerRepoImpl: PlayerRepoImpl,
    val schedulerProviderImpl: SchedulerProviderImpl
) : PlayerDetailMvpPresenter {

    val compositeDisposable = CompositeDisposable()

    override fun getPlayerData(idPlayer: String) {
        compositeDisposable.add(playerRepoImpl.getDetailPlayer(idPlayer)
            .subscribeOn(schedulerProviderImpl.io())
            .observeOn(schedulerProviderImpl.ui())
            .subscribe {
                mView.displayPlayerDetail(it.player[0])
            })
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}