package com.qlue.footballapp.model.db

class FavouriteTeam(
    val id: Long?,
    val idTeam: String
) {
    companion object {
        const val TABLE_TEAM: String = "TABLE_TEAM"
        const val ID: String = "ID"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_IMAGE: String = "TEAM_IMAGE"
    }
}