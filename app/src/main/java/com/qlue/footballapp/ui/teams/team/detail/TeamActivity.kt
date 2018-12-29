package com.qlue.footballapp.ui.teams.team.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.qlue.footballapp.R
import com.qlue.footballapp.adapter.ViewPagerAdpater
import com.qlue.footballapp.model.Team
import com.qlue.footballapp.model.db.FavouriteTeam
import com.qlue.footballapp.model.repo.LocalRepoImpl
import com.qlue.footballapp.ui.players.player.FragmentPlayer
import com.qlue.footballapp.ui.teams.team.teamoverview.FragmentTeamOverview
import kotlinx.android.synthetic.main.activity_team.*
import org.jetbrains.anko.toast

class TeamActivity : AppCompatActivity(), TeamDetailMvpView {

    private var isFavourite: Boolean = false
    private var menuItem: Menu? = null

    lateinit var team: Team
    lateinit var mPresenter: TeamDetailPresenter

    override fun setFavoriteState(favList: List<FavouriteTeam>) {
        if (!favList.isEmpty()) isFavourite = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)
        setSupportActionBar(toolbar)

        team = if (savedInstanceState != null) {
            savedInstanceState.getParcelable("team")
        } else {
            intent.getParcelableExtra("team")
        }
        val bundle = Bundle()
        bundle.putParcelable("teams", team)
        supportActionBar?.title = team.strTeam
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val localRepo = LocalRepoImpl(applicationContext)
        mPresenter = TeamDetailPresenter(this, localRepo)
        mPresenter.checkTeam(team.idTeam)

        loadImage()

        val adapter = ViewPagerAdpater(supportFragmentManager)
        val teamFragment = FragmentTeamOverview()
        val playersFragment = FragmentPlayer()
        teamFragment.arguments = bundle
        playersFragment.arguments = bundle
        adapter.populateFragment(teamFragment, "Team Overview")
        adapter.populateFragment(playersFragment, "Players")
        viewpagerTeam.adapter = adapter
        tabs.setupWithViewPager(viewpagerTeam)

    }

    private fun loadImage() {
        if (!team.strTeamFanart1.equals(null)) {
            Glide.with(applicationContext)
                .load(team.strTeamFanart1)
                .apply(
                    RequestOptions().placeholder(
                        R.drawable.ic_action_load
                    )
                )
                .apply(RequestOptions().override(220, 160))
                .into(imageTeam)
        } else {
            Glide.with(applicationContext)
                .load(team.strTeamBadge)
                .apply(RequestOptions().placeholder(R.drawable.ic_action_load))
                .apply(RequestOptions().override(120, 140))
                .into(imageTeam)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.team_menu, menu)
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
                if (!isFavourite) {
                    mPresenter.insertTeam(team.idTeam, team.strTeamBadge)
                    toast("Team added to favorite")
                    isFavourite = !isFavourite
                } else {
                    mPresenter.deleteTeam(team.idTeam)
                    toast("Team removed from favorite")
                    isFavourite = !isFavourite
                }
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavourite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.start_added)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.star)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable("team", team)

    }
}
