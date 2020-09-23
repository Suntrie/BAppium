package logic.drivers


object DriverFactory {
    fun getDriver(platformName: String?): DeviceDriver? {
        if (platformName == null) {
            return null
        }
        return if (platformName.equals("android", ignoreCase = true)) {
            AndroidDriver()
        } else if (platformName.equals("iOS", ignoreCase = true)) {
            IosDriver()
        } else {
            null
        }
    }
}