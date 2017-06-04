package com.example.noahkim.bakingtime;

import android.app.Activity;
import android.app.Instrumentation.ActivityResult;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.noahkim.bakingtime.ui.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Noah on 6/3/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityIntentTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule =
            new IntentsTestRule<>(MainActivity.class);

    @Before
    public void stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(not(isInternal())).respondWith(new ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void clickRecipeOpensDetails() {

        onView(withId(R.id.card_view)).perform(click());
        // intended(Matcher<Intent> matcher) asserts the given matcher matches one and only one
        // intent sent by the application.
        intended(allOf(
                hasExtra(MainActivity.RECIPE_DETAILS, 2)));

    }
}
