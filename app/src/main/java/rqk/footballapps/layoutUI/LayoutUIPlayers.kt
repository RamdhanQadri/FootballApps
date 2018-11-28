package rqk.footballapps.layoutUI

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class LayoutUIPlayers : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(matchParent, wrapContent)
                orientation = LinearLayout.VERTICAL

                cardView {
                    linearLayout {
                        backgroundColor = Color.GRAY
                        padding = dip(10)
                        orientation = LinearLayout.HORIZONTAL
                        gravity = Gravity.CENTER_HORIZONTAL

                        linearLayout {
                            gravity = Gravity.CENTER

                            imageView {
                                id = players_badge
                            }.lparams {
                                height = dip(50)
                                width = dip(50)
                            }
                        }

                        linearLayout {
                            gravity = Gravity.CENTER
                            textView {
                                id = players_name
                                textSize = 16f
                            }.lparams(width = dip(90), height = wrapContent)
                        }.lparams {
                            topMargin = dip(5)
                            weight = 2F
                        }

                        linearLayout {
                            gravity = Gravity.CENTER
                            textView {
                                id = players_position
                                textSize = 16f
                            }.lparams(width = dip(90), height = wrapContent)
                        }.lparams {
                            topMargin = dip(5)
                            weight = 2F
                        }
                    }
                }.lparams(matchParent, matchParent) {
                    setMargins(dip(8), dip(8), dip(8), dip(8))
                }
            }
        }
    }

    companion object Id {
        const val players_badge = 1
        const val players_name = 2
        const val players_position = 3
    }
}