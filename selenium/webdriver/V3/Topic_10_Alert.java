package webdriver.V3;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_10_Alert {

    WebDriver driver;
    WebDriverWait explicitWait;
    Alert alert;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofMillis(2000));
    }


    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        //switch qua alert
//        alert = driver.switchTo().alert();
        //cho alert xuat hien + switch qua luon
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form");

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        //switch qua alert
        alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");

        alert.dismiss();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
    }

    @Test
    public void TC_03_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form");

        String textToSend = "Automation";
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        //switch qua alert
        alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am a JS prompt");
        alert.sendKeys(textToSend);

        alert.accept();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + textToSend);
    }

    @Test
    public void TC_04_Authentication_Alert_I() {
        String username = "admin";
        String password = "admin";

        driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
//        driver.get("http://the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations!')]")).isDisplayed());
    }

    @Test
    public void TC_05_Authentication_Alert_II() {
        String username = "admin";
        String password = "admin";

        driver.get("https://the-internet.herokuapp.com/");

        String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        driver.get(getAuthenticationLink(basicAuthenLink,username,password));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations!')]")).isDisplayed());
    }

    public String getAuthenticationLink(String url,String username,String password){
        String[] links = url.split("//");
        url = links[0] + "//"  + "//" + username+":" + password+"@" +links[1];
        return url;
    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
