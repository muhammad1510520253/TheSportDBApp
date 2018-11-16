package com.blogspot.ketikanmd.thesportdbapp.player

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.blogspot.ketikanmd.thesportdbapp.R
import com.blogspot.ketikanmd.thesportdbapp.TeamModels
import com.blogspot.ketikanmd.thesportdbapp.fragment.TeamFragment
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import org.jetbrains.anko.sdk25.coroutines.onClick
import kotlin.concurrent.thread

class TeamAdapter(private val context: Context?, private var teamItems: List<TeamModels>, private val listener: (TeamModels) -> Unit)
    : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {


    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teamItems[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.team_items, parent, false))
    }

    override fun getItemCount(): Int = teamItems.size

    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val logoImage = view.findViewById(R.id.team_logo) as ImageView
        private val nameTeam = view.findViewById(R.id.name_team) as TextView
        private val stadiumTeam = view.findViewById(R.id.stadium_team) as TextView
        fun bindItem(teamItems: TeamModels, listener: (TeamModels) -> Unit) {
            Picasso.get().load(teamItems.teamBadge).into(logoImage)
            nameTeam.text = teamItems.strTeam
            stadiumTeam.text = teamItems.strStadium
            itemView.onClick { listener(teamItems) }
        }
    }

    fun refreshTeams(fill: List<TeamModels>) {
        teamItems = fill
        notifyDataSetChanged()
    }


}