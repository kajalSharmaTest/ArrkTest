package com.arrk.arrkandroidtest;

/**
 * Created by Kajal on 19/05/2018.
 */

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.arrk.arrkandroidtest.utils.PaginationAdapter;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Test class showcasing some  RecyclerViewActions from Espresso.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestPeopleList {

    private static final int ITEM_INDEX = 0;

    @Rule
    public ActivityTestRule<StarWarCharacterActivity> mActivityRule = new ActivityTestRule<>(
            StarWarCharacterActivity.class);

    @Test
    public void scrollToItemBelowFold_checkItsText() {
        // First scroll to the position that needs to be matched and click on it.{
        try{
            Thread.sleep(2000);
        } catch (Exception e){
            e.printStackTrace();
        }
        onView(ViewMatchers.withId(R.id.main_recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_INDEX, click()));

        // Match the text in an item below the fold and check that it's displayed.
        String itemElementText = "Luke Skywalker";
        onView(withText(itemElementText)).check(matches(isDisplayed()));
    }

}
