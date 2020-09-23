package logic.drivers

import logic.config.DeviceConfig
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.WebElement
import java.net.MalformedURLException
import java.net.URL

class AndroidDriver : DeviceDriver {
    @Throws(MalformedURLException::class)
    override fun getDriver(deviceConfig: DeviceConfig): AppiumDriver<*>? {
        return AndroidDriver<WebElement?>(URL(deviceConfig.url), deviceConfig.caps)
    }

}