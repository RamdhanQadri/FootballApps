package rqk.footballapps.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import rqk.footballapps.view.fragmentmatch.LastMatchFragment
import rqk.footballapps.view.fragmentmatch.NextMatchFragment

class ViewPagerAdapterMatch(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(p0: Int): Fragment {
        return when (p0) {
            0 -> {
                NextMatchFragment()
            }
            else -> {
                LastMatchFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 ->
                "Next Match"
            else -> {
                "Last Match"
            }
        }
    }
}