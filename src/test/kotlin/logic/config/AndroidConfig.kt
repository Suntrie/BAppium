package logic.config

import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.DesiredCapabilities

class AndroidConfig : MobileConfig(), DeviceConfig {
    override val caps: DesiredCapabilities
        get() {
            val capabilities = DesiredCapabilities()
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, getProperty("android_automationName"))
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getProperty("android_deviceName"))
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, getProperty("android_platformName"))
            capabilities.setCapability(MobileCapabilityType.APP, getProperty("android_appPath"))
            capabilities.setCapability(MobileCapabilityType.FULL_RESET, getProperty("android_fullReset"))
            capabilities.setCapability(MobileCapabilityType.NO_RESET, getProperty("android_noReset"))
            capabilities.setCapability("androidInstallTimeout", 150000)
            capabilities.setCapability("adbExecTimeout", 50000)

            return capabilities
        }

    override val url: String
        get() = getProperty("android_appiumServer")

    override val user: String
        get(){
            return  _user
        }

    override val password: String
        get(){
            return  _password
        }

}