package com.example.myapplicationdisco

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplicationdisco.view.CnnGrids
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CnnGridsTest {

    private lateinit var scenario: ActivityScenario<CnnGrids>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(CnnGrids::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun testGridViewsDisplayed() {
        // Check if grid views are displayed
        onView(withId(R.id.gridView)).check(matches(isDisplayed()))
        onView(withId(R.id.gridView2)).check(matches(isDisplayed()))
    }

    @Test
    fun testButtonClick() {
        // Perform click on buttonGoToMain
        onView(withId(R.id.buttonGoToMain)).perform(click())


    }
}
