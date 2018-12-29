package com.qlue.footballapp.ui.mathces.next

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qlue.footballapp.R
import com.qlue.footballapp.adapter.NextMatchAdapter
import com.qlue.footballapp.model.Match
import com.qlue.footballapp.model.repo.MatchRepoImpl
import com.qlue.footballapp.network.APIFootball
import com.qlue.footballapp.network.FootballService
import com.qlue.footballapp.utils.IdlingResource.FetchingIdlingResource
import com.qlue.footballapp.utils.SchedulerProviderImpl
import com.qlue.footballapp.utils.hide
import com.qlue.footballapp.utils.show
import kotlinx.android.synthetic.main.fragment_next_match.*

class FragmentNextMatch : Fragment(), NextMatchMvpView {

    lateinit var mPresenter: NextMatchPresenter

    private var matchLists: MutableList<Match> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = FootballService.getClient().create(APIFootball::class.java)
        val request = MatchRepoImpl(service)
        val scheduler = SchedulerProviderImpl()
        mPresenter = NextMatchPresenter(this, request, scheduler)
        mPresenter.getNextMatchData()

        nextMatchSwiperefresh.setOnRefreshListener {
            mPresenter.getNextMatchData()
        }
    }

    override fun hideLoading() {
        nextMatchProgressBar.hide()
    }

    override fun showLoading() {
        nextMatchProgressBar.show()
    }

    override fun hideSwipeRefresh() {
        nextMatchSwiperefresh.isRefreshing = false
        nextMatchProgressBar.hide()
        rvFootballNextMatch.visibility = View.VISIBLE
    }

    override fun displayNextMatch(mathcList: List<Match>) {
        matchLists.clear()
        matchLists.addAll(mathcList)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvFootballNextMatch.layoutManager = layoutManager
        rvFootballNextMatch.adapter = context?.let { NextMatchAdapter(mathcList, it) }
    }
}