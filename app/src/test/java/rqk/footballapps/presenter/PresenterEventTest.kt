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
import rqk.footballapps.interfac.ViewEvent
import rqk.footballapps.model.EventResponse

class PresenterEventTest {
    private val leagueId: Int = 4328 // English Premier League

    @Mock
    private
    lateinit var viewDetail: ViewEvent

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var gson: Gson

    private lateinit var presenterEvent: PresenterEvent

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenterEvent = PresenterEvent(viewDetail, apiRepository, gson)
    }

    @Test
    fun getLastEvents() {
        val ref = asReference()
        GlobalScope.launch {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getLastEvents(leagueId)).await(),
                    EventResponse::class.java

                )
            presenterEvent.getLastEvents(leagueId)

            Mockito.verify(viewDetail).showLoading()
            Mockito.verify(ref().viewDetail).showMatch(data.events)
            Mockito.verify(viewDetail).hideLoading()
        }
    }

    @Test
    fun getNextEvents() {
        val ref = asReference()
        GlobalScope.launch {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getNextEvents(leagueId)).await(),
                    EventResponse::class.java

                )
            presenterEvent.getNextEvents(leagueId)

            Mockito.verify(viewDetail).showLoading()
            Mockito.verify(ref().viewDetail).showMatch(data.events)
            Mockito.verify(viewDetail).hideLoading()
        }
    }
}