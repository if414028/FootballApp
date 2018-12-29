package com.qlue.footballapp.ui.mathces.match

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import android.view.*
import com.qlue.footballapp.R
import com.qlue.footballapp.adapter.ViewPagerAdpater
import com.qlue.footballapp.ui.mathces.last.FragmentLastMatch
import com.qlue.footballapp.ui.mathces.next.FragmentNextMatch
import com.qlue.footballapp.ui.mathces.searchmatches.SearchMatchesActivity
import org.jetbrains.anko.startActivity

class FragmentMatch : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vPager = view.findViewById<ViewPager>(R.id.viewpager)
        val tabs = view.findViewById<TabLayout>(R.id.tabs)
        val adapter = ViewPagerAdpater(childFragmentManager)
        setHasOptionsMenu(true)
        adapter.populateFragment(FragmentLastMatch(), "Last Match")
        adapter.populateFragment(FragmentNextMatch(), "Upcoming")
        vPager.adapter = adapter
        tabs.setupWithViewPager(vPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.search_menu, menu)

        val searchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?

        searchView?.queryHint = "Search matches"

        searchView?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                context?.startActivity<SearchMatchesActivity>("query" to query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })
    }
}