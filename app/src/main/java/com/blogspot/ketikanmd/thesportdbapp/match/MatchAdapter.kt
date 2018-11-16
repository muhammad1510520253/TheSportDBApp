package com.blogspot.ketikanmd.thesportdbapp.match

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.blogspot.ketikanmd.thesportdbapp.MatchModels
import com.blogspot.ketikanmd.thesportdbapp.R
import com.blogspot.ketikanmd.thesportdbapp.utils.dateFormat
import org.jetbrains.anko.sdk25.coroutines.onClick

class MatchAdapter(private val context: Context?, private var matchItems: List<MatchModels>, private val listener: (MatchModels) -> Unit)
    : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(matchItems[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.items, parent, false))
    }

    override fun getItemCount(): Int = matchItems.size

    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val dateMatch = view.findViewById(R.id.date_match) as TextView
        private val homeTeam = view.findViewById(R.id.home_team) as TextView
        private val scoreHome = view.findViewById(R.id.scoreHome) as TextView
        private val awayTeam = view.findViewById(R.id.away_team) as TextView
        private val scoreAway = view.findViewById(R.id.scoreAway) as TextView

        fun bindItem(matchItems: MatchModels, listener: (MatchModels) -> Unit) {
            if (matchItems?.dateMatch == null) {
                dateMatch.text = "-"
            } else {
                dateMatch.text = matchItems?.dateMatch.toString().dateFormat()
            }
            homeTeam.text = matchItems?.homeTeam
            scoreHome.text = matchItems?.homeScore
            awayTeam.text = matchItems?.awayTeam
            scoreAway.text = matchItems?.awayScore
            itemView.onClick { listener(matchItems) }
        }

    }

    fun refreshMatch(fill: List<MatchModels>) {
        matchItems = fill
        notifyDataSetChanged()
    }


}