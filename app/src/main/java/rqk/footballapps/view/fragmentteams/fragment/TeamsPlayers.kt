package rqk.footballapps.view.fragmentteams.fragment

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
import rqk.footballapps.adapter.AdapterPlayers
import rqk.footballapps.api.ApiRepository
import rqk.footballapps.interfac.ViewPlayers
import rqk.footballapps.model.Players
import rqk.footballapps.presenter.PresenterPlayers
import rqk.footballapps.utils.invisible
import rqk.footballapps.utils.visible
import rqk.footballapps.view.details.DetailPlayers
import rqk.footballapps.view.details.DetailTeam

class TeamsPlayers : Fragment(), AnkoComponent<Context>, ViewPlayers {
    private var playersTeam: MutableList<Players> = mutableListOf()
    private lateinit var presenterPlayers: PresenterPlayers
    private lateinit var adapterPlayers: AdapterPlayers
    private lateinit var recyclerViewPlayers: RecyclerView
    private lateinit var progressBarPlayers: ProgressBar
    private lateinit var swipeRefreshPlayers: SwipeRefreshLayout


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val frag = activity as DetailTeam


        adapterPlayers = AdapterPlayers(playersTeam) {
            requireContext().startActivity<DetailPlayers>(
                "namePlayers" to "${it.namePlayers}",
                "teamId" to frag.passingTeamId()
            )
        }

        recyclerViewPlayers.adapter = adapterPlayers

        val request = ApiRepository()
        val gson = Gson()
        presenterPlayers = PresenterPlayers(this, request, gson)

        swipeRefreshPlayers.onRefresh {
            presenterPlayers.searchPlayers(frag.passingTeamName())
        }
        presenterPlayers.searchPlayers(frag.passingTeamName())
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
            swipeRefreshPlayers = swipeRefreshLayout {
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )
                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)
                    recyclerViewPlayers = recyclerView {
                        id = recyclerViewId
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(requireContext())
                    }
                    progressBarPlayers = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }


    override fun showLoading() {
        progressBarPlayers.visible()
    }

    override fun hideLoading() {
        progressBarPlayers.invisible()
    }

    override fun showMatch(data: List<Players>) {
        swipeRefreshPlayers.isRefreshing = false
        playersTeam.clear()
        playersTeam.addAll(data)
        adapterPlayers.notifyDataSetChanged()
    }
}