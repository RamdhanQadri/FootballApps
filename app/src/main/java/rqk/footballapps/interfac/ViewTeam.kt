package rqk.footballapps.interfac

import rqk.footballapps.model.Team

interface ViewTeam {
    fun showLoading()
    fun hideLoading()
    fun showMatch(data: List<Team>)
}