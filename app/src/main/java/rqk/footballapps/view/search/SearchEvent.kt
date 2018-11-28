package rqk.footballapps.view.search

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import rqk.footballapps.R
import rqk.footballapps.R.id.*
import rqk.footballapps.adapter.AdapterSearchEvent
import rqk.footballapps.api.ApiRepository
import rqk.footballapps.interfac.ViewEvent
import rqk.footballapps.model.Event
import rqk.footballapps.presenter.PresenterSearchEvent
import rqk.footballapps.view.details.DetailActivity

class SearchEvent : AppCompatActivity(), ViewEvent {
    private var events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: PresenterSearchEvent
    private lateinit var adapter: AdapterSearchEvent
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var teamEventName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val request = ApiRepository()
        val gson = Gson()
        presenter = PresenterSearchEvent(this, request, gson)

        adapter = AdapterSearchEvent(events) {
            startActivity<DetailActivity>(
                "match" to "${it.eventId}",
                "home" to "${it.homeTeamId}",
                "away" to "${it.awayTeamId}"
            )
        }

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
                    layoutManager = LinearLayoutManager(context!!)
                }
            }.lparams(width = matchParent, height = matchParent)
        }

        recyclerView.adapter = adapter

        swipeRefresh.onRefresh {
            presenter.getSearchEvents(teamEventName)
        }
        presenter.getSearchEvents(teamEventName)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_search, menu)
        val searchItem = menu.findItem(R.id.action_se)
        val searchView = searchItem.actionView as SearchView
        searchView.setIconifiedByDefault(false)
        searchView.isIconified = false
        searchView.requestFocusFromTouch()
        searchViewListen(searchView)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun showMatch(data: List<Event>) {
        hideLoading()
        events.clear()
        data.forEach {
            if (it.sport.equals("Soccer")) {
                events.add(it)
            }
        }
        adapter.notifyDataSetChanged()
    }

    private fun searchViewListen(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query.length > 2) {
                    teamEventName = query
                    presenter.getSearchEvents(teamEventName)
                }
                return false
            }
        })
    }
}