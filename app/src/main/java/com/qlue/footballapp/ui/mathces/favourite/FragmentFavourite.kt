package com.qlue.footballapp.ui.mathces.favourite

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qlue.footballapp.R
import com.qlue.footballapp.adapter.ViewPagerAdpater
import com.qlue.footballapp.ui.mathces.favourite.match.FragmentFavouriteMatch
import com.qlue.footballapp.ui.mathces.favourite.team.FragmentFavouriteTeam

class FragmentFavourite : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vPager = view.findViewById<ViewPager>(R.id.viewpager)
        val tabs = view.findViewById<TabLayout>(R.id.tabs)
        val adapter = ViewPagerAdpater(childFragmentManager)
        adapter.populateFragment(FragmentFavouriteMatch(), "Favorite Match")
        adapter.populateFragment(FragmentFavouriteTeam(), "Favorite Team")
        vPager.adapter = adapter
        tabs.setupWithViewPager(vPager)
    }
}