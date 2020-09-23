package logic.utils

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.nativekey.AndroidKey
import io.appium.java_client.android.nativekey.KeyEvent


interface ClickAction {
    fun action (driver: AppiumDriver<MobileElement?>, mobileElement: MobileElement)
}

object IOSClickAction: ClickAction {
    override fun action(driver: AppiumDriver<MobileElement?>, mobileElement: MobileElement) {
        mobileElement.IOSClick(driver)
    }
}

object AndroidClickAction: ClickAction {
    override fun action(driver: AppiumDriver<MobileElement?>, mobileElement: MobileElement) {
        mobileElement.click()
    }

}

interface FilterAction {
    fun action (label: String, el: MobileElement):Boolean
}

object AndroidFilterAction: FilterAction {
    override fun action(label: String, el: MobileElement)=el.text==label
}

object IOSFilterAction: FilterAction {
    override fun action(label: String, el: MobileElement)=el.getAttribute("name")==label
}

interface SendKeysAndTerminatorAction{
    fun action(driver: AppiumDriver<MobileElement?>,mobileElement: MobileElement, text: String)
}

object AndroidSendKeysAndTerminatorAction: SendKeysAndTerminatorAction {
    override fun action(driver: AppiumDriver<MobileElement?>, mobileElement: MobileElement, text: String) {
        mobileElement.sendKeys(text);
        (driver as AndroidDriver<MobileElement?>).pressKey(KeyEvent(AndroidKey.ENTER))
    }
}
object IOSSendKeysAndTerminatorAction: SendKeysAndTerminatorAction{
    override fun action(driver: AppiumDriver<MobileElement?>,mobileElement: MobileElement, text: String){
        mobileElement.sendKeys(text+"\n");
    }
}