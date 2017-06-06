package com.example.noahkim.bakingtime;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.noahkim.bakingtime.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by Noah on 6/2/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityScreenTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void MainActivityTest() {
        onView(withId(R.id.recyclerview_recipes))
                .perform(actionOnItemAtPosition(0, click()));

//        onView(withId(R.id.ingredient_name))
//                .check(matches(isDisplayed()));

    }
}
