package logic.utils

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.android.AndroidTouchAction
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption
import org.aspectj.runtime.internal.Conversions
import org.openqa.selenium.By
import logic.utils.Direction.DOWN
import logic.utils.Direction.UP
import java.time.Duration

class MobileActions(private val driver: AppiumDriver<MobileElement>) {
    private var startX = 0.0
    private var endX = 0.0
    private var startY = 0.0
    private var endY = 0.0

    fun swipe(direction: Direction?) {
        when (direction) {
            UP -> {
                run {
                    endX = driver.manage().window().size.getWidth() * 0.5
                    startX = endX
                }
                startY = driver.manage().window().size.getHeight() * 0.9
                endY = driver.manage().window().size.getHeight() * 0.2
            }
            DOWN -> {
                run {
                    endX = driver.manage().window().size.getWidth() * 0.5
                    startX = endX
                }
                startY = driver.manage().window().size.getHeight() * 0.2
                endY = driver.manage().window().size.getHeight() * 0.9
            }
        }
        touchAction(startX, startY, endX, endY)
    }

    fun swipeToElement(direction: Direction?, element: By?, xPercent: Double) {
        var counter = 0
        var isFoundElement = false
        while (!isFoundElement || counter == 5) {
            when (direction) {
                UP -> {
                    run {
                        endX = driver.manage().window().size.getWidth() * xPercent
                        startX = endX
                    }
                    startY = driver.manage().window().size.getHeight() * 0.9
                    endY = driver.manage().window().size.getHeight() * 0.2
                    if (driver.findElements(element).size > 0) {
                        isFoundElement = true
                    } else {
                        counter += 1
                        touchAction(startX, startY, endX, endY)
                    }
                }
                DOWN -> {
                    run {
                        endX = driver.manage().window().size.getWidth() * xPercent
                        startX = endX
                    }
                    startY = driver.manage().window().size.getHeight() * 0.2
                    endY = driver.manage().window().size.getHeight() * 0.9
                    if (driver.findElements(element).size > 0) {
                        isFoundElement = true
                    } else {
                        counter += 1
                        touchAction(startX, startY, endX, endY)
                    }
                }
            }
        }
    }

    fun touchAction(
        startX: Double?,
        startY: Double?,
        endX: Double?,
        endY: Double?
    ) {
        touchAction(
            Conversions.intValue(startX),
            Conversions.intValue(startY),
            Conversions.intValue(endX),
            Conversions.intValue(endY)
        )
    }

    fun touchAction(startX: Int, startY: Int, endX: Int, endY: Int) {
        val duration: Long = 1000
        AndroidTouchAction(driver)
            .press(PointOption.point(startX, startY))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
            .moveTo(PointOption.point(endX, endY))
            .release()
            .perform()
    }

    fun swipeByElements(startElement: MobileElement, endElement: MobileElement) {
        swipeByElements(startElement as AndroidElement, endElement as AndroidElement)
    }

    fun swipeByElements(startElement: AndroidElement, endElement: AndroidElement) {
        val startX = startElement.location.getX() + startElement.size.getWidth() / 2
        val startY = startElement.location.getY() + startElement.size.getHeight() / 2
        val endX = endElement.location.getX() + endElement.size.getWidth() / 2
        val endY = endElement.location.getY() + endElement.size.getHeight() / 2
        AndroidTouchAction(driver) //TODO discuss
            .press(PointOption.point(startX, startY))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
            .moveTo(PointOption.point(endX, endY))
            .release().perform()
    }

    //TODO: what is swipe wave?

}