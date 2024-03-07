package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class Topic_05_Element_Homework_PII {
    WebDriver driver;


    By emailTextBy = By.id("email");

    By passwordBy = By.id("pass");
    By linkAccountBy = By.xpath("//div[@class='footer']//a[@title='My Account']");
    By loginButtonBy = By.xpath("//button[@title='Login']");

    By emailMessageError = By.xpath("//div[@class='content fieldset']//div[@id='advice-required-entry-email']");
    By passwordMessageError = By.xpath("//div[@class='content fieldset']//div[@id='advice-required-entry-pass']");

    @BeforeClass
    public void beforeClass() { //pre-condition
//        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void beforeMethod(){
        driver = new ChromeDriver();
        driver.get("http://live.techpanda.org/");
        driver.findElement(linkAccountBy).click();
    }
    @Test
    public void Login_with_empty_email_and_password() {
        driver.findElement(emailTextBy).sendKeys("");
        driver.findElement(passwordBy).sendKeys("");

        driver.findElement(loginButtonBy).click();

        Assert.assertEquals(driver.findElement(emailMessageError).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(passwordMessageError).getText(),"This is a required field.");
    }

    @Test
    public void Login_with_invalid_email() {
        driver.findElement(emailTextBy).sendKeys("");
        driver.findElement(passwordBy).sendKeys("");

        driver.findElement(emailTextBy).sendKeys(getRandomNumber()+"@"+getRandomNumber());
        driver.findElement(passwordBy).sendKeys("123456");
        driver.findElement(loginButtonBy).click();

        Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void Login_with_password_less_6_characters() {
        driver.findElement(emailTextBy).sendKeys("");
        driver.findElement(passwordBy).sendKeys("");

        driver.findElement(emailTextBy).sendKeys("automation@gmail.com");
        driver.findElement(passwordBy).sendKeys("123");
        driver.findElement(loginButtonBy).click();

        Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void Login_with_incorrect_email_password() {
        driver.findElement(emailTextBy).sendKeys("");
        driver.findElement(passwordBy).sendKeys("");

        driver.findElement(emailTextBy).sendKeys("automation@gmail.com");
        driver.findElement(passwordBy).sendKeys("123444");
        driver.findElement(loginButtonBy).click();

        Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='messages']//span")).getText(),"Invalid login or password.");
    }


    @AfterClass
    public void cleanBrowser() { //Post-condition

//        driver.quit();
    }

    public void sleepInSecond(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getRandomNumber(){
        Random random = new Random();
        return random.nextInt(999999);
    }
}
