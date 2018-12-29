package com.qlue.footballapp.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.qlue.footballapp.model.db.FavouriteMatch
import com.qlue.footballapp.model.db.FavouriteTeam
import org.jetbrains.anko.db.*

class DbHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {

    companion object {
        private var instance: DbHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DbHelper {
            if (instance == null) {
                instance = DbHelper(ctx.applicationContext)
            }
            return instance as DbHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            FavouriteMatch.TABLE_FAVORITE, true,
            FavouriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavouriteMatch.EVENT_ID to TEXT + UNIQUE,
            FavouriteMatch.HOME_TEAM_ID to TEXT,
            FavouriteMatch.AWAY_TEAM_ID to TEXT
        )
        db?.createTable(
            FavouriteTeam.TABLE_TEAM, true,
            FavouriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavouriteTeam.TEAM_ID to TEXT,
            FavouriteTeam.TEAM_IMAGE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavouriteMatch.TABLE_FAVORITE, true)
        db?.dropTable(FavouriteTeam.TABLE_TEAM, true)
    }

}

val Context.database: DbHelper
    get() = DbHelper.getInstance(applicationContext)