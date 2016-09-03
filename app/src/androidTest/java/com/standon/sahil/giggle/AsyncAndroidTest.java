package com.standon.sahil.giggle;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AsyncAndroidTest extends AndroidTestCase implements JokeReceiver{
    /*@Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.standon.sahil.giggle", appContext.getPackageName());
    }*/

    private String joke="testString";
    CountDownLatch timingSignal;

    @Test
    public void testAsyncTask() throws Exception {
        JokeFetchTask task = new JokeFetchTask();
        timingSignal = new CountDownLatch(1);
        task.execute(this);
        timingSignal.await();
        assertEquals("Here's a Joke. Laugh your brains out ;)", joke);
    }

    @Override
    public void jokeFetched(String joke) {
        this.joke = joke;
        timingSignal.countDown();
    }
}
