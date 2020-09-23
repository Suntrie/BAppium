package logic.drivers

import logic.config.DeviceConfig
import io.appium.java_client.AppiumDriver
import java.net.MalformedURLException

interface DeviceDriver {
    @Throws(MalformedURLException::class)
    fun getDriver(deviceConfig: DeviceConfig): AppiumDriver<*>?
}