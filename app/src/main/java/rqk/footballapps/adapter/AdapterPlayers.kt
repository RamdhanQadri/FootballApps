package rqk.footballapps.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import rqk.footballapps.layoutUI.LayoutUIPlayers
import rqk.footballapps.model.Players

class AdapterPlayers(
    private val players: List<Players>,
    private val listener: (Players) -> Unit
) : RecyclerView.Adapter<PlayersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        return PlayersViewHolder(
            LayoutUIPlayers().createView(
                AnkoContext.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        holder.bindItem(players[position], listener)
    }

    override fun getItemCount(): Int = players.count()
}

class PlayersViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val playersBadg: ImageView = view.find(LayoutUIPlayers.players_badge)
    private val playersName: TextView = view.find(LayoutUIPlayers.players_name)
    private val playersPosition: TextView = view.find(LayoutUIPlayers.players_position)

    fun bindItem(players: Players, listener: (Players) -> Unit) {
        Glide.with(view).load(players.iconPlayers).into(playersBadg)
        playersName.text = players.namePlayers
        playersPosition.text = players.positionPlayers

        itemView.setOnClickListener {
            listener(players)
        }
    }
}