package rqk.footballapps.presenter

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.coroutines.experimental.asReference
import rqk.footballapps.api.ApiRepository
import rqk.footballapps.api.TheSportDBApi
import rqk.footballapps.interfac.ViewDetailEvent
import rqk.footballapps.model.EventResponse
import rqk.footballapps.model.TeamResponse

class PresenterDetail(
    private val view: ViewDetailEvent,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getDetailEvents(eventId: String) {
        val ref = asReference()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getDetailEvent(eventId)).await(),
                    EventResponse::class.java

                )
            view.hideLoading()
            ref().view.showMatch(data.events)
        }
    }

    fun getDetailTeams(teamId: String, homeTeam: Boolean = true) {
        val ref = asReference()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getDetailTeam(teamId)).await(),
                    TeamResponse::class.java

                )
            view.hideLoading()
            ref().view.showTeam(data.teams, homeTeam)
        }
    }
}