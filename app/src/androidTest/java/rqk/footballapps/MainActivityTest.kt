package rqk.footballapps

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import org.hamcrest.core.AllOf
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import rqk.footballapps.R.id.*
import rqk.footballapps.view.MainActivity

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun AddAndRemoveEventAndClubTest() {
        //Add Event Next Match English Premier League To Favorite
        Thread.sleep(3000)
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(spinnerId))).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).perform(scrollToPosition<RecyclerView.ViewHolder>(10))
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                10,
                click()
            )
        )
        Thread.sleep(3000)
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(3000)
        pressBack()


        //Add Event Last Event Italian Serie A To Favorite
        Thread.sleep(3000)
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(allOf(withText("Last Match"))).perform(click())
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(spinnerId))).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(spinnerId))).perform(click())
        Thread.sleep(3000)
        onView(allOf(withText("Italian Serie A"))).perform(click())
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).perform(scrollToPosition<RecyclerView.ViewHolder>(10))
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                10,
                click()
            )
        )
        Thread.sleep(3000)
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(3000)
        pressBack()

        //Add Club Italian Serie A To Favorite
        Thread.sleep(3000)
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(withId(teams)).perform(click())
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(spinnerId))).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(spinnerId))).perform(click())
        Thread.sleep(3000)
        onView(allOf(withText("Italian Serie A"))).perform(click())
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).perform(scrollToPosition<RecyclerView.ViewHolder>(10))
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                10,
                click()
            )
        )
        Thread.sleep(3000)
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(3000)
        pressBack()

        //Detail Players English Premier League and Add Club English Premier League To Favorite
        Thread.sleep(3000)
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(withId(teams)).perform(click())
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(spinnerId))).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Thread.sleep(3000)
        onView(allOf(withText("Players"))).perform(click())
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Thread.sleep(3000)
        pressBack()
        Thread.sleep(3000)
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(3000)
        pressBack()

        //Remove Event and Club From Favorite
        //Remove Event
        Thread.sleep(3000)
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(withId(favorites)).perform(click())
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Thread.sleep(3000)
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(3000)
        pressBack()

        Thread.sleep(3000)
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(withId(favorites)).perform(click())
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Thread.sleep(3000)
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(3000)
        pressBack()

        //Remove Club
        Thread.sleep(3000)
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(withId(favorites)).perform(click())
        Thread.sleep(3000)
        onView(allOf(withText("Team"))).perform(click())
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Thread.sleep(3000)
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(3000)
        pressBack()

        Thread.sleep(3000)
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(withId(favorites)).perform(click())
        Thread.sleep(3000)
        onView(allOf(withText("Team"))).perform(click())
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(allOf(isDisplayed(), withId(recyclerViewId))).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Thread.sleep(3000)
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(3000)
        pressBack()

        //Add Event From Search Event To Favorite
        Thread.sleep(3000)
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(withId(favorites)).perform(click())
        Thread.sleep(3000)
        onView(withId(matches)).perform(click())
        Thread.sleep(3000)
        onView(withId(search_me)).check(matches(isDisplayed()))
        Thread.sleep(3000)
        onView(withId(search_me)).perform(click())
        Thread.sleep(3000)
        onView(isAssignableFrom(EditText::class.java)).perform(typeText("Inter"))
        Thread.sleep(3000)
        onView(AllOf.allOf(isDisplayed(), withId(recyclerViewId))).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        Thread.sleep(3000)
        onView(withId(add_to_favorite)).perform(click())
        Thread.sleep(3000)
    }
}