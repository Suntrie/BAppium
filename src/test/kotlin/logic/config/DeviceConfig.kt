package logic.config

import org.openqa.selenium.remote.DesiredCapabilities

interface DeviceConfig {
    val caps: DesiredCapabilities?
    val url: String?

    val user: String?

    val password: String?

    fun getProperty(key: String?): String?
}
