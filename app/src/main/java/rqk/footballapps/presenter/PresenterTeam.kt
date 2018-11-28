package rqk.footballapps.presenter

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.coroutines.experimental.asReference
import rqk.footballapps.api.ApiRepository
import rqk.footballapps.api.TheSportDBApi
import rqk.footballapps.interfac.ViewTeam
import rqk.footballapps.model.TeamResponse

class PresenterTeam(
    private val view: ViewTeam,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getTeam(teamLeague: String?) {
        val ref = asReference()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getTeams(teamLeague)).await(),
                    TeamResponse::class.java

                )
            ref().view.showMatch(data.teams)
            view.hideLoading()
        }
    }

    fun searchTeams(teamName: String) {
        val ref = asReference()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.searchTeams(teamName)).await(),
                    TeamResponse::class.java

                )
            ref().view.showMatch(data.teams)
            view.hideLoading()
        }
    }

    fun teamDetail(teamId: String?) {
        val ref = asReference()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getDetailTeam(teamId)).await(),
                    TeamResponse::class.java

                )
            ref().view.showMatch(data.teams)
            view.hideLoading()
        }
    }
}