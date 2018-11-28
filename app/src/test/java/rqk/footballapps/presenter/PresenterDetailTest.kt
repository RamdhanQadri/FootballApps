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
import rqk.footballapps.interfac.ViewDetailEvent
import rqk.footballapps.model.EventResponse
import rqk.footballapps.model.TeamResponse

class PresenterDetailTest {
    private val eventId: Int = 582175
    private val teamId: Int = 133604 //Arsenal
    private val homeTeam: Boolean = true

    @Mock
    private
    lateinit var viewDetail: ViewDetailEvent

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var gson: Gson

    private lateinit var presenterDetail: PresenterDetail

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenterDetail = PresenterDetail(viewDetail, apiRepository, gson)
    }

    @Test
    fun getDetailEvents() {
        val ref = asReference()
        GlobalScope.launch {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getDetailEvent(eventId.toString())).await(),
                    EventResponse::class.java
                )
            presenterDetail.getDetailEvents(eventId.toString())

            Mockito.verify(viewDetail).showLoading()
            Mockito.verify(ref().viewDetail).showMatch(data.events)
            Mockito.verify(viewDetail).hideLoading()
        }
    }

    @Test
    fun getDetailTeams() {
        val ref = asReference()
        GlobalScope.launch {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getDetailTeam(teamId.toString())).await(),
                    TeamResponse::class.java

                )
            presenterDetail.getDetailTeams(teamId.toString())

            Mockito.verify(viewDetail).showLoading()
            Mockito.verify(ref().viewDetail).showTeam(data.teams, homeTeam)
            Mockito.verify(viewDetail).hideLoading()
        }

    }
}