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
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import rqk.footballapps.R
import rqk.footballapps.R.id.recyclerViewId
import rqk.footballapps.adapter.AdapterFavorite
import rqk.footballapps.dbhelper.database
import rqk.footballapps.model.Favorites
import rqk.footballapps.view.details.DetailActivity

class MatchFvFragment : Fragment(), AnkoComponent<Context> {
    private var events: MutableList<Favorites> = mutableListOf()
    private lateinit var adapter: AdapterFavorite
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = AdapterFavorite(events) {
            requireContext().startActivity<DetailActivity>(
                "match" to "${it.eventId}",
                "home" to "${it.homeTeamId}",
                "away" to "${it.awayTeamId}"
            )
        }
        recyclerView.adapter = adapter
        showFavorites()
        swipeRefresh.onRefresh {
            showFavorites()
        }
    }

    private fun showFavorites() {
        requireContext().database.use {
            swipeRefresh.isRefreshing = true
            val result = select(Favorites.OPEN_TABLE)
            val favorite = result.parseList(classParser<Favorites>())
            events.clear()
            events.addAll(favorite)
            adapter.notifyDataSetChanged()
            swipeRefresh.isRefreshing = false
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    R.color.colorAccent,
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