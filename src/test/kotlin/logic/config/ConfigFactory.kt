package logic.config

class ConfigFactory {
    fun getDeviceConfig(platformName: String?): DeviceConfig? {
        if (platformName == null) {
            return null
        }
        return if (platformName.equals("android", ignoreCase = true)) {
            AndroidConfig()
        } else if(platformName.equals("iOS")) {
                return IOSConfig();
            } else {
            null
        }
    }
}