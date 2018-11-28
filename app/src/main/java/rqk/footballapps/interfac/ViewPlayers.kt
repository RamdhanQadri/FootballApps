package rqk.footballapps.interfac

import rqk.footballapps.model.Players

interface ViewPlayers {
    fun showLoading()
    fun hideLoading()
    fun showMatch(data: List<Players>)
}