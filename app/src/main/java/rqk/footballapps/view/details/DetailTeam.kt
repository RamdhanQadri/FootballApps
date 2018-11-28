package rqk.footballapps.view.details

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import rqk.footballapps.R
import rqk.footballapps.R.id.add_to_favorite
import rqk.footballapps.R.menu.detail_menu
import rqk.footballapps.adapter.ViewPagerAdapterTeamDetail
import rqk.footballapps.api.ApiRepository
import rqk.footballapps.dbhelper.database
import rqk.footballapps.interfac.ViewTeam
import rqk.footballapps.model.FavoritesTeam
import rqk.footballapps.model.Team
import rqk.footballapps.presenter.PresenterTeam

class DetailTeam : AppCompatActivity(), ViewTeam {
    private lateinit var presenterTeam: PresenterTeam
    private lateinit var team: Team
    private lateinit var teamId: String
    private lateinit var teamName: String

    private var menuItem: Menu? = null
    private var isTeamFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_team)
        supportActionBar?.hide()

        teamId = intent.getStringExtra("teamId")
        teamName = intent.getStringExtra("teamName")

        toolbarTeam.setNavigationIcon(R.drawable.abc_ic_ab_back_material)
        toolbarTeam.inflateMenu(detail_menu)
        menuItem = toolbarTeam.menu

        toolbarTeam.setNavigationOnClickListener {
            finish()
        }

        toolbarTeam.setOnMenuItemClickListener {
            when (it.itemId) {
                add_to_favorite -> {
                    if (isTeamFavorite) removeFromTeamFavorites() else addTeamFavorites()

                    isTeamFavorite = !isTeamFavorite
                    setFavorite()

                    true
                }
                else -> super.onOptionsItemSelected(it)
            }
        }

        val request = ApiRepository()
        val gson = Gson()
        presenterTeam = PresenterTeam(this, request, gson)
        presenterTeam.teamDetail(teamId)

        val fragAdapter = ViewPagerAdapterTeamDetail(supportFragmentManager)
        pagerTeam.adapter = fragAdapter
        tabLayoutTeam.setupWithViewPager(pagerTeam)

        favoriteState()
        setFavorite()
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoritesTeam.OPEN_TABLE_TEAM)
                .whereArgs(
                    "(TEAM_ID = {teamId})", "teamId" to teamId
                )
            val favorite = result.parseList(classParser<FavoritesTeam>())
            if (!favorite.isEmpty()) isTeamFavorite = true
        }
    }

    fun passingTeamId() = teamId
    fun passingTeamName() = teamName

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showMatch(data: List<Team>) {
        val first = data.first()
        team = Team(
            teamId = first.teamId,
            teamName = first.teamName,
            teamBadge = first.teamBadge
        )
        Glide.with(this).load(data[0].teamBadge).into(badge)
        name.text = data[0].teamName
        year.text = data[0].teamFormedYear
        stadium.text = data[0].teamStadium
    }

    private fun addTeamFavorites() {
        try {
            database.use {
                insert(
                    FavoritesTeam.OPEN_TABLE_TEAM,
                    FavoritesTeam.TEAM_ID to team.teamId,
                    FavoritesTeam.TEAM_NAME to team.teamName,
                    FavoritesTeam.TEAM_BEDGE to team.teamBadge
                )
            }
            pagerTeam.snackbar(R.string.added)
        } catch (e: SQLiteConstraintException) {
            pagerTeam.snackbar(e.localizedMessage)
        }
    }

    private fun removeFromTeamFavorites() {
        try {
            database.use {
                delete(
                    FavoritesTeam.OPEN_TABLE_TEAM,
                    "(TEAM_ID = {teamId})", "teamId" to teamId
                )
            }
            pagerTeam.snackbar(R.string.removed)
        } catch (e: SQLiteConstraintException) {
            pagerTeam.snackbar(e.localizedMessage)
        }
    }

    private fun setFavorite() {
        if (isTeamFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }
}
