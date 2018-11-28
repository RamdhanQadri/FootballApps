package rqk.footballapps.view.favorites

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_match_fragment.*
import rqk.footballapps.R
import rqk.footballapps.adapter.ViewPagerAdapterFavorites

class FavoritesActivity : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val fragAdapter = ViewPagerAdapterFavorites(childFragmentManager)
        pager.adapter = fragAdapter
        tabLayout.setupWithViewPager(pager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_match_fragment, container, false)
        return view
    }
}
