package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Topic_20_Wait_PVIII_Page_Loading {

    WebDriver driver;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;


    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_OrangeHRM() {
        driver.get("https://api.orangehrm.com/#api-_/");
        //wait cho spinner invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(),"OrangeHRM Rest API Documentation");
    }
    @Test
    public void TC_02_Page_Ready() {
        driver.get("https://api.orangehrm.com/#api-_/");
        //wait cho page load success/ready
        Assert.assertTrue(isPageLoadedSuccess());

        Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(),"OrangeHRM Rest API Documentation");
    }

    //only jquery
    public boolean isPageLoadedSuccess(){
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));
        jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jquery != null) && (jQuery.active === 0);");
            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
                }catch (Exception e){
                    return  true;
                }
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    //jquey and js
    public boolean isJQueryAndPageLoadedSuccess( ){
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));
        jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                }catch (Exception e){
                    return  true;
                }
            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
                }catch (Exception e){
                    return  true;
                }
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

    public String getTimeNow(){
        Date date = new Date();
        return date.toString();
    }
}
