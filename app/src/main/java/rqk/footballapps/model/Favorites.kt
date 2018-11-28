package rqk.footballapps.model

data class Favorites(
    val id: Long?,
    val eventId: String?,
    val eventDate: String?,
    val eventTime: String?,
    val homeTeamId: String?,
    val homeTeam: String?,
    val homeScore: String?,
    val awayTeamId: String?,
    val awayTeam: String?,
    val awayScore: String?
) {
    companion object {
        const val OPEN_TABLE: String = "OPEN_TABLE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val EVENT_TIME: String = "EVENT_TIME"
        const val HOME_TEAM_ID: String = "HOME_TEAM_ID"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val HOME_TEAM_SCORE: String = "HOME_TEAM_SCORE"
        const val AWAY_TEAM_ID: String = "AWAY_TEAM_ID"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val AWAY_TEAM_SCORE: String = "AWAY_TEAM_SCORE"
    }
}