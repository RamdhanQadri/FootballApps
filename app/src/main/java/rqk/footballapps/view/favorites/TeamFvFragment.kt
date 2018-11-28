package rqk.footballapps.view.favorites

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import rqk.footballapps.R.color.colorAccent
import rqk.footballapps.R.id.recyclerViewId
import rqk.footballapps.adapter.AdapterFavoriteTeam
import rqk.footballapps.dbhelper.database
import rqk.footballapps.model.FavoritesTeam
import rqk.footballapps.view.details.DetailTeam

class TeamFvFragment : Fragment(), AnkoComponent<Context> {
    private var favorites: MutableList<FavoritesTeam> = mutableListOf()
    private lateinit var adapter: AdapterFavoriteTeam
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = AdapterFavoriteTeam(favorites) {
            requireContext().startActivity<DetailTeam>(
                "teamId" to "${it.teamId}",
                "teamName" to "${it.teamName}"
            )
        }

        recyclerView.adapter = adapter
        showFavorite()
        swipeRefresh.onRefresh {
            favorites.clear()
            showFavorite()
        }
    }

    private fun showFavorite() {
        requireContext().database.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavoritesTeam.OPEN_TABLE_TEAM)
            val favorite = result.parseList(classParser<FavoritesTeam>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )

                recyclerView = recyclerView {
                    id = recyclerViewId
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }

    }

}