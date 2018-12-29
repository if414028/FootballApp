package com.qlue.footballapp.ui.mathces.searchmatches

import com.qlue.footballapp.model.repo.MatchRepoImpl
import com.qlue.footballapp.utils.SchedulerProviderImpl
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class SearchMathcesPresenter(
    val mView: SearchMatchesMvpView,
    val matchRepoImpl: MatchRepoImpl,
    val schedulerProviderImpl: SchedulerProviderImpl
) : SearchMatchesMvpPresenter {

    val compositeDisposable = CompositeDisposable()

    override fun searchMatch(query: String?) {
        mView.showLoading()
        compositeDisposable.add(matchRepoImpl.searchMatches(query)
            .subscribeOn(schedulerProviderImpl.io())
            .observeOn(schedulerProviderImpl.ui())
            .subscribe {
                mView.hideLoading()
                mView.displayMatch(it.match ?: Collections.emptyList())
            })
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}