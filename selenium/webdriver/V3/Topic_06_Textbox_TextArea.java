package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_06_Textbox_TextArea {
    WebDriver driver;

    String loginPageUrl;
    String userId;
    String userPassword;
    public void beforeClass() {
      driver = new ChromeDriver();
      loginPageUrl = "https://demo.guru99.com/v4/";
      driver.get(loginPageUrl);
    }

    @Test
    public void TC_Register() {
        driver.findElement(By.xpath("//a[text()='here']")).click();
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("kevink"+getRandomNumber()+"@gmail.com");
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        userId = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        userPassword = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
    }

    @Test
    public void TC_02_Login() {
        driver.get(loginPageUrl);

        driver.findElement(By.name(""));
    }

    @Test
    public void TC_03_New_Customer() {
    }

    @Test
    public void TC_04_Edit_Customer() {
    }

    public void afterClass() {
        driver.quit();
    }
    public int getRandomNumber(){
        Random random = new Random();
        return random.nextInt(99999);
    }
}
