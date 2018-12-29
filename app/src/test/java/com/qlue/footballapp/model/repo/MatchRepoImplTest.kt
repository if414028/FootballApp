package com.qlue.footballapp.model.repo

import com.qlue.footballapp.model.Match
import com.qlue.footballapp.model.Matches
import com.qlue.footballapp.network.APIFootball
import com.qlue.footballapp.ui.mathces.last.LastMatchMvpView
import com.qlue.footballapp.ui.mathces.last.LastMatchPresenter
import com.qlue.footballapp.utils.SchedulerProvider
import com.qlue.footballapp.utils.SchedulerProviderTest
import io.reactivex.Flowable
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchRepoImplTest {

    @Mock
    lateinit var APIFootball : APIFootball

    lateinit var matchRepoImpl: MatchRepoImpl

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        matchRepoImpl = MatchRepoImpl(APIFootball)

    }

    @Test
    fun getEventById(){
        matchRepoImpl.getEventById("111")
        Mockito.verify(APIFootball).getEventById("111")
    }

    @Test
    fun getNextMatch(){
        matchRepoImpl.getNextMatch("111")
        Mockito.verify(APIFootball).getNextMatch("111")
    }

    @Test
    fun getLastMatch(){
        matchRepoImpl.getLastMatch("111")
        Mockito.verify(APIFootball).getLastmatch("111")
    }

    @Test
    fun getTeams(){
        matchRepoImpl.getTeams("111")
        Mockito.verify(APIFootball).getTeam("111")
    }
}