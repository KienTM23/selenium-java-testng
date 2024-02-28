package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_XPath_CSS {

    // 1. Setup : OS/Browser/Web/Page/Data/Variable/ Object..
    WebDriver driver;

    //chay truoc cac testcase
    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.get("https://www.youtube.com/watch?v=Kn8PrTP4CmI");
    }

    // 2. Action/ Execute: Tương tác lên element nào/ nhập liệu/ verify..

    @Test
    public void TC_01_() {
        driver.get("https://www.facebook.com/");

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
    }

    @Test
    public void TC_02_() {

    }
    // 3. Clean : Delete data test/account/close browser...

    // chay sau cac testcase
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}
