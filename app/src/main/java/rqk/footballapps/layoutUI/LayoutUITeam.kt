package rqk.footballapps.layoutUI

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class LayoutUITeam : AnkoComponent<ViewGroup> {
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

                        imageView {
                            id = team_badge
                        }.lparams {
                            height = dip(50)
                            width = dip(50)
                        }

                        textView {
                            id = team_name
                            textSize = 16f
                        }.lparams {
                            margin = dip(15)
                        }
                    }
                }.lparams(matchParent, matchParent) {
                    setMargins(dip(8), dip(8), dip(8), dip(8))
                }
            }
        }
    }

    companion object Id {
        const val team_badge = 1
        const val team_name = 2
    }
}