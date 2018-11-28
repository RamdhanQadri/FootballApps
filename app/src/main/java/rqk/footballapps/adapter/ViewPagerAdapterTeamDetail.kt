package rqk.footballapps.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import rqk.footballapps.view.fragmentteams.fragment.TeamsOverview
import rqk.footballapps.view.fragmentteams.fragment.TeamsPlayers

class ViewPagerAdapterTeamDetail(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(p0: Int): Fragment {
        return when (p0) {
            0 -> {
                TeamsOverview()
            }
            else -> {
                TeamsPlayers()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 ->
                "Overview"
            else -> {
                "Players"
            }
        }
    }
}