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
import rqk.footballapps.model.EventSearchResponse

class PresenterSearchEventTest {
    private val eventNameTeam = "Arsenal"

    @Mock
    private
    lateinit var viewEvent: ViewEvent

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var gson: Gson

    private lateinit var presenterSearchEvent: PresenterSearchEvent

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenterSearchEvent = PresenterSearchEvent(viewEvent, apiRepository, gson)
    }

    @Test
    fun getSearchEvents() {
        val ref = asReference()
        GlobalScope.launch {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.searchEvent(eventNameTeam)).await(),
                    EventSearchResponse::class.java

                )
            presenterSearchEvent.getSearchEvents(eventNameTeam)

            Mockito.verify(viewEvent).showLoading()
            Mockito.verify(ref().viewEvent).showMatch(data.event)
            Mockito.verify(viewEvent).hideLoading()
        }
    }
}