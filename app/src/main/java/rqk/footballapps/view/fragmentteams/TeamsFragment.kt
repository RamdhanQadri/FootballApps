package rqk.footballapps.view.fragmentteams

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.*
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import rqk.footballapps.R
import rqk.footballapps.R.id.*
import rqk.footballapps.adapter.AdapterTeam
import rqk.footballapps.api.ApiRepository
import rqk.footballapps.interfac.ViewTeam
import rqk.footballapps.model.Team
import rqk.footballapps.presenter.PresenterTeam
import rqk.footballapps.utils.invisible
import rqk.footballapps.utils.visible
import rqk.footballapps.view.details.DetailTeam


class TeamsFragment : Fragment(), AnkoComponent<Context>, ViewTeam {
    private var teamsTeam: MutableList<Team> = mutableListOf()
    private lateinit var presenterTeam: PresenterTeam
    private lateinit var adapterTeam: AdapterTeam
    private lateinit var recyclerViewTeam: RecyclerView
    private lateinit var progressBarTeam: ProgressBar
    private lateinit var swipeRefreshTeam: SwipeRefreshLayout
    private lateinit var spinnerTeam: Spinner
    private lateinit var leagueNameTeam: String
    private var text: String = ""

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val spinnerItem = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, spinnerItem)
        spinnerTeam.adapter = spinnerAdapter

        adapterTeam = AdapterTeam(teamsTeam) {
            requireContext().startActivity<DetailTeam>(
                "teamId" to "${it.teamId}",
                "teamName" to "${it.teamName}"
            )
        }

        recyclerViewTeam.adapter = adapterTeam

        val request = ApiRepository()
        val gson = Gson()
        presenterTeam = PresenterTeam(this, request, gson)

        spinnerTeam.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueNameTeam = spinnerTeam.selectedItem.toString()
                presenterTeam.getTeam(leagueNameTeam)

            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        swipeRefreshTeam.onRefresh {
            presenterTeam.getTeam(leagueNameTeam)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return createView(requireContext().let { AnkoContext.create(it) })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_search, menu)

        val searchItem = menu.findItem(R.id.action_se)
        val searchView = searchItem.actionView as SearchView
        searchView.requestFocusFromTouch()
        searchViewListen(searchView)
    }

    private fun searchViewListen(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query.length > 2) {
                    text = query
                    presenterTeam.searchTeams(text)
                }
                return false
            }
        })
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)
            spinnerTeam = spinner {
                id = spinnerId
            }
            swipeRefreshTeam = swipeRefreshLayout {
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)
                    recyclerViewTeam = recyclerView {
                        id = recyclerViewId
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                    progressBarTeam = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBarTeam.visible()
    }

    override fun hideLoading() {
        progressBarTeam.invisible()
    }

    override fun showMatch(data: List<Team>) {
        swipeRefreshTeam.isRefreshing = false
        teamsTeam.clear()
        data.forEach {
            if (it.sport.equals("Soccer")) {
                teamsTeam.add(it)
            }
        }
        adapterTeam.notifyDataSetChanged()
    }
}