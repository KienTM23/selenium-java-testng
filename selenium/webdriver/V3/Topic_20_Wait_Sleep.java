package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_20_Wait_Sleep {

    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    //3 bien cho file

    By startButton = By.cssSelector("div#start>button");
    By loadingIcon = By.cssSelector("div#loading");
    By helloText = By.cssSelector("div#finish>h4");
    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();

    }

    @Test
    public void TC_01_Less_Than(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(startButton).click();

        sleep(3);
        Assert.assertEquals(driver.findElement(helloText).getText(),"Hello World!");
    }
    @Test
    public void TC_02_Equal(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.findElement(startButton).click();
        sleep(5);
        Assert.assertEquals(driver.findElement(helloText).getText(),"Hello World!");
    }
    @Test
    public void TC_03_More_Than(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

        driver.findElement(startButton).click();
        sleep(10);
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
