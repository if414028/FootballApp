package com.qlue.footballapp.ui.teams.team

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.qlue.footballapp.R
import com.qlue.footballapp.adapter.TeamAdapter
import android.support.v7.widget.SearchView
import com.qlue.footballapp.model.Team
import com.qlue.footballapp.model.repo.TeamRepoImpl
import com.qlue.footballapp.network.APIFootball
import com.qlue.footballapp.network.FootballService
import com.qlue.footballapp.utils.SchedulerProviderImpl
import com.qlue.footballapp.utils.hide
import com.qlue.footballapp.utils.show
import kotlinx.android.synthetic.main.fragment_team.*

class FragmentTeam : Fragment(), TeamMvpView {

    lateinit var mPresenter: TeamPresenter
    lateinit var leagueName: String

    private var teamLists: MutableList<Team> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = FootballService.getClient().create(APIFootball::class.java)
        val request = TeamRepoImpl(service)
        val scheduler = SchedulerProviderImpl()
        setHasOptionsMenu(true)
        mPresenter = TeamPresenter(this, request, scheduler)
        mPresenter.getTeamData("4328")
        val spinnerItem = resources.getStringArray(R.array.leagueName)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItem)
        spTeam.adapter = spinnerAdapter
        spTeam.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spTeam.selectedItem.toString()
                when (leagueName) {
                    "English Premier League" -> mPresenter.getTeamData("4328")
                    "German Bundesliga" -> mPresenter.getTeamData("4331")
                    "Italian Serie A" -> mPresenter.getTeamData("4332")
                    "French Ligue 1" -> mPresenter.getTeamData("4334")
                    "Spanish La Liga" -> mPresenter.getTeamData("4335")
                    "Netherlands Eredivisie" -> mPresenter.getTeamData("4337")
                    else -> mPresenter.getTeamData("4328")
                }
            }

        }
    }

    override fun displayTeam(teamList: List<Team>) {
        teamLists.clear()
        teamLists.addAll(teamList)
        val layoutManager = LinearLayoutManager(context)
        rvTeam.layoutManager = layoutManager
        rvTeam.adapter = TeamAdapter(teamLists, context)
    }

    override fun hideLoading() {
        teamProgressBar.hide()
        rvTeam.visibility = View.VISIBLE
    }

    override fun showLoading() {
        teamProgressBar.show()
        rvTeam.visibility = View.INVISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)
        val searchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?
        searchView?.queryHint = "Search team"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { mPresenter.searchTeam(it) }
                return false
            }
        })

        searchView?.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                mPresenter.getTeamData("4328")
                return true
            }

        })
    }
}