package com.qlue.footballapp.ui.mathces.next

import com.qlue.footballapp.model.repo.MatchRepoImpl
import com.qlue.footballapp.utils.IdlingResource.FetcherListener
import com.qlue.footballapp.utils.IdlingResource.FetchingIdlingResource
import com.qlue.footballapp.utils.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NextMatchPresenter(val mView: NextMatchMvpView,
                         val matchRepoImpl: MatchRepoImpl,
                         val schedulers: SchedulerProvider) : NextMatchMvpPresenter {

    val compositeDisposable = CompositeDisposable()

    override fun getNextMatchData() {
        mView.showLoading()
        compositeDisposable.add(matchRepoImpl.getNextMatch("4328")
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
            .subscribe {
                mView.displayNextMatch(it.match)
                mView.hideLoading()
                mView.hideSwipeRefresh()
            })
    }
}