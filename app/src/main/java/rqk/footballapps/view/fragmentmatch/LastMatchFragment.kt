package rqk.footballapps.view.fragmentmatch

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import rqk.footballapps.R
import rqk.footballapps.R.id.*
import rqk.footballapps.adapter.AdapterEvent
import rqk.footballapps.api.ApiRepository
import rqk.footballapps.interfac.ViewEvent
import rqk.footballapps.model.Event
import rqk.footballapps.presenter.PresenterEvent
import rqk.footballapps.utils.invisible
import rqk.footballapps.utils.visible
import rqk.footballapps.view.details.DetailActivity

private var leagueName: Int = 4328

class LastMatchFragment : Fragment(), AnkoComponent<Context>, ViewEvent {
    private var events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: PresenterEvent
    private lateinit var adapter: AdapterEvent
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var spinnerLeg: String


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val spinnerItem = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, spinnerItem)
        spinner.adapter = spinnerAdapter

        adapter = AdapterEvent(events) {
            requireContext().startActivity<DetailActivity>(
                "match" to "${it.eventId}",
                "home" to "${it.homeTeamId}",
                "away" to "${it.awayTeamId}"
            )

        }

        recyclerView.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = PresenterEvent(this, request, gson)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                spinnerLeg = spinner.selectedItem.toString()
                if (spinnerLeg.equals("English Premier League")) {
                    leagueName = 4328
                    presenter.getLastEvents(leagueName)
                } else if (spinnerLeg.equals("English League Championship")) {
                    leagueName = 4329
                    presenter.getLastEvents(leagueName)
                } else if (spinnerLeg.equals("Spanish La Liga")) {
                    leagueName = 4335
                    presenter.getLastEvents(leagueName)
                } else if (spinnerLeg.equals("German Bundesliga")) {
                    leagueName = 4331
                    presenter.getLastEvents(leagueName)
                } else if (spinnerLeg.equals("Italian Serie A")) {
                    leagueName = 4332
                    presenter.getLastEvents(leagueName)
                } else if (spinnerLeg.equals("French Ligue 1")) {
                    leagueName = 4334
                    presenter.getLastEvents(leagueName)
                } else {
                    leagueName = 4328
                    presenter.getLastEvents(leagueName)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        swipeRefresh.onRefresh {
            presenter.getLastEvents(leagueName)
        }
        presenter.getLastEvents(leagueName)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(requireContext().let { AnkoContext.create(it) })
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)
            spinner = spinner {
                id = spinnerId
            }
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)
                    recyclerView = recyclerView {
                        id = recyclerViewId
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatch(data: List<Event>) {
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }
}