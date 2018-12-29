package com.qlue.footballapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.qlue.footballapp.R
import com.qlue.footballapp.model.Team
import com.qlue.footballapp.ui.teams.team.detail.TeamActivity
import kotlinx.android.synthetic.main.grid_team_item.view.*
import org.jetbrains.anko.startActivity

class TeamAdapter(val teamList: List<Team>, val context: Context?) :
    RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamAdapter.TeamViewHolder {
        return TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.grid_team_item, parent, false))
    }

    override fun getItemCount(): Int = teamList.size

    override fun onBindViewHolder(holder: TeamAdapter.TeamViewHolder, position: Int) {
        holder.bind(teamList[position])
    }

    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(team: Team) {
            itemView.tvTeam.text = team.strTeam
            Glide.with(itemView.context)
                .load(team.strTeamBadge)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_action_load)
                )
                .into(itemView.imgTeam)
            itemView.setOnClickListener {
                itemView.context.startActivity<TeamActivity>("team" to team)
            }
        }
    }
}