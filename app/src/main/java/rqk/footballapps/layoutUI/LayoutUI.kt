package rqk.footballapps.layoutUI

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import rqk.footballapps.R

class LayoutUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(matchParent, wrapContent)
                orientation = LinearLayout.VERTICAL

                cardView {

                    linearLayout {
                        backgroundColor = Color.GRAY
                        orientation = LinearLayout.VERTICAL
                        padding = dip(10)

                        relativeLayout {
                            textView {
                                id = textViewDate
                                textSize = 14f
                                gravity = Gravity.CENTER
                                textColorResource = android.R.color.black
                            }.lparams(wrapContent, wrapContent) {
                                centerInParent()
                            }

                            imageButton(R.drawable.icons_alarm) {
                                id = btn_notify
                                backgroundColor = Color.GRAY
                                topPadding = dip(8)
                                rightPadding = dip(8)
                                visibility = View.INVISIBLE
                            }.lparams(wrapContent, wrapContent) {
                                alignParentRight()
                            }
                        }.lparams(matchParent, wrapContent)

                        textView {
                            id = textViewTime
                            textSize = 14f
                            gravity = Gravity.CENTER
                            textColorResource = android.R.color.black
                        }.lparams(matchParent, wrapContent)

                        linearLayout {
                            padding = dip(3)
                            orientation = LinearLayout.HORIZONTAL
                            gravity = Gravity.CENTER
                            weightSum = 3F

                            linearLayout {
                                gravity = Gravity.CENTER

                                textView {
                                    id = textViewHomeTeam
                                    textSize = 16f
                                    textColorResource = android.R.color.black
                                    gravity = Gravity.CENTER
                                }.lparams(width = dip(80), height = wrapContent)
                            }.lparams {
                                topMargin = dip(5)
                                weight = 1F
                            }

                            linearLayout {

                                linearLayout {
                                    gravity = Gravity.CENTER

                                    textView {
                                        id = testViewHomeScore
                                        textSize = 16f
                                        textColorResource = android.R.color.black
                                    }
                                }.lparams {
                                    topMargin = dip(5)
                                    weight = 0.1F
                                }

                                linearLayout {
                                    gravity = Gravity.CENTER

                                    textView {
                                        textResource = R.string.match
                                        textSize = 16f
                                    }
                                }.lparams {
                                    topMargin = dip(5)
                                    weight = 0.1F
                                }

                                linearLayout {
                                    gravity = Gravity.CENTER

                                    textView {
                                        id = textViewAwayScore
                                        textSize = 16f
                                        textColorResource = android.R.color.black
                                    }
                                }.lparams {
                                    topMargin = dip(5)
                                    weight = 0.1F
                                }
                            }.lparams {
                                topMargin = dip(5)
                                weight = 1F
                            }

                            linearLayout {
                                gravity = Gravity.RIGHT

                                textView {
                                    id = textViewAwayTeam
                                    textSize = 16f
                                    textColorResource = android.R.color.black
                                }.lparams(width = dip(80), height = wrapContent)
                            }.lparams {
                                topMargin = dip(5)
                                weight = 1F
                            }
                        }.lparams(matchParent, matchParent)
                    }
                }.lparams(matchParent, matchParent) {
                    setMargins(dip(8), dip(8), dip(8), dip(8))
                }
            }

        }

    }

    companion object Id {
        const val textViewDate = 1
        const val btn_notify = 2
        const val textViewTime = 3
        const val textViewHomeTeam = 4
        const val testViewHomeScore = 5
        const val textViewAwayScore = 6
        const val textViewAwayTeam = 7
    }
}