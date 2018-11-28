package rqk.footballapps.presenter

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.coroutines.experimental.asReference
import rqk.footballapps.api.ApiRepository
import rqk.footballapps.api.TheSportDBApi
import rqk.footballapps.interfac.ViewEvent
import rqk.footballapps.model.EventResponse

class PresenterEvent(
    private val view: ViewEvent,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getLastEvents(leagueId: Int?) {
        val ref = asReference()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getLastEvents(leagueId)).await(),
                    EventResponse::class.java

                )
            ref().view.showMatch(data.events)
            view.hideLoading()
        }
    }

    fun getNextEvents(leagueId: Int?) {
        val ref = asReference()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getNextEvents(leagueId)).await(),
                    EventResponse::class.java
                )
            ref().view.showMatch(data.events)
            view.hideLoading()
        }
    }
}