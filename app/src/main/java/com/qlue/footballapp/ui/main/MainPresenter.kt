package com.qlue.footballapp.ui.main

import com.qlue.footballapp.model.repo.MatchRepoImpl
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(val mView: MainMvpView, val matchRepositoryImpl: MatchRepoImpl) : MainMvpPresenter {
    val compositeDisposable = CompositeDisposable()
}