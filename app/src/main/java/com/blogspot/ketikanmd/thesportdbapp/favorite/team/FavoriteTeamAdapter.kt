package com.blogspot.ketikanmd.thesportdbapp.favorite.match

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.blogspot.ketikanmd.thesportdbapp.R
import com.squareup.picasso.Picasso
import org.jetbrains.anko.sdk25.coroutines.onClick

class FavoriteTeamAdapter(private val context: Context?, private var favoriteTeam: List<FavoriteTeam>, private val listener: (FavoriteTeam) -> Unit) : RecyclerView.Adapter<FavoriteTeamAdapter.MatchViewHolder>() {
    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(favoriteTeam[position], listener)
    }

    override fun getItemCount(): Int = favoriteTeam.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.team_items, parent, false))

    }

    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var logoImage = view.findViewById(R.id.team_logo) as ImageView
        private val nameTeam = view.findViewById(R.id.name_team) as TextView
        private val stadiumTeam = view.findViewById(R.id.stadium_team) as TextView

        fun bindItem(favoriteTeam: FavoriteTeam, listener: (FavoriteTeam) -> Unit) {
            Picasso.get().load(favoriteTeam.teamLogo.toString()).into(logoImage)
            nameTeam.text = favoriteTeam.teamName
            stadiumTeam.text = favoriteTeam.teamStadium
            itemView.onClick { listener(favoriteTeam) }

        }

    }

}