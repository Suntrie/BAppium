package logic.utils

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import io.appium.java_client.ios.IOSTouchAction
import io.appium.java_client.touch.TapOptions
import io.appium.java_client.touch.offset.ElementOption
import java.util.HashMap
import kotlin.random.Random

object Registry{
    const val androidBundleId = "com.example.btest"
    const val iOSBundleId = "com.rxcap.meducation"
    val testMedicines = setOf("codeine", "nizoral", "morphine", "zirgan")
}

fun MobileElement.isChecked()=with(getAttribute("checked")){this!=null&&this.equals("true")}
fun MobileElement.resourceId()=getAttribute("resource-id")

fun AppiumDriver<MobileElement?>.iOSHideKeyboard(){

    val nextPattern="//XCUIElementTypeButton[@name=\"Next:\"]"
    val donePattern="//XCUIElementTypeButton[@name=\"Done\"]"

    var el=Wait.forElement(this, MobileBy.xpath(nextPattern))

    while(Wait.isVisible(this, el as MobileElement?))
    {
        (el as MobileElement).IOSClick(this)
        el=Wait.forElement(this, MobileBy.xpath(nextPattern))
    }

    val done=Wait.forElement(this, MobileBy.xpath(donePattern))
    (done as MobileElement?)?.IOSClick(this)
}

fun MobileElement.IOSClick(driver: AppiumDriver<MobileElement?>){
    val touch = IOSTouchAction(driver)
    touch.tap(
        TapOptions.tapOptions()
            .withElement(ElementOption.element(this))
    )
        .perform()
}

fun AppiumDriver<MobileElement?>.confirmIOSPushes(){
    Wait.alertIsPresent(this)
    val args = HashMap<String, String>()
    args["action"] = "accept";
    this.executeScript("mobile: alert", args);
}

fun generatePassword():String{
    val lowerCaseChars = ('a'..'z')
    val upperCaseChars = ('A'..'Z')
    val numbers = ('0'..'9')

    val specialSymbols1 = ('!'..'/')
    val specialSymbols2 = (':'..'@')
    val specialSymbols3 = ('{'..'~')

    val baseSymbols = setOf(lowerCaseChars, upperCaseChars, numbers)
    val specialSymbols = listOf(specialSymbols1, specialSymbols2, specialSymbols3)

    val password = mutableListOf<Char>()

    while(password.size<7) {
        for (el in baseSymbols) {
            password.addAll(MutableList(Random.nextInt(1, 5))
            { el.random() })
        }

        val targetSpecialSymbols =
            specialSymbols[Random.nextInt(specialSymbols.size)]

        password.addAll(MutableList(Random.nextInt(2, 5))
        { targetSpecialSymbols.random() })
    }

    return password.joinToString(separator = "")
}