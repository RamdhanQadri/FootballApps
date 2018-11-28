package rqk.footballapps.model

data class FavoritesTeam(
    val id: Long?,
    val teamId: String?,
    val teamName: String?,
    val teamBadge: String?
) {
    companion object {
        const val OPEN_TABLE_TEAM: String = "OPEN_TABLE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BEDGE: String = "TEAM_BEDGE"
    }
}