package com.blogspot.ketikanmd.thesportdbapp.favorite.match


data class FavoriteMatch(val id: Long?, val eventId: String?, val homeTeamId: String?, var awayTeamId: String?, val dateMatch: String?,
                         val teamHome: String?, val teamAway: String?, val scoreHome: String?, val scoreAway: String?

) {
    companion object {

        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val eventId: String = "EVENT_ID"
        const val homeTeamId: String = "HOME_TEAM_ID"
        const val awayTeamId: String = "AWAY_TEAM_ID"
        const val dateMatch: String = "DATE_MATCH"
        const val teamHome: String = "TEAM_HOME"
        const val teamAway: String = "TEAM_AWAY"
        const val scoreHome: String = "SCORE_HOME"
        const val scoreAway: String = "SCORE_AWAY"
    }

}