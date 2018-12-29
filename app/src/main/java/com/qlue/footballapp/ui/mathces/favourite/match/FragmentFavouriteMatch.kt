package com.qlue.footballapp.ui.mathces.favourite.match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qlue.footballapp.R
import com.qlue.footballapp.adapter.FavouriteAdapter
import com.qlue.footballapp.model.Match
import com.qlue.footballapp.model.repo.LocalRepoImpl
import com.qlue.footballapp.model.repo.MatchRepoImpl
import com.qlue.footballapp.network.APIFootball
import com.qlue.footballapp.network.FootballService
import com.qlue.footballapp.utils.SchedulerProviderImpl
import com.qlue.footballapp.utils.hide
import com.qlue.footballapp.utils.show
import kotlinx.android.synthetic.main.fragment_favourite_match.*

class FragmentFavouriteMatch : Fragment(), FavouriteMatchMvpView {

    private var matchLists: MutableList<Match> = mutableListOf()
    lateinit var mPresenter: FavouriteMatchPresenter

    override fun hideLoading() {
        favouriteProgressBar.hide()
        rvFavourite.visibility = View.VISIBLE
    }

    override fun showLoading() {
        favouriteProgressBar.show()
        rvFavourite.visibility = View.INVISIBLE
    }

    override fun displayFootballMatch(matchList: List<Match>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvFavourite.layoutManager = layoutManager
        rvFavourite.adapter = FavouriteAdapter(matchList, context)
    }

    override fun hideSwipeRefresh() {
        FavouriteSwiperefresh.isRefreshing = false
        favouriteProgressBar.hide()
        rvFavourite.visibility = View.VISIBLE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favourite_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = FootballService.getClient().create(APIFootball::class.java)
        val request = MatchRepoImpl(service)
        val localRepositoryImpl = LocalRepoImpl(context!!)
        val scheduler = SchedulerProviderImpl()
        mPresenter = FavouriteMatchPresenter(
            this,
            request,
            localRepositoryImpl,
            scheduler
        )
        mPresenter.getFootballMatchData()

        FavouriteSwiperefresh.setOnRefreshListener {
            mPresenter.getFootballMatchData()
        }
    }
}