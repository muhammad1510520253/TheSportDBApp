package com.blogspot.ketikanmd.thesportdbapp.favorite.match


data class FavoriteTeam(val id: Long?, val teamId: String?, val teamLogo: String?, var teamName: String?, val teamStadium: String?
                        , val teamYear: String?
) {
    companion object {

        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val teamId: String = "TEAM_ID"
        const val teamLogo: String = "TEAM_LOGO"
        const val teamName: String = "TEAM_NAME"
        const val teamStadium: String = "TEAM_STADIUM"
        const val teamYear: String = "TEAM_YEAR"
    }

}