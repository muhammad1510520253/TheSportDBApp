package com.blogspot.ketikanmd.thesportdbapp.favorite.match

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.blogspot.ketikanmd.thesportdbapp.R
import com.blogspot.ketikanmd.thesportdbapp.utils.dateFormat
import org.jetbrains.anko.sdk25.coroutines.onClick

class FavoriteMatchAdapter(private val context: Context?, private var favoriteMatch: List<FavoriteMatch>, private val listener: (FavoriteMatch) -> Unit) : RecyclerView.Adapter<FavoriteMatchAdapter.MatchViewHolder>() {
    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(favoriteMatch[position], listener)
    }

    override fun getItemCount(): Int = favoriteMatch.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.items, parent, false))

    }

    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dateMatch = view.findViewById(R.id.date_match) as TextView
        private val homeTeam = view.findViewById(R.id.home_team) as TextView
        private val awayTeam = view.findViewById(R.id.away_team) as TextView
        private val scoreHome = view.findViewById(R.id.scoreHome) as TextView
        private val scoreAway = view.findViewById(R.id.scoreAway) as TextView

        fun bindItem(favoriteMatch: FavoriteMatch, listener: (FavoriteMatch) -> Unit) {
            dateMatch.text = favoriteMatch.dateMatch.toString().dateFormat()
            homeTeam.text = favoriteMatch.teamHome
            awayTeam.text = favoriteMatch.teamAway
            scoreHome.text = favoriteMatch.scoreHome
            scoreAway.text = favoriteMatch.scoreAway
            itemView.onClick { listener(favoriteMatch) }

        }

    }

}