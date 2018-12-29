package com.qlue.footballapp.ui.mathces.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.qlue.footballapp.R
import com.qlue.footballapp.model.Match
import com.qlue.footballapp.model.Team
import com.qlue.footballapp.model.db.FavouriteMatch
import com.qlue.footballapp.model.repo.LocalRepoImpl
import com.qlue.footballapp.model.repo.MatchRepoImpl
import com.qlue.footballapp.network.APIFootball
import com.qlue.footballapp.network.FootballService
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity(), DetailMvpView {

    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    lateinit var match: Match

    lateinit var mPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val service = FootballService.getClient().create(APIFootball::class.java)
        val request = MatchRepoImpl(service)
        val localRepo = LocalRepoImpl(applicationContext)
        mPresenter = DetailPresenter(this, request, localRepo)

        match = intent.getParcelableExtra<Match>("match")
        mPresenter.getTeamBadgeHome(match.idHomeTeam)
        mPresenter.getTeamBadgeAway(match.idAwayTeam)
        match.idEvent?.let { mPresenter.checkMatch(it) }
        fetchData(match)
        supportActionBar?.title = match.strEvent
    }

    override fun displayTeamBadgeHome(team: Team) {
        Glide.with(applicationContext)
            .load(team.strTeamBadge)
            .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
            .into(homeImg)
    }

    override fun displayTeamBadgeAway(team: Team) {
        Glide.with(applicationContext)
            .load(team.strTeamBadge)
            .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
            .into(awayImg)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fav_menu, menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.favorite -> {
                if (!isFavorite){
                    match.idEvent?.let {
                        mPresenter.insertMatch(
                            it, match.idHomeTeam, match.idAwayTeam)
                    }
                    toast("Match added to Favourite")
                    isFavorite = !isFavorite
                }else{
                    match.idEvent?.let { mPresenter.deleteMatch(it) }
                    toast("Match removed to Favourite")
                    isFavorite = !isFavorite
                }
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.start_added)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.star)
    }

    override fun setFavoriteState(favList: List<FavouriteMatch>) {
        if(!favList.isEmpty()) isFavorite = true
    }

    fun fetchData(match: Match) {
        tvScheduleDate.text = match.dateEvent
        tvHomeDetailName.text = match.strHomeTeam
        tvHomeDetailScore.text = match.intHomeScore
        tvAwayDetailName.text = match.strAwayTeam
        tvAwayDetailScore.text = match.intAwayScore

        tvHomeShoots.text = match.intHomeShoots
        tvAwayShoots.text = match.intAwayShots

        tvHomeGoals.text = match.strHomeGoalDetails
        tvAwayGoals.text = match.strAwayGoalDetails

        tvGkHome.text = match.strHomeLineupGoalkeeper
        tvGkAway.text = match.strAwayLineupGoalkeeper

        tvDefHome.text = match.strHomeLineupDefense
        tvDefAway.text = match.strAwayLineupDefense

        tvMidHome.text = match.strHomeLineupMidfield
        tvMidAway.text = match.strAwayLineupMidfield

        tvForwardHome.text = match.strHomeLineupForward
        tvForwardAway.text = match.strAwayLineupForward

        tvSubHome.text = match.strHomeLineupSubstitutes
        tvSubAway.text = match.strAwayLineupSubstitutes
    }
}
