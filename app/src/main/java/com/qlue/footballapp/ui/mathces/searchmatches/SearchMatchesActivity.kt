package com.qlue.footballapp.ui.mathces.searchmatches

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import com.qlue.footballapp.R
import com.qlue.footballapp.adapter.MatchAdapter
import com.qlue.footballapp.model.Match
import com.qlue.footballapp.model.repo.MatchRepoImpl
import com.qlue.footballapp.network.APIFootball
import com.qlue.footballapp.network.FootballService
import com.qlue.footballapp.utils.SchedulerProviderImpl
import com.qlue.footballapp.utils.hide
import com.qlue.footballapp.utils.show
import kotlinx.android.synthetic.main.activity_search_matches.*

class SearchMatchesActivity : AppCompatActivity(), SearchMatchesMvpView {

    private var matchLists: MutableList<Match> = mutableListOf()
    lateinit var mPresenter: SearchMathcesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_matches)

        val query = intent.getStringExtra("query")
        Log.v("test", query)
        val service = FootballService.getClient().create(APIFootball::class.java)
        val request = MatchRepoImpl(service)
        val scheduler = SchedulerProviderImpl()
        mPresenter = SearchMathcesPresenter(this, request, scheduler)
        mPresenter.searchMatch(query)
    }

    override fun showLoading() {
        searchMatchesProgressBar.show()
        rvSearchMatch.hide()
    }

    override fun hideLoading() {
        searchMatchesProgressBar.hide()
        rvSearchMatch.show()
    }

    override fun displayMatch(matchList: List<Match>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        val layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        rvSearchMatch.layoutManager = layoutManager
        rvSearchMatch.adapter = MatchAdapter(matchList, applicationContext)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?
        searchView?.queryHint = "Search Matches"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mPresenter.searchMatch(newText)
                return false
            }
        })
        return true
    }
}
