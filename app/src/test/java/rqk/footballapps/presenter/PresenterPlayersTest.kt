package rqk.footballapps.presenter

import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.coroutines.experimental.asReference
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import rqk.footballapps.api.ApiRepository
import rqk.footballapps.api.TheSportDBApi
import rqk.footballapps.interfac.ViewPlayers
import rqk.footballapps.model.PlayersResponse

class PresenterPlayersTest {
    private val teamNamePlayer = "Arsenal"
    private val playersName = "Mesut Ozil"

    @Mock
    private
    lateinit var viewPlayers: ViewPlayers

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var gson: Gson

    private lateinit var presenterPlayers: PresenterPlayers

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenterPlayers = PresenterPlayers(viewPlayers, apiRepository, gson)
    }

    @Test
    fun searchPlayers() {
        val ref = asReference()
        GlobalScope.launch {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.searchPlayers(teamNamePlayer)).await(),
                    PlayersResponse::class.java

                )
            presenterPlayers.searchPlayers(teamNamePlayer)

            Mockito.verify(viewPlayers).showLoading()
            Mockito.verify(ref().viewPlayers).showMatch(data.player)
            Mockito.verify(viewPlayers).hideLoading()
        }
    }

    @Test
    fun searchDetailPlayers() {
        val ref = asReference()
        GlobalScope.launch {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.searchDetailPlayers(playersName)).await(),
                    PlayersResponse::class.java
                )
            presenterPlayers.searchDetailPlayers(playersName)

            Mockito.verify(viewPlayers).showLoading()
            Mockito.verify(ref().viewPlayers).showMatch(data.player)
            Mockito.verify(viewPlayers).hideLoading()
        }
    }
}