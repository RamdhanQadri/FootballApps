package rqk.footballapps.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import rqk.footballapps.R
import rqk.footballapps.R.id.*
import rqk.footballapps.view.favorites.FavoritesActivity
import rqk.footballapps.view.fragmentmatch.MatchFragment
import rqk.footballapps.view.fragmentteams.TeamsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                matches -> {
                    supportActionBar?.hide()
                    loadMatchesFragment(savedInstanceState)
                }
                teams -> {
                    supportActionBar?.show()
                    loadTeamFragment(savedInstanceState)
                }
                favorites -> {
                    supportActionBar?.hide()
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = matches

    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment, FavoritesActivity(), FavoritesActivity::class.java.simpleName)
                .commit()
        }
    }

    private fun loadMatchesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment, MatchFragment(), MatchFragment::class.java.simpleName)
                .commit()
        }

    }

    private fun loadTeamFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment, TeamsFragment(), TeamsFragment::class.java.simpleName)
                .commit()
        }

    }
}
