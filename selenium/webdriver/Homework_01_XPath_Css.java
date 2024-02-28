package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Homework_01_XPath_Css {

    // 1. Setup : OS/Browser/Web/Page/Data/Variable/ Object..
    WebDriver driver;

    //chay truoc cac testcase
    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();
        driver.get("https://accounts.shopify.com/lookup?rid=6bd3307e-e0c2-402b-bfb4-2879a453e9a8");
    }

    // 2. Action/ Execute: Tương tác lên element nào/ nhập liệu/ verify..

    @Test
    public void TC_01_test_locator(){
        driver.get("https://accounts.shopify.com/lookup?rid=6bd3307e-e0c2-402b-bfb4-2879a453e9a8");

        driver.findElement(By.id("account_email")).sendKeys("kientm@gmail.com");
        sleep(3);

        driver.findElement(By.xpath("//button[@name='commit']"));
        sleep(3);
    }

    @Test
    public void TC_02_(){

    }
    // 3. Clean : Delete data test/account/close browser...

    // chay sau cac testcase
    @AfterClass
    public void cleanBrowser()
    {
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
