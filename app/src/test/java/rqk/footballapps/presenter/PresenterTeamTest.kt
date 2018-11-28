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
import rqk.footballapps.interfac.ViewTeam
import rqk.footballapps.model.TeamResponse

class PresenterTeamTest {
    private val teamLeague: Int = 4328 // English Premier League
    private val teamName = "Arsenal"
    private val teamId: Int = 133604 //Arsenal

    @Mock
    private
    lateinit var viewTeam: ViewTeam

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var gson: Gson

    private lateinit var presenterTeam: PresenterTeam

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenterTeam = PresenterTeam(viewTeam, apiRepository, gson)
    }

    @Test
    fun getTeam() {
        val ref = asReference()
        GlobalScope.launch {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getTeams(teamLeague.toString())).await(),
                    TeamResponse::class.java

                )
            presenterTeam.getTeam(teamLeague.toString())

            Mockito.verify(viewTeam).showLoading()
            Mockito.verify(ref().viewTeam).showMatch(data.teams)
            Mockito.verify(viewTeam).hideLoading()
        }
    }

    @Test
    fun searchTeams() {
        val ref = asReference()
        GlobalScope.launch {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.searchTeams(teamName)).await(),
                    TeamResponse::class.java

                )
            presenterTeam.searchTeams(teamName)

            Mockito.verify(viewTeam).showLoading()
            Mockito.verify(ref().viewTeam).showMatch(data.teams)
            Mockito.verify(viewTeam).hideLoading()
        }
    }

    @Test
    fun teamDetail() {
        val ref = asReference()
        GlobalScope.launch {
            val data =
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getDetailTeam(teamId.toString())).await(),
                    TeamResponse::class.java

                )
            presenterTeam.teamDetail(teamId.toString())

            Mockito.verify(viewTeam).showLoading()
            Mockito.verify(ref().viewTeam).showMatch(data.teams)
            Mockito.verify(viewTeam).hideLoading()
        }
    }
}