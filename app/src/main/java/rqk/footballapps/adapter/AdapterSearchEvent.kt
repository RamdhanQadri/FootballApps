package rqk.footballapps.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import rqk.footballapps.layoutUI.LayoutUI
import rqk.footballapps.model.Event
import rqk.footballapps.utils.*

class AdapterSearchEvent(
    private val events: List<Event>,
    private val listener: (Event) -> Unit
) : RecyclerView.Adapter<SearchEventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchEventViewHolder {
        return SearchEventViewHolder(
            LayoutUI().createView(
                AnkoContext.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun onBindViewHolder(holder: SearchEventViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }

    override fun getItemCount(): Int = events.count()
}

class SearchEventViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val eventDate: TextView = view.find(LayoutUI.textViewDate)
    private val eventTime: TextView = view.find(LayoutUI.textViewTime)
    private val homeTeamName: TextView = view.find(LayoutUI.textViewHomeTeam)
    private val homeTeamScore: TextView = view.find(LayoutUI.testViewHomeScore)
    private val awayTeamScore: TextView = view.find(LayoutUI.textViewAwayScore)
    private val awayTeamName: TextView = view.find(LayoutUI.textViewAwayTeam)

    fun bindItem(events: Event, listener: (Event) -> Unit) {
        eventDate.text = events.eventDate?.formatDate()
        eventTime.text = events.eventTime?.formatTime()

        homeTeamName.text = events.homeTeam
        homeTeamScore.text = events.homeScore
        awayTeamScore.text = events.awayScore
        awayTeamName.text = events.awayTeam

        val homeScore = events.homeScore?.toInt() ?: 0
        val awayScore = events.awayScore?.toInt() ?: 0

        if (homeScore - awayScore > 0) {
            homeTeamName.bold()
            awayTeamName.normal()
        } else {
            if (homeScore - awayScore < 0) {
                homeTeamName.normal()
                awayTeamName.bold()
            } else {
                homeTeamName.normal()
                awayTeamName.normal()
            }
        }

        itemView.setOnClickListener {
            listener(events)
        }
    }
}