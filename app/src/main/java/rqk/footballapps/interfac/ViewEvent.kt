package rqk.footballapps.interfac

import rqk.footballapps.model.Event

interface ViewEvent {
    fun showLoading()
    fun hideLoading()
    fun showMatch(data: List<Event>)
}