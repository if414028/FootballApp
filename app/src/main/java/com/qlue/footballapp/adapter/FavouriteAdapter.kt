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
import org.jetbrains.anko.startActivity

class FavouriteAdapter(val matchList: List<Match>, val context: Context?) :
    RecyclerView.Adapter<FavouriteAdapter.FavViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteAdapter.FavViewHolder {
        return FavViewHolder(LayoutInflater.from(context).inflate(R.layout.card_match, parent, false))
    }

    override fun getItemCount(): Int = matchList.size

    override fun onBindViewHolder(holder: FavouriteAdapter.FavViewHolder, position: Int) {
        holder.bind(matchList[position])
    }

    inner class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(match: Match) {
            itemView.tvMatchDate.setText(match.dateEvent)
            itemView.tvHomeName.setText(match.strHomeTeam)
            itemView.tvHomeScore.setText(match.intHomeScore)
            itemView.tvAwayName.setText(match.strAwayTeam)
            itemView.tvAwayScore.setText(match.intAwayScore)

            itemView.setOnClickListener {
                itemView.context.startActivity<DetailActivity>("match" to match)
            }
        }
    }
}