package org.example

import com.microsoft.appcenter.appium.Factory
import io.qameta.allure.Feature
import io.qameta.allure.junit4.DisplayName
import org.junit.Rule
import org.junit.Test
import logic.utils.Registry
import logic.utils.Wait


@Feature("Installation, launch, deinstallation")
public class InitializationTest : AndroidBaseTest() {
    @Rule @JvmField
    public var watcher = Factory.createWatcher()


    @Test
    @DisplayName("Installation")
    fun installationTest() {


        assert(driver.isAppInstalled(Registry.androidBundleId)) { "Application is not installed" }

        driver.removeApp(Registry.androidBundleId)

        Wait.customDelay(5000)

        assert(!driver.isAppInstalled(Registry.androidBundleId)) { "Application is installed" }

    }


}