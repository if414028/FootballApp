package com.qlue.footballapp.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.qlue.footballapp.R
import com.qlue.footballapp.ui.mathces.favourite.FragmentFavourite
import com.qlue.footballapp.ui.mathces.favourite.match.FragmentFavouriteMatch
import com.qlue.footballapp.ui.mathces.match.FragmentMatch
import com.qlue.footballapp.ui.teams.team.FragmentTeam
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainMvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.lastMatch -> {
                    loadLastMatch(savedInstanceState)
                }
                R.id.upMatch -> {
                    loadUpcomingMatch(savedInstanceState)
                }
                R.id.favMatch -> {
                    loadFavoritesMatch(savedInstanceState)
                }
            }
            true
        }

        bottom_navigation.selectedItemId = R.id.lastMatch
    }

    private fun loadLastMatch(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FragmentMatch(), FragmentMatch::class.java.simpleName)
                .commit()
        }
    }

    private fun loadUpcomingMatch(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FragmentTeam(), FragmentTeam::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoritesMatch(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.main_container,
                    FragmentFavourite(), FragmentFavourite::class.java.simpleName
                )
                .commit()
        }
    }
}
