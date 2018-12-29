package com.qlue.footballapp.ui.mathces.last

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qlue.footballapp.R
import com.qlue.footballapp.adapter.LastMatchAdapter
import com.qlue.footballapp.model.Match
import com.qlue.footballapp.model.repo.MatchRepoImpl
import com.qlue.footballapp.network.APIFootball
import com.qlue.footballapp.network.FootballService
import com.qlue.footballapp.utils.SchedulerProviderImpl
import com.qlue.footballapp.utils.hide
import com.qlue.footballapp.utils.show
import kotlinx.android.synthetic.main.fragment_favourite_match.*
import kotlinx.android.synthetic.main.fragment_last_match.*

class FragmentLastMatch : Fragment(), LastMatchMvpView {

    lateinit var mPresenter: LastMatchPresenter

    private var matchLists: MutableList<Match> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = FootballService.getClient().create(APIFootball::class.java)
        val request = MatchRepoImpl(service)
        val scheduler = SchedulerProviderImpl()
        mPresenter = LastMatchPresenter(this, request, scheduler)
        mPresenter.getLastMatchData()

        lastMatchSwiperefresh.setOnRefreshListener {
            mPresenter.getLastMatchData()
        }
    }

    override fun hideSwipeRefresh() {
        lastMatchSwiperefresh.isRefreshing = false
        lastMatchProgressBar.hide()
        rvFootballLastMatch.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        lastMatchProgressBar.hide()
    }

    override fun showLoading() {
        lastMatchProgressBar.show()
    }

    override fun displayLastMatch(matchList: List<Match>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvFootballLastMatch.layoutManager = layoutManager
        rvFootballLastMatch.adapter = context?.let { LastMatchAdapter(matchList, it) }
    }
}