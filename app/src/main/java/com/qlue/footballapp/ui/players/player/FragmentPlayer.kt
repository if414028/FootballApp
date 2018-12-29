package com.qlue.footballapp.ui.players.player

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qlue.footballapp.R
import com.qlue.footballapp.adapter.PlayerAdapter
import com.qlue.footballapp.model.Player
import com.qlue.footballapp.model.Team
import com.qlue.footballapp.model.repo.PlayerRepoImpl
import com.qlue.footballapp.network.APIFootball
import com.qlue.footballapp.network.FootballService
import com.qlue.footballapp.utils.SchedulerProviderImpl
import com.qlue.footballapp.utils.hide
import com.qlue.footballapp.utils.show
import kotlinx.android.synthetic.main.fragment_players.*

class FragmentPlayer : Fragment(), PlayerMvpView {

    private var listPlayer: MutableList<Player> = mutableListOf()
    lateinit var mPresenter: PlayerPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val team: Team? = arguments?.getParcelable("teams")
        val service = FootballService.getClient().create(APIFootball::class.java)
        val request = PlayerRepoImpl(service)
        val scheduler = SchedulerProviderImpl()
        mPresenter = PlayerPresenter(this, request, scheduler)
        mPresenter.getAllPlayer(team?.idTeam)
    }

    override fun showLoading() {
        playerProgressbar.show()
        rvPlayer.visibility = View.GONE
    }

    override fun hideLoading() {
        playerProgressbar.hide()
        rvPlayer.visibility = View.VISIBLE
    }

    override fun displayPlayers(playerList: List<Player>) {
        listPlayer.clear()
        listPlayer.addAll(playerList)
        val layoutManager = LinearLayoutManager(context)
        rvPlayer.layoutManager = layoutManager
        rvPlayer.adapter = PlayerAdapter(listPlayer, context)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }
}