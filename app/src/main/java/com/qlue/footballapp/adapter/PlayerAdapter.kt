package com.qlue.footballapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.qlue.footballapp.R
import com.qlue.footballapp.model.Player
import com.qlue.footballapp.ui.players.Detail.PlayerActivity
import kotlinx.android.synthetic.main.player_item.view.*
import org.jetbrains.anko.startActivity

class PlayerAdapter(
    val playerList: List<Player>,
    val context: Context?
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(LayoutInflater.from(context).inflate(R.layout.player_item, parent, false))
    }

    override fun getItemCount() = playerList.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(playerList[position])
    }

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(player: Player) {
            itemView.tvPlayer.text = player.strPlayer
            itemView.tvPosition.text = player.strPosition
            Glide.with(itemView.context)
                .load(player.strCutout)
                .apply(RequestOptions().placeholder(R.drawable.ic_action_load))
                .apply(RequestOptions().override(120, 140))
                .into(itemView.imgPlayer)

            itemView.setOnClickListener {
                itemView.context.startActivity<PlayerActivity>("player" to player)
            }
        }
    }
}