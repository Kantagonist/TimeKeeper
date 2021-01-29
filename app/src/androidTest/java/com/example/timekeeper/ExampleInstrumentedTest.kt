package com.example.timekeeper

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.timekeeper.internal.TimePoint

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.timekeeper", appContext.packageName)
    }

    @Test
    fun testCSVReader(){
        val timePoints = TimePoint.getListOfTimePointsSince(1)
        assertEquals(timePoints[0].TimeInMiliseconds, 1611910435903)
        assertEquals(timePoints[0].Id, "hello")
    }
}