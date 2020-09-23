package logic.utils

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.openqa.selenium.*
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.*
import java.util.concurrent.Callable

object Wait {

    const val interval = 100
    const val timeout = 10000
    fun forElement(driver: AppiumDriver<MobileElement?>, by: By?): WebElement? {
        return if (whileExists(driver, by)) {
            driver.findElement(by)
        } else {
            null
        }
    }

    fun forElementAndClickIfElementExist(driver: AppiumDriver<MobileElement?>, by: By?, timeout: Int) {
        if (whileExists(driver, by, timeout)) {
            driver.findElement(by)?.click()
        }
    }

    fun forText(driver: AppiumDriver<MobileElement?>, text: String, timeout: Int): Boolean {
        return whileExists(driver, By.xpath("//*[@text='$text']"), timeout)
    }

    fun forTextContains(driver: AppiumDriver<MobileElement?>, text: String, timeout: Int): Boolean {
        return whileExists(driver, By.xpath("//*[contains(@text,'$text')]"), timeout)
    }

    fun IOSForValueContains(driver: AppiumDriver<MobileElement?>, text: String, timeout: Int): Boolean {
        return whileExists(driver, By.xpath("//*[contains(@value,'$text')]"), timeout)
    }

    fun whileExists(driver: WebDriver, by: By?): Boolean {
        var waitTime = 0
        do {
            try {
                Thread.sleep(interval.toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            waitTime += interval
        } while (!isVisible(driver, by) && waitTime <= timeout)

        return isVisible(driver, by)
    }

    fun whileExists(driver: AppiumDriver<MobileElement?>, by: By?, timeout: Int): Boolean {
        var waitTime = 0
        do {
            try {
                Thread.sleep(interval.toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            waitTime += interval
        } while (!isVisible(driver, by) && waitTime <= timeout)
        return isVisible(driver, by)
    }

    fun whileEmpty(driver: AppiumDriver<MobileElement?>, by: By?): Boolean {
        var waitTime = 0
        do {
            try {
                Thread.sleep(interval.toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            waitTime += interval
        } while (!isListNotEmpty(driver, by) && waitTime <= timeout)
        return isListNotEmpty(driver, by)
    }

    fun isListNotEmpty(driver: AppiumDriver<MobileElement?>, by: By?): Boolean {
        return try {
            driver.findElements(by).size > 0
        } catch (e: NoSuchElementException) {
            false
        }
    }

    fun isVisible(driver: AppiumDriver<MobileElement?>, el: MobileElement?):Boolean{
        return try{
            return el!=null && el.isDisplayed
        }catch(e: NoSuchElementException){
            false
        }
    }

    fun isVisible(driver: AppiumDriver<MobileElement?>, by: By?): Boolean {
        return try {
            val el= driver.findElement(by)
            return el!=null && el.isDisplayed
        } catch (e: NoSuchElementException) {
            false
        } catch (e: StaleElementReferenceException) {
            false
        }
    }

    fun isVisible(driver: WebDriver, by: By?): Boolean {
        return try {
            driver.findElement<WebElement>(by).isDisplayed
        } catch (e: NoSuchElementException) {
            false
        } catch (e: StaleElementReferenceException) {
            false
        }
    }

    fun untilClickable(driver: WebDriver?, by: By?): WebElement {
        val wait = WebDriverWait(driver, 15)
        return wait.until(ExpectedConditions.elementToBeClickable(by))
    }

    fun customDelay(ms: Int) {
        try {
            Thread.sleep(ms.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    @Throws(Exception::class)
    fun waitWhile(condition: Callable<*>, r: Runnable?, timeOut: Long) {
        var loopBreaker = 0
        do {
            r?.run()
            if (timeOut > 0) {
                Thread.sleep(timeOut)
            }
            loopBreaker++
        } while (condition.call() as Boolean && loopBreaker < 20)
    }

    fun getResourceId(id: String)=Registry.androidBundleId +":id/"+id

    fun getRandomTestString()=StringBuilder(UUID.randomUUID().toString()).takeLast(16).toString() //TODO: length

    fun alertIsPresent(driver: AppiumDriver<MobileElement?>){
        val wait=WebDriverWait(driver, timeout.toLong());
        wait.until(ExpectedConditions.alertIsPresent());
    }

}