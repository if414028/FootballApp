package com.qlue.footballapp.model.repo

import android.content.Context
import com.qlue.footballapp.model.db.FavouriteMatch
import com.qlue.footballapp.model.db.FavouriteTeam
import com.qlue.footballapp.utils.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class LocalRepoImpl(private val context: Context) : LocalRepo {

    override fun getTeamFromDb(): List<FavouriteTeam> {
        lateinit var favoriteList: List<FavouriteTeam>
        context.database.use {
            val result = select(FavouriteTeam.TABLE_TEAM)
            val favorite = result.parseList(classParser<FavouriteTeam>())
            favoriteList = favorite
        }
        return favoriteList
    }

    override fun insertTeamData(teamId: String, imageUrl: String) {
        context.database.use {
            insert(
                FavouriteTeam.TABLE_TEAM,
                FavouriteTeam.TEAM_ID to teamId,
                FavouriteTeam.TEAM_IMAGE to imageUrl
            )

        }
    }

    override fun deleteTeamData(teamId: String) {
        context.database.use {
            delete(
                FavouriteTeam.TABLE_TEAM, "(TEAM_ID = {id})",
                "id" to teamId
            )
        }
    }

    override fun checkFavTeam(teamId: String): List<FavouriteTeam> {
        return context.database.use {
            val result = select(FavouriteTeam.TABLE_TEAM)
                .whereArgs(
                    "(TEAM_ID = {id})",
                    "id" to teamId
                )
            val favorite = result.parseList(classParser<FavouriteTeam>())
            favorite
        }
    }

    override fun getMatchFromDb(): List<FavouriteMatch> {
        lateinit var favoriteList: List<FavouriteMatch>
        context.database.use {
            val result = select(FavouriteMatch.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavouriteMatch>())
            favoriteList = favorite
        }
        return favoriteList
    }

    override fun insertMatchData(eventId: String, homeId: String, awayId: String) {
        context.database.use {
            insert(
                FavouriteMatch.TABLE_FAVORITE,
                FavouriteMatch.EVENT_ID to eventId,
                FavouriteMatch.HOME_TEAM_ID to homeId,
                FavouriteMatch.AWAY_TEAM_ID to awayId
            )

        }
    }

    override fun deleteMatchData(eventId: String) {
        context.database.use {
            delete(
                FavouriteMatch.TABLE_FAVORITE, "(EVENT_ID = {id})",
                "id" to eventId
            )
        }
    }

    override fun checkFavMatch(eventId: String): List<FavouriteMatch> {
        return context.database.use {
            val result = select(FavouriteMatch.TABLE_FAVORITE)
                .whereArgs(
                    "(EVENT_ID = {id})",
                    "id" to eventId
                )
            val favorite = result.parseList(classParser<FavouriteMatch>())
            favorite
        }
    }

}