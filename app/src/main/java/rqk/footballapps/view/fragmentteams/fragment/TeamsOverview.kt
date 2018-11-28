package rqk.footballapps.view.fragmentteams.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import rqk.footballapps.R.color.colorAccent
import rqk.footballapps.api.ApiRepository
import rqk.footballapps.interfac.ViewTeam
import rqk.footballapps.model.Team
import rqk.footballapps.presenter.PresenterTeam
import rqk.footballapps.view.details.DetailTeam

class TeamsOverview : Fragment(), AnkoComponent<Context>, ViewTeam {
    private lateinit var presenterTeam: PresenterTeam
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var teamDescription: TextView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val frag = activity as DetailTeam
        val request = ApiRepository()
        val gson = Gson()
        presenterTeam = PresenterTeam(this, request, gson)
        presenterTeam.teamDetail(frag.passingTeamId())

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(requireContext().let { AnkoContext.create(it) })
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL
            backgroundColor = Color.WHITE

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )

                scrollView {
                    isVerticalScrollBarEnabled = false
                    relativeLayout {
                        lparams(matchParent, wrapContent)

                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.RIGHT

                            teamDescription = textView().lparams {
                                setMargins(dip(8), dip(8), dip(8), dip(8))
                            }
                        }
                    }
                }
            }
        }
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showMatch(data: List<Team>) {
        teamDescription.text = data[0].teamDescription
    }
}