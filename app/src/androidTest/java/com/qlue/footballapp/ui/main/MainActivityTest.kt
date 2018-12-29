package com.qlue.footballapp.ui.main

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeLeft
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.qlue.footballapp.R
import com.qlue.footballapp.utils.IdlingResource.FetchingIdlingResource
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {


    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun mainActivityTest() {

        delay()

        onView(withId(R.id.rvFootballLastMatch))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rvFootballLastMatch)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )
        onView(withId(R.id.rvFootballLastMatch)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(8, click())
        )

        delay()
        onView(withId(R.id.favorite)).perform(click())

        pressBack()

        delay()

        onView(withId(R.id.viewpager)).perform(swipeLeft())

        delay()

        onView(withId(R.id.rvFootballNextMatch))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rvFootballNextMatch)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )
        onView(withId(R.id.rvFootballNextMatch)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click())
        )
        onView(withId(R.id.awayImg)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAwayDetailName)).check(matches(isDisplayed()))
        onView(withId(R.id.homeImg)).check(matches(isDisplayed()))
        onView(withId(R.id.tvHomeDetailName)).check(matches(isDisplayed()))

        delay()

        pressBack()

        onView(withId(R.id.upMatch)).perform(click())

        delay()
        onView(withId(R.id.rvTeam))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rvTeam))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rvTeam)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(R.id.rvTeam)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click())
        )

        delay()

        pressBack()

        onView(withId(R.id.favMatch)).perform(click())

        delay()
        onView(withId(R.id.rvFavourite))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rvFavourite)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(R.id.rvFavourite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        onView(withId(R.id.awayImg)).check(matches(isDisplayed()))
        onView(withId(R.id.homeImg)).check(matches(isDisplayed()))

        onView(withId(R.id.favorite)).perform(click())

        delay()

        pressBack()
    }

    private fun delay() {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}