package com.blogspot.ketikanmd.thesportdbapp


import android.support.test.espresso.Espresso.onView
import android.support.test.runner.AndroidJUnit4
import com.blogspot.ketikanmd.thesportdbapp.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import com.blogspot.ketikanmd.thesportdbapp.R.id.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class testAppBehaviour {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testViewBehavior() {
        onView(withId(bottom_navigation))
                .check(matches(isDisplayed()))
        onView(withId(sp_match))
                .check(matches(isDisplayed()))
        onView(withId(sp_match)).perform(click())
        onView(withText("Spanish La Liga")).perform(click())
        Thread.sleep(3000)

        onView(withId(rv_prev))
                .check(matches(isDisplayed()))
        onView(withId(rv_prev)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))
        onView(withId(rv_prev)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        Thread.sleep(3000)
        onView(withId(add_to_favoriteMatch))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favoriteMatch)).perform(click())
        Thread.sleep(3000)
        pressBack()

        onView(withId(Team)).perform(click())
        onView(withId(rv_team)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(rv_team)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        Thread.sleep(3000)
        onView(withId(add_to_favoriteMatch))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favoriteMatch)).perform(click())
        Thread.sleep(3000)
        pressBack()

        onView(withId(Favorite)).perform(click())
        Thread.sleep(3000)

        onView(withId(buttonAbout)).perform(click())
        Thread.sleep(3000)
        pressBack()

    }


}