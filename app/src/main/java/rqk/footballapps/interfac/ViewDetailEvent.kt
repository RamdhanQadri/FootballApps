package rqk.footballapps.interfac

import rqk.footballapps.model.Event
import rqk.footballapps.model.Team

interface ViewDetailEvent {
    fun showLoading()
    fun hideLoading()
    fun showMatch(data: List<Event>)
    fun showTeam(data: List<Team>, homeTeam: Boolean)
}