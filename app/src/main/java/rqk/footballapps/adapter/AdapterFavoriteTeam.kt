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
import rqk.footballapps.model.FavoritesTeam

class AdapterFavoriteTeam(
    private val favorites: List<FavoritesTeam>,
    private val listener: (FavoritesTeam) -> Unit
) : RecyclerView.Adapter<FavoritesTeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesTeamViewHolder {
        return FavoritesTeamViewHolder(
            LayoutUITeam().createView(
                AnkoContext.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun onBindViewHolder(holder: FavoritesTeamViewHolder, position: Int) {
        holder.bindItem(favorites[position], listener)
    }

    override fun getItemCount(): Int = favorites.count()
}

class FavoritesTeamViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val teamsBadg: ImageView = view.find(LayoutUITeam.team_badge)
    private val teamName: TextView = view.find(LayoutUITeam.team_name)

    fun bindItem(favorites: FavoritesTeam, listener: (FavoritesTeam) -> Unit) {
        Glide.with(view).load(favorites.teamBadge).into(teamsBadg)
        teamName.text = favorites.teamName

        itemView.setOnClickListener {
            listener(favorites)
        }
    }
}