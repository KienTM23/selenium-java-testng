package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_20_Wait_Explicit {

    WebDriver driver;
    WebDriverWait explicitWait;

    //WebDriverWait extends FluentWait<Webdriver>
    //FluentWait<T> implements Wait<T>
    String projectPath = System.getProperty("user.dir");
    //3 bien cho file

    By startButton = By.cssSelector("div#start>button");
    By loadingIcon = By.cssSelector("div#loading");
    By helloText = By.cssSelector("div#finish>h4");
    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        //Tong thoi gian la 15s
        //Time lap lai tim element mac dinh la 0.5s
        //ko cho het 15s
        //den s thu 5 la no pass
        //qua step tiep
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        //Tong thoi gian la 15s
        //Time lap lai tim element mac dinh la 1s
//        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15),Duration.ofMillis(1000));
    }

    @Test
    public void TC_01_Invisible(){
        driver.get("https://automationfc.github.io/dynamic-loading/");


        //wait cho start button dc click hay chua
        //cho cho start button dc phep click trong vong 15s
        explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
        driver.findElement(startButton).click();

        //wait cho loading icon bien mat trong 15s
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
        Assert.assertEquals(driver.findElement(helloText).getText(),"Hello World!");
    }
    @Test
    public void TC_02_Visible(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        //wait cho start button dc click hay chua
        //cho cho start button dc phep click trong vong 15s
        driver.findElement(startButton).click();
       //wait cho helloworld text hien thi trong vong 15s
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));
        Assert.assertEquals(driver.findElement(helloText).getText(),"Hello World!");
    }
    @Test
    public void TC_03_Visible_Less_Than(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3),Duration.ofSeconds(1));
        //wait cho start button dc click hay chua
        //cho cho start button dc phep click trong vong 15s
        driver.findElement(startButton).click();
        //wait cho helloworld text hien thi trong vong 15s
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));
        Assert.assertEquals(driver.findElement(helloText).getText(),"Hello World!");
    }
    @Test
    public void TC_04_Visible_Equal(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //wait cho start button dc click hay chua
        //cho cho start button dc phep click trong vong 15s
        driver.findElement(startButton).click();
        //wait cho helloworld text hien thi trong vong 15s
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));
        Assert.assertEquals(driver.findElement(helloText).getText(),"Hello World!");
    }
    @Test
    public void TC_04_Visible_More_Than(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        //wait cho start button dc click hay chua
        //cho cho start button dc phep click trong vong 15s
        driver.findElement(startButton).click();
        //wait cho helloworld text hien thi trong vong 15s
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));
        Assert.assertEquals(driver.findElement(helloText).getText(),"Hello World!");
    }


    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

    public void sleep(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
