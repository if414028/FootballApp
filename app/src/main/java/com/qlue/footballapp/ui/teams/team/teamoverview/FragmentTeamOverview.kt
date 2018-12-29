package com.qlue.footballapp.ui.teams.team.teamoverview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.qlue.footballapp.R
import com.qlue.footballapp.model.Team
import kotlinx.android.synthetic.main.fragment_team_overview.*

class FragmentTeamOverview: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_overview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val team: Team? = arguments?.getParcelable("teams")
        initView(team)
    }

    fun initView(teamInfo: Team?){
        Glide.with(this)
            .load(teamInfo?.strTeamBadge)
            .apply(RequestOptions().placeholder(R.drawable.ic_action_load))
            .into(imgBadge)

        teamName.text = teamInfo?.strTeam
        tvFormed.text = teamInfo?.intFormedYear
        tvStadium.text = teamInfo?.strStadium
        teamOverview.text = teamInfo?.strDescriptionEN
    }
}