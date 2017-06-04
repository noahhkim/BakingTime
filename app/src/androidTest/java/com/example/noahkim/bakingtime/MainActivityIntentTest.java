package com.example.noahkim.bakingtime;

import android.app.Activity;
import android.app.Instrumentation.ActivityResult;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.noahkim.bakingtime.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Noah on 6/3/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityIntentTest {
    private final String PACKAGE_NAME = "com.example.noahkim.bakingtime.ui.RecipeDetailsActivity";

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule =
            new IntentsTestRule<>(MainActivity.class);

//    @Before
//    public void stubAllExternalIntents() {
//        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
//        // every test run. In this case all external Intents will be blocked.
//        intending(not(isInternal())).respondWith(new ActivityResult(Activity.RESULT_OK, null));
//    }

    @Test
    public void clickRecipeOpensDetails() {
        // Build a result to return when a particular activity is launched.
        Intent intent = new Intent();
        intent.putExtra(MainActivity.RECIPE_DETAILS, 2);
        ActivityResult result = new ActivityResult(Activity.RESULT_OK, intent);

        // Set up result stubbing when an intent sent to "details" is seen.
        intending(toPackage(PACKAGE_NAME)).respondWith(result);

        // User action that results in details activity being launched.
        // Launching activity expects recipe name to be returned and displays it on the screen.
        onView(withId(R.id.recipe_list_frame)).perform(click());

        // Assert that data we set up above is shown.
        onView(withId(R.id.recipe_details_frame)).check(matches(withText(2)));
    }
}
