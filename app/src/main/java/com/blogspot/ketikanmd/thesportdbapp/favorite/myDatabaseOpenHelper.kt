package com.blogspot.ketikanmd.thesportdbapp.favorite

import org.jetbrains.anko.db.*
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.blogspot.ketikanmd.thesportdbapp.favorite.match.FavoriteMatch
import com.blogspot.ketikanmd.thesportdbapp.favorite.match.FavoriteTeam


class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true,
                FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatch.eventId to TEXT + UNIQUE,
                FavoriteMatch.homeTeamId to TEXT,
                FavoriteMatch.awayTeamId to TEXT,
                FavoriteMatch.dateMatch to TEXT,
                FavoriteMatch.teamHome to TEXT,
                FavoriteMatch.teamAway to TEXT,
                FavoriteMatch.scoreHome to TEXT,
                FavoriteMatch.scoreAway to TEXT
        )
        db.createTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true,
                FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeam.teamId to TEXT + UNIQUE,
                FavoriteTeam.teamLogo to TEXT,
                FavoriteTeam.teamName to TEXT,
                FavoriteTeam.teamStadium to TEXT,
                FavoriteTeam.teamYear to TEXT
        )

    }

    companion object {
        private var instance: MyDatabaseOpenHelper? = null
        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }


    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.dropTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true)
        db.dropTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)