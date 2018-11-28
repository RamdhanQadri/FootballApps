package rqk.footballapps.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import rqk.footballapps.layoutUI.LayoutUITeam
import rqk.footballapps.model.Team

class AdapterTeam(
    private val teams: List<Team>,
    private val listener: (Team) -> Unit
) : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(
            LayoutUITeam().createView(
                AnkoContext.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    override fun getItemCount(): Int = teams.count()
}

class TeamViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val teamsBadg: ImageView = view.find(LayoutUITeam.team_badge)
    private val teamName: TextView = view.find(LayoutUITeam.team_name)

    fun bindItem(teams: Team, listener: (Team) -> Unit) {
        Glide.with(view).load(teams.teamBadge).into(teamsBadg)
        teamName.text = teams.teamName

        itemView.setOnClickListener {
            listener(teams)
        }
    }
}