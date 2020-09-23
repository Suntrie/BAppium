package logic.config

import java.io.FileInputStream
import java.util.*

//TODO
abstract class MobileConfig {
    var props = Properties()
    var configPath = MobileConfig::class.java.classLoader.getResource("config.properties")!!.path
    var _password = getProperty("password")
    var _user = getProperty("user")

    fun getProperty(key: String?): String {

        FileInputStream(configPath).use{
            props.load(it)
        }

        return props.getProperty(key)
    }
}