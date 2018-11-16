package com.blogspot.ketikanmd.thesportdbapp.match

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.blogspot.ketikanmd.thesportdbapp.R
import com.blogspot.ketikanmd.thesportdbapp.models.PlayerModels
import com.squareup.picasso.Picasso
import org.jetbrains.anko.sdk25.coroutines.onClick

class PlayerAdapter(private val context: Context?, private var playerItems: List<PlayerModels>, private val listener: (PlayerModels) -> Unit)
    : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(playerItems[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(LayoutInflater.from(context).inflate(R.layout.player_items, parent, false))
    }

    override fun getItemCount(): Int = playerItems.size

    class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val playerName = view.findViewById(R.id.playerName) as TextView
        private val playerPosition = view.findViewById(R.id.playerPosition) as TextView
        private val playerImage = view.findViewById(R.id.playerImage) as ImageView
        fun bindItem(playerItems: PlayerModels, listener: (PlayerModels) -> Unit) {
            Picasso.get().load(playerItems.strCutout).into(playerImage)
            playerName.text = playerItems.strPlayer
            playerPosition.text = playerItems.strPosition
            itemView.onClick { listener(playerItems) }
        }
    }
    fun refreshPlayer(fill: List<PlayerModels>) {
        playerItems = fill
        notifyDataSetChanged()
    }

}