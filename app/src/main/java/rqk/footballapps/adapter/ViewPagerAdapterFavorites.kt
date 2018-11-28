package rqk.footballapps.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import rqk.footballapps.view.favorites.MatchFvFragment
import rqk.footballapps.view.favorites.TeamFvFragment

class ViewPagerAdapterFavorites(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(p0: Int): Fragment {
        return when (p0) {
            0 -> {
                MatchFvFragment()
            }
            else -> {
                TeamFvFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 ->
                "Match"
            else -> {
                "Team"
            }
        }
    }
}