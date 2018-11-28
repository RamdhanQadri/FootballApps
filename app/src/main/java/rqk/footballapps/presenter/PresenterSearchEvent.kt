package rqk.footballapps.presenter

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.coroutines.experimental.asReference
import rqk.footballapps.api.ApiRepository
import rqk.footballapps.api.TheSportDBApi
import rqk.footballapps.interfac.ViewEvent
import rqk.footballapps.model.EventSearchResponse

class PresenterSearchEvent(
    private val view: ViewEvent,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getSearchEvents(eventNameTeam: String) {
        val ref = asReference()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.searchEvent(eventNameTeam)).await(),
                    EventSearchResponse::class.java

                )
            ref().view.showMatch(data.event)
            view.hideLoading()
        }
    }
}