package logic.config

import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.DesiredCapabilities

class IOSConfig : MobileConfig(), DeviceConfig {
    override val caps: DesiredCapabilities
        get() {
            val capabilities = DesiredCapabilities()
            capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, getProperty("appium-version"))
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, getProperty("ios_automationName"))
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getProperty("ios_deviceName"))
            capabilities.setCapability(MobileCapabilityType.UDID, "a4fcb073a0ec0c03c3f5f58110185440d76392c3")
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, getProperty("ios_platformName"))
            capabilities.setCapability(MobileCapabilityType.APP, getProperty("ios_appPath"))
            capabilities.setCapability(MobileCapabilityType.FULL_RESET, getProperty("ios_fullReset"))
            capabilities.setCapability("platformVersion", getProperty("ios_platformVersion"))
            capabilities.setCapability("bundleId", getProperty("ios_bundleId"))
            capabilities.setCapability("newCommandTimeout", 240)
            return capabilities
        }

    override val url: String
        get() = getProperty("ios_appiumServer")

    override val user: String
        get(){
            return  _user
        }

    override val password: String
        get(){
            return  _password
        }

}