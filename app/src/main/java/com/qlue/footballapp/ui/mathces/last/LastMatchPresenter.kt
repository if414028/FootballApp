package com.qlue.footballapp.ui.mathces.last

import com.qlue.footballapp.model.repo.MatchRepoImpl
import com.qlue.footballapp.utils.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LastMatchPresenter(val mView: LastMatchMvpView,
                         val matchRepoImpl: MatchRepoImpl,
                         val schedulers: SchedulerProvider) : LastMatchMvpPresenter {

    val compositeDisposable = CompositeDisposable()

    override fun getLastMatchData() {
        mView.showLoading()
        compositeDisposable.add(matchRepoImpl.getLastMatch("4328")
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
            .subscribe {
                mView.displayLastMatch(it.match)
                mView.hideLoading()
                mView.hideSwipeRefresh()
            })
    }

}