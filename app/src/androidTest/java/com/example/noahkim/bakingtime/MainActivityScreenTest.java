package com.example.noahkim.bakingtime;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.noahkim.bakingtime.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsAnything.anything;


/**
 * Created by Noah on 6/2/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityScreenTest {

    public static final String RECIPE_NAME = "brownies";


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Clicks on a GridView item and checks it opens up the RecipeDetailsActivity with the correct details.
     */
    @Test
    public void MainActivityTest() {

        // Uses {@link Espresso#onData(org.hamcrest.Matcher)} to get a reference to a specific
        // recyclerview item and clicks it.
        onData(anything()).inAdapterView(withId(R.id.recyclerview_recipes))
        .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        // Checks that the RecipeDetailsActivity opens with the correct tea name displayed
        onView(withId(R.id.toolbar)).check(matches(withText(RECIPE_NAME)));

    }
}
