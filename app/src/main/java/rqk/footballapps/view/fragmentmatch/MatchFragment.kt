package rqk.footballapps.view.fragmentmatch


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_match_fragment.*
import org.jetbrains.anko.startActivity
import rqk.footballapps.R
import rqk.footballapps.R.id.search_me
import rqk.footballapps.adapter.ViewPagerAdapterMatch
import rqk.footballapps.view.search.SearchEvent

class MatchFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val fragAdapter = ViewPagerAdapterMatch(childFragmentManager)
        pager.adapter = fragAdapter
        tabLayout.setupWithViewPager(pager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_match_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.inflateMenu(R.menu.search_menu)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                search_me -> {
                    requireContext().startActivity<SearchEvent>()
                    true
                }

            }
            true
        }
    }
}
