package com.qlue.footballapp.ui.mathces.favourite.match

import com.qlue.footballapp.model.Match
import com.qlue.footballapp.model.repo.LocalRepoImpl
import com.qlue.footballapp.model.repo.MatchRepoImpl
import com.qlue.footballapp.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class FavouriteMatchPresenter(
    val mView: FavouriteMatchMvpView,
    val matchRepositoryImpl: MatchRepoImpl,
    val localRepositoryImpl: LocalRepoImpl,
    val scheduler: SchedulerProvider
) : FavouriteMatchMvpPresenter {

    val compositeDisposable = CompositeDisposable()

    override fun getFootballMatchData() {
        mView.showLoading()
        val favList = localRepositoryImpl.getMatchFromDb()
        var eventList: MutableList<Match> = mutableListOf()
        for (fav in favList){
            compositeDisposable.add(matchRepositoryImpl.getEventById(fav.idEvent)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribe{
                    eventList.add(it.match[0])
                    mView.displayFootballMatch(eventList)
                    mView.hideLoading()
                    mView.hideSwipeRefresh()
                })
        }

        if(favList.isEmpty()){
            mView.hideLoading()
            mView.hideSwipeRefresh()
            mView.displayFootballMatch(eventList)
        }
    }

}