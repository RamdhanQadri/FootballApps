package rqk.footballapps.view.details

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Gravity
import android.view.MenuItem
import android.widget.*
import com.bumptech.glide.Glide
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import rqk.footballapps.R
import rqk.footballapps.api.ApiRepository
import rqk.footballapps.interfac.ViewPlayers
import rqk.footballapps.model.Players
import rqk.footballapps.presenter.PresenterPlayers
import rqk.footballapps.utils.invisible
import rqk.footballapps.utils.visible

class DetailPlayers : AppCompatActivity(), ViewPlayers {
    private lateinit var presenterDetailPlayers: PresenterPlayers
    private lateinit var scrollV: ScrollView
    private lateinit var progressBarDetail: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var imagePlayers: ImageView
    private lateinit var weightPlayers: TextView
    private lateinit var heightPlayers: TextView
    private lateinit var posisiPlayers: TextView
    private lateinit var rincianPlayers: TextView
    private lateinit var namePlayers: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        namePlayers = intent.getStringExtra("namePlayers")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = namePlayers

        linearLayout {
            swipeRefreshLayout = swipeRefreshLayout {
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )
                scrollV = scrollView {
                    isVerticalScrollBarEnabled = false
                    linearLayout {
                        lparams(matchParent, matchParent)
                        orientation = LinearLayout.VERTICAL

                        imagePlayers = imageView {
                            scaleType = ImageView.ScaleType.CENTER
                            fitsSystemWindows = true
                        }.lparams(matchParent, dip(220))

                        //------------------------------------
                        cardView {
                            linearLayout {
                                padding = dip(3)
                                orientation = LinearLayout.HORIZONTAL
                                gravity = Gravity.CENTER
                                weightSum = 3F
                                linearLayout {
                                    orientation = LinearLayout.VERTICAL
                                    gravity = Gravity.CENTER

                                    linearLayout {
                                        gravity = Gravity.CENTER

                                        textView {
                                            textResource = R.string.weight
                                            textSize = 16f
                                        }
                                    }.lparams {
                                        topMargin = dip(5)
                                        weight = 0.1F
                                    }

                                    linearLayout {
                                        gravity = Gravity.CENTER

                                        weightPlayers = textView {
                                            textSize = 16f
                                            textColorResource = android.R.color.black
                                        }
                                    }.lparams {
                                        topMargin = dip(5)
                                        weight = 0.1F
                                    }
                                }.lparams {
                                    topMargin = dip(5)
                                    weight = 2F
                                }

                                linearLayout {
                                    orientation = LinearLayout.VERTICAL
                                    gravity = Gravity.CENTER

                                    linearLayout {
                                        gravity = Gravity.CENTER

                                        textView {
                                            textResource = R.string.height
                                            textSize = 16f
                                        }
                                    }.lparams {
                                        topMargin = dip(5)
                                        weight = 0.1F
                                    }

                                    linearLayout {
                                        gravity = Gravity.CENTER

                                        heightPlayers = textView {
                                            textSize = 16f
                                            textColorResource = android.R.color.black
                                        }
                                    }.lparams {
                                        topMargin = dip(5)
                                        weight = 0.1F
                                    }
                                }.lparams {
                                    topMargin = dip(5)
                                    weight = 2F
                                }
                            }
                        }.lparams(matchParent, matchParent)

                        linearLayout {
                            gravity = Gravity.CENTER
                            posisiPlayers = textView {
                                gravity = Gravity.CENTER
                                textSize = 16f
                                typeface = Typeface.DEFAULT_BOLD
                            }.lparams(width = wrapContent, height = wrapContent) {
                                setMargins(dip(2), dip(2), dip(2), dip(2))
                            }
                        }.lparams(matchParent, matchParent) {
                            setMargins(dip(5), dip(1), dip(5), dip(1))
                        }

                        cardView {
                            linearLayout {
                                orientation = LinearLayout.VERTICAL
                                gravity = Gravity.RIGHT

                                rincianPlayers = textView().lparams {
                                    setMargins(dip(8), dip(8), dip(8), dip(8))
                                }
                            }
                        }.lparams(matchParent, matchParent)
                    }
                }
            }
            progressBarDetail = progressBar {
            }
        }

        val request = ApiRepository()
        val gson = Gson()
        presenterDetailPlayers = PresenterPlayers(this, request, gson)
        swipeRefreshLayout.onRefresh {
            presenterDetailPlayers.searchDetailPlayers(namePlayers)
        }
        presenterDetailPlayers.searchDetailPlayers(namePlayers)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        progressBarDetail.visible()
    }

    override fun hideLoading() {
        progressBarDetail.invisible()
    }

    override fun showMatch(data: List<Players>) {
        Glide.with(this).load(data[0].fanartPlayers).into(imagePlayers)
        weightPlayers.text = data[0].weightPlayers
        heightPlayers.text = data[0].heightPlayers
        posisiPlayers.text = data[0].positionPlayers
        rincianPlayers.text = data[0].descriptionPlayers
    }
}
