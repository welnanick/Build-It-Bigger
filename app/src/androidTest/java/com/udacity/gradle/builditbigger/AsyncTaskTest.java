package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {

    @Rule
    public final ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void idlingResourceTest() throws ExecutionException, InterruptedException {

        assertNotEquals("", new EndpointsAsyncTask().execute(activityTestRule.getActivity()).get());

    }
}
