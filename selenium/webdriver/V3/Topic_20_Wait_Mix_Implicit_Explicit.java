package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Topic_20_Wait_Mix_Implicit_Explicit {

    WebDriver driver;
    WebDriverWait explicitWait;


    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();

    }

    @Test
    public void TC_01_Element_Found() {
        driver.get("https://www.facebook.com/");

        By emailIDBy = By.id("email");

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        System.out.println("Start implicit ="+getTimeNow());
        driver.findElement(emailIDBy).isDisplayed();
        System.out.println("End implicit  =" + getTimeNow());

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        System.out.println("End explicit  =" + getTimeNow());
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIDBy));
        System.out.println("End explicit  =" + getTimeNow());
    }
    @Test
    public void TC_02_Element_Not_Found_Only_Implicit() {
        driver.get("https://www.facebook.com/");

        By emailIDBy = By.id("vietname");

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        System.out.println("Start implicit ="+getTimeNow());
        try {
            driver.findElement(emailIDBy).isDisplayed();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("End implicit  =" + getTimeNow());
    }
    @Test
    public void TC_03_Element_Not_Found_Only_Explicit_By() {
        driver.get("https://www.facebook.com/");

        By emailIDBy = By.id("email");

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        System.out.println("End explicit  =" + getTimeNow());
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIDBy));
        System.out.println("End explicit  =" + getTimeNow());
    }
    @Test
    public void TC_04_Element_Not_Found_Implicit_Explicit() {
        driver.get("https://www.facebook.com/");

        By emailIDBy = By.id("vietnam");

        //1- Implicit < Explicit
        //2- Implicit = Explicit
        //3- Implicit > Explicit

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        System.out.println("Start implicit ="+getTimeNow());
//        driver.findElement(emailIDBy).isDisplayed();
//        System.out.println("End implicit  =" + getTimeNow());

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        System.out.println("End explicit  =" + getTimeNow());
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIDBy));
        System.out.println("End explicit  =" + getTimeNow());
        //=> implicit se ko bi anh huong boi bat ky loai wait nao len no
        //=> explicit dang bi implicit anh huong , vi implicit no nho hon nen chua thay
        //chay theo dang Async: implicit se kich hoat chay de tim element truoc
        //sau 1 don vi time la 0.5 s ~ 1s thi explicit se dc kich hoat de chay luon
    }
    @Test
    public void TC_05_Element_Not_Found_Only_Explicit_Webelement() {
//        driver.get("https://www.facebook.com/");
//
//        WebElement emailIDTextbox = driver.findElement(By.id("email"));
//
//        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        System.out.println("End explicit  =" + getTimeNow());
//        explicitWait.until(ExpectedConditions.visibilityOf(emailIDTextbox));
//        System.out.println("End explicit  =" + getTimeNow());
        driver.get("https://www.facebook.com/");

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        System.out.println("End explicit  =" + getTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("email"))));
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("End explicit  =" + getTimeNow());
        //case nay se pass , timeout = 0
        //ExpectedConditions.visibilityOf(driver.findElement(By.id("email"))))
        //=> 1 . chay: driver.findElement(By.id("email") => pass ;visibilityOf
        //fail thi stop luon
        //present: co trong DOM, non-present ko co trong DOM
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
