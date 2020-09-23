package org.example

import com.microsoft.appcenter.appium.EnhancedAndroidDriver
import com.microsoft.appcenter.appium.Factory
import logic.config.AndroidConfig
import io.appium.java_client.MobileElement
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.net.URL


abstract class AndroidBaseTest {

    protected lateinit var driver: EnhancedAndroidDriver<MobileElement?>
    var androidConfig = AndroidConfig()

    @Before
    fun setUp() {
       this.driver = Factory.createAndroidDriver(URL(androidConfig.url), androidConfig.caps)//AndroidDriver(URL(androidConfig.url), androidConfig.caps)
    }

    @After
    fun tearDown() {
        this.driver.quit()
    }

    @Test
    fun test(){
        println(driver.pageSource)
    }
}