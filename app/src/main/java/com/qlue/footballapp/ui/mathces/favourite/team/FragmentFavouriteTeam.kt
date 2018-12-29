package com.qlue.footballapp.ui.mathces.favourite.team

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qlue.footballapp.R
import com.qlue.footballapp.adapter.FavouriteTeamAdapter
import com.qlue.footballapp.adapter.TeamAdapter
import com.qlue.footballapp.model.Team
import com.qlue.footballapp.model.repo.LocalRepoImpl
import com.qlue.footballapp.model.repo.TeamRepoImpl
import com.qlue.footballapp.network.APIFootball
import com.qlue.footballapp.network.FootballService
import com.qlue.footballapp.utils.SchedulerProviderImpl
import com.qlue.footballapp.utils.hide
import com.qlue.footballapp.utils.show
import kotlinx.android.synthetic.main.fragment_favourite_match.*
import kotlinx.android.synthetic.main.fragment_favourite_team.*

class FragmentFavouriteTeam : Fragment(), FavouriteTeamMvpView {

    private var teamLists: MutableList<Team> = mutableListOf()
    lateinit var mPresenter: FavouriteTeamPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favourite_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val sercive = FootballService.getClient().create(APIFootball::class.java)
        val localRepositoryImpl = LocalRepoImpl(context!!)
        val teamRepositoryImpl = TeamRepoImpl(sercive)
        val scheduler = SchedulerProviderImpl()
        mPresenter = FavouriteTeamPresenter(this, localRepositoryImpl, teamRepositoryImpl, scheduler)
        mPresenter.getTeamData()

        favouriteTeamSwiperefresh.setOnRefreshListener {
            mPresenter.getTeamData()
        }
    }

    override fun dispayTeams(teamList: List<Team>) {
        teamLists.clear()
        teamLists.addAll(teamList)
        val layoutManager = GridLayoutManager(context, 3)
        rvFavouriteTeam.layoutManager = layoutManager
        rvFavouriteTeam.adapter = FavouriteTeamAdapter(teamLists, context)
    }

    override fun hideLoading() {
        favouriteTeamProgressBar.hide()
        rvFavouriteTeam.show()
    }

    override fun showLoading() {
        favouriteTeamProgressBar.show()
        rvFavouriteTeam.hide()
    }

    override fun hideSwipeRefresh() {
        favouriteTeamSwiperefresh.isRefreshing = false
        favouriteTeamProgressBar.hide()
        rvFavouriteTeam.show()
    }
}