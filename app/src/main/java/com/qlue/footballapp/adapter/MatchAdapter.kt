package com.qlue.footballapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qlue.footballapp.R
import com.qlue.footballapp.model.Match
import com.qlue.footballapp.ui.mathces.detail.DetailActivity
import kotlinx.android.synthetic.main.card_match.view.*
import org.jetbrains.anko.*

class MatchAdapter(
    val matchList: List<Match>,
    val context: Context?
) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchAdapter.MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.card_match, parent, false))
    }

    override fun getItemCount(): Int = matchList.size

    override fun onBindViewHolder(holder: MatchAdapter.MatchViewHolder, position: Int) {
        holder.bind(matchList[position])
    }

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(match: Match) {
            itemView.tvMatchDate.text = match.dateEvent
            itemView.tvHomeName.text = match.strHomeTeam
            itemView.tvHomeScore.text = match.intHomeScore
            itemView.tvAwayName.text = match.strAwayTeam
            itemView.tvAwayScore.text = match.intAwayScore

            itemView.setOnClickListener {
                itemView.context.startActivity<DetailActivity>("match" to match)
            }
        }
    }
}