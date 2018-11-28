package rqk.footballapps.presenter

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.coroutines.experimental.asReference
import rqk.footballapps.api.ApiRepository
import rqk.footballapps.api.TheSportDBApi
import rqk.footballapps.interfac.ViewPlayers
import rqk.footballapps.model.PlayersResponse

class PresenterPlayers(
    private val view: ViewPlayers,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun searchPlayers(teamNamePlayer: String?) {
        val ref = asReference()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.searchPlayers(teamNamePlayer)).await(),
                    PlayersResponse::class.java

                )
            ref().view.showMatch(data.player)
            view.hideLoading()
        }
    }

    fun searchDetailPlayers(playersName: String?) {
        val ref = asReference()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.searchDetailPlayers(playersName)).await(),
                    PlayersResponse::class.java

                )
            ref().view.showMatch(data.player)
            view.hideLoading()
        }
    }
}