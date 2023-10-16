/*
 * ===========================================================================================
 *  COPYRIGHT
 *           PAX Computer Technology(Shenzhen) CO., LTD PROPRIETARY INFORMATION
 *  This software is supplied under the terms of a license agreement or nondisclosure
 *  agreement with PAX Computer Technology(Shenzhen) CO., LTD and may not be copied or
 *  disclosed except in accordance with the terms in that agreement.
 *           Copyright (C) 2021-2023 PAX Computer Technology(Shenzhen) CO., LTD All
 *   rights reserved.
 *
 *  Revision History:
 *  Date                           Author                      Action
 *  2023/10/16                     likewei                     Create
 * ===========================================================================================
 *
 */

package com.example.lifecycletest

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

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
        assertEquals("com.example.lifecycletest", appContext.packageName)
    }
}