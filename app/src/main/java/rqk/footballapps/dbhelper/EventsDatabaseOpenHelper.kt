package rqk.footballapps.dbhelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import rqk.footballapps.model.Favorites
import rqk.footballapps.model.FavoritesTeam

class EventsDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FOOTBALL.db", null, 12) {
    companion object {
        private var intance: EventsDatabaseOpenHelper? = null
        @Synchronized
        fun getIntance(ctx: Context): EventsDatabaseOpenHelper {
            if (intance == null) {
                intance = EventsDatabaseOpenHelper(ctx.applicationContext)
            }
            return intance as EventsDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            Favorites.OPEN_TABLE, true,
            Favorites.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorites.EVENT_ID to TEXT + UNIQUE,
            Favorites.EVENT_DATE to TEXT,
            Favorites.EVENT_TIME to TEXT,
            Favorites.HOME_TEAM_ID to TEXT,
            Favorites.HOME_TEAM to TEXT,
            Favorites.HOME_TEAM_SCORE to TEXT,
            Favorites.AWAY_TEAM_ID to TEXT,
            Favorites.AWAY_TEAM to TEXT,
            Favorites.AWAY_TEAM_SCORE to TEXT
        )

        db.createTable(
            FavoritesTeam.OPEN_TABLE_TEAM, true,
            FavoritesTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoritesTeam.TEAM_ID to TEXT + UNIQUE,
            FavoritesTeam.TEAM_NAME to TEXT,
            FavoritesTeam.TEAM_BEDGE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorites.OPEN_TABLE, true)
        db.dropTable(FavoritesTeam.OPEN_TABLE_TEAM, true)
    }
}

val Context.database: EventsDatabaseOpenHelper get() = EventsDatabaseOpenHelper.getIntance(applicationContext)