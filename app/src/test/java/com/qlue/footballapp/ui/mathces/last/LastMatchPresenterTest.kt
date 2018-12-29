package com.qlue.footballapp.ui.mathces.last

import com.qlue.footballapp.model.Match
import com.qlue.footballapp.model.Matches
import com.qlue.footballapp.model.repo.MatchRepoImpl
import com.qlue.footballapp.utils.SchedulerProvider
import com.qlue.footballapp.utils.SchedulerProviderTest
import io.reactivex.Flowable
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LastMatchPresenterTest {

    @Mock
    lateinit var mView: LastMatchMvpView

    @Mock
    lateinit var matchRepoImpl: MatchRepoImpl

    lateinit var scheduler: SchedulerProvider

    lateinit var mPresenter: LastMatchPresenter

    lateinit var matches: Matches

    lateinit var footballmatches: Flowable<Matches>

    private val match = mutableListOf<Match>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        scheduler = SchedulerProviderTest()
        matches = Matches(match)
        footballmatches = Flowable.just(matches)
        mPresenter = LastMatchPresenter(mView, matchRepoImpl, scheduler)
        Mockito.`when`(matchRepoImpl.getLastMatch("4328")).thenReturn(footballmatches)
    }

    @Test
    fun getLastMatchData() {
        mPresenter.getLastMatchData()
        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).displayLastMatch(match)
        Mockito.verify(mView).hideLoading()
    }
}