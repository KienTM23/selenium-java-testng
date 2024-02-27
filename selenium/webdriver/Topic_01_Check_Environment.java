package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class Topic_01_Check_Environment {
    WebDriver driver;

    @Test
    public void TC_01_Run_On_Firefox() {
        //fifrefox
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com/");
        driver.quit();
    }

    @Test
    public void TC_02_Run_On_Chrome() {
        //Chrome
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");
        driver.quit();
    }

    @Test
    public void TC_03_Run_On_Edge() {
        //Edge
        driver = new EdgeDriver();
        driver.get("https://www.facebook.com/");
        driver.quit();
    }

    public int getRandomNumber(){
        return new Random().nextInt(9999);
    }

    ///unit test
    @Test
    public void testGetRandomNumber(){
        Topic_01_Check_Environment testClass = new Topic_01_Check_Environment();
        int getRandomnumber = testClass.getRandomNumber();
        Assert.assertTrue(getRandomnumber >= 0 && getRandomnumber <= 9999);
    }
}