package rqk.footballapps.adapter

import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk15.coroutines.onClick
import rqk.footballapps.layoutUI.LayoutUI
import rqk.footballapps.model.Event
import rqk.footballapps.utils.*
import java.util.*

class AdapterEvent(
    private val events: List<Event>,
    private val listener: (Event) -> Unit
) : RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            LayoutUI().createView(
                AnkoContext.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }

    override fun getItemCount(): Int = events.count()
}

class EventViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val eventDate: TextView = view.find(LayoutUI.textViewDate)
    private val eventTime: TextView = view.find(LayoutUI.textViewTime)
    private val homeTeamName: TextView = view.find(LayoutUI.textViewHomeTeam)
    private val homeTeamScore: TextView = view.find(LayoutUI.testViewHomeScore)
    private val awayTeamScore: TextView = view.find(LayoutUI.textViewAwayScore)
    private val awayTeamName: TextView = view.find(LayoutUI.textViewAwayTeam)
    private val btnNotif: ImageButton = view.find(LayoutUI.btn_notify)

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

        val dateTimeBt = formatDateTime(events.eventDate, events.eventTime)

        if (dateTimeBt!!.after(Date())) {
            btnNotif.visibility = View.VISIBLE
        }

        itemView.setOnClickListener {
            listener(events)
        }
        btnNotif.onClick {

            val intent = Intent(Intent.ACTION_INSERT)
            intent.type = "vnd.android.cursor.item/event"
            intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false)
            intent.putExtra(
                CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                "${events.eventDate} ${events.eventTime}"
            )

            intent.putExtra(
                CalendarContract.Events.TITLE,
                "${events.homeTeam} vs ${events.awayTeam}"
            )

            intent.putExtra(
                CalendarContract.Events.DESCRIPTION,
                "Reminder ${events.homeTeam} and ${events.awayTeam} from My Football App"
            )


            itemView.context.startActivity(intent)
        }
    }
}